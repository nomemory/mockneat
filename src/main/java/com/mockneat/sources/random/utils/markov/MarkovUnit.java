package com.mockneat.sources.random.utils.markov;

import com.mockneat.sources.random.Rand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static com.mockneat.utils.StringUtils.lowerCase;
import static java.util.Arrays.stream;

/**
 * Created by andreinicolinciobanu on 01/02/2017.
 */
public class MarkovUnit {

    private static final String TEXT_SPLIT_REGEX = " ?(?<!\\G)((?<=[^\\p{Punct}])(?=\\p{Punct})|\\b) ?";

    private Map<WordState, WordStatistic> chain;
    private Integer stateSize = 2;

    public MarkovUnit(List<String> lines, Integer stateSize) {
        this.stateSize = stateSize;
        this.chain = getChain(getRawChain(getWords(lines)));
    }

    public MarkovUnit(String textFile, Integer stateSize) throws IOException {
        this.stateSize = stateSize;

        List<String> lines = Files.readAllLines(Paths.get(textFile));
        this.chain = getChain(getRawChain(getWords(lines)));
    }

    protected List<String> getWords(List<String> lines) {
        List<String> words = new ArrayList<>();
        lines.stream().forEach(line -> {
            line = line.replaceAll("\""," ");
            stream(line.split(" ")).forEach(word -> {
                String trimmed = lowerCase(word.trim());
                if (!"".equals(word))
                    words.add(trimmed);
            });
        });
        return words;
    }

    protected Map<WordState, Map<String, Integer>> getRawChain(List<String> words) {
        Map<WordState, Map<String, Integer>> result = new HashMap<>();
        WordState currentState;
        Map<String, Integer> currentRawValue;
        Integer currentCount;
        int stop = words.size() - stateSize;
        String nextWord = null;
        for(int i = 0; i < stop; i++) {
            nextWord = words.get(i+stateSize);
            currentState = WordState.fromWords(words, stateSize, i);

            currentRawValue = result.get(currentState);
            if (null==currentRawValue) {
                currentRawValue = new HashMap<>();
                result.put(currentState, currentRawValue);
                //System.out.println(currentState);
            }

            currentCount = currentRawValue.get(nextWord);
            if (null==currentCount) {
                currentCount = 0;
            }

            currentRawValue.put(nextWord, ++currentCount);
            //System.out.printf("\t %s : %d\n", nextWord, currentCount);
        }
        return result;
    }

    protected Map<WordState, WordStatistic> getChain(Map<WordState, Map<String, Integer>> rawChain) {
        return rawChain
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(e -> e.getKey(),
                                                  e -> new WordStatistic(e.getValue())));
    }

    public void generateText(Rand rand, Integer minLength, Integer maxLength) {
        //TODO validate rand, minLength, maxLength

        // Obtain a random state from the existing states
        WordState state = rand.objs().fromKeys(chain).val();
        WordStatistic statistic;
        String nextWord;
        int cnt = 100;
        while (cnt --> 0) {
            statistic = chain.get(state);
            while(null==statistic) {
                statistic = chain.get(rand.objs().fromKeys(chain).val());
                //System.out.println("\nSTATE: " + state + " NOT FOUND\n");
            }
            nextWord = statistic.nextWord();
            //state.nextState(nextWord);
            state = new WordState(state.getState().get(1), nextWord);
            //System.out.print(nextWord + " ");
        }
    }

    public static void main(String[] args) {
        String textFile = "resources/markov/kafka";
        try{
            List<String> lines = Files.readAllLines(Paths.get(textFile));
            MarkovUnit mu = new MarkovUnit(lines, 2);
            int times = 100;
            while(times-->0) {
                mu.generateText(new Rand(), 0, 100);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
