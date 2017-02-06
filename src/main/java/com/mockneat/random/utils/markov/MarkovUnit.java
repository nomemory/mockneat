package com.mockneat.random.utils.markov;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static com.mockneat.utils.StringUtils.capitalize;
import static com.mockneat.utils.StringUtils.lowerCase;
import static java.util.Arrays.stream;

/**
 * Created by andreinicolinciobanu on 01/02/2017.
 */
public class MarkovUnit {

    private Map<WordState, WordStatistic> chain;
    private RandUnit<WordState> randState;
    private Integer stateSize = 2;
    private Rand rand = new Rand();

    public MarkovUnit(Rand rand, List<String> lines, Integer stateSize) {
        this.stateSize = stateSize;
        this.chain = getChain(getRawChain(getWords(lines)));
        this.rand = rand;
        this.randState = this.rand.objs().fromKeys(chain);
    }

    public MarkovUnit(Rand rand, String textFile, Integer stateSize) throws IOException {
        this.stateSize = stateSize;
        this.rand = rand;

        List<String> lines = Files.readAllLines(Paths.get(textFile));
        this.chain = getChain(getRawChain(getWords(lines)));
        this.randState = this.rand.objs().fromKeys(chain);
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

    public String  generateText(Integer maxLength) {
        //TODO validate rand, minLength, maxLength

        // Obtain a random state from the existing states
        StringBuilder buff = new StringBuilder();
        WordState state = randState.val();
        String prev = ".";
        WordStatistic statistic;
        String next;
        while (buff.length() < maxLength) {
            statistic = chain.get(state);
            while(null==statistic) {
                statistic = chain.get(randState.val());
            }
            next = statistic.nextWord();
            state = state.nextState(next);
            if (prev.endsWith(".")) {
                next = capitalize(next);
            }
            prev = next;
            buff.append(next).append(" ");
        }
        return buff.subSequence(0, maxLength).toString();
    }
}
