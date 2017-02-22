package com.mockneat.mock.utils.markov;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.lowerCase;
import static org.apache.commons.lang3.text.WordUtils.capitalize;

public class MarkovUnit {

    private Map<WordState, WordStatistic> chain;
    private MockUnit<WordState> randState;
    private Integer stateSize = 2;
    private MockNeat rand = MockNeat.threadLocal();

    public MarkovUnit(MockNeat rand, List<String> lines, Integer stateSize) {
        this.stateSize = stateSize;
        this.chain = getChain(getRawChain(getWords(lines)));
        this.rand = rand;
        this.randState = this.rand.fromKeys(chain);
    }

    public MarkovUnit(MockNeat rand, String textFile, Integer stateSize) throws IOException {
        this.stateSize = stateSize;
        this.rand = rand;

        List<String> lines = Files.readAllLines(Paths.get(textFile));
        this.chain = getChain(getRawChain(getWords(lines)));
        this.randState = this.rand.fromKeys(chain);
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

        // Obtain a objs state from the existing states
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
