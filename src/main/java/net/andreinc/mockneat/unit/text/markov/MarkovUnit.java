package net.andreinc.mockneat.unit.text.markov;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.utils.file.FileManager;
import net.andreinc.mockneat.types.enums.MarkovChainType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.lowerCase;
import static org.apache.commons.text.WordUtils.capitalize;

public final class MarkovUnit {

    private static final FileManager fm = FileManager.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(MarkovUnit.class);

    private final String path;
    private final Map<WordState, WordStatistic> chain;
    private final MockUnit<WordState> randState;
    private final Integer stateSize;
    private final MockNeat mock;

    private MarkovUnit(MockNeat mock, List<String> lines, String path, Integer stateSize) {
        this.path = path;
        this.stateSize = stateSize;
        this.chain = getChain(getRawChain(getWords(lines)));
        this.mock = mock;
        this.randState = this.mock.fromKeys(chain);
    }

    public static MarkovUnit internal(MockNeat mock, MarkovChainType chainType, int stateSize) throws IOException {
        return new MarkovUnit(mock, fm.read(chainType), chainType.getFile(), stateSize);
    }

    public static MarkovUnit external(MockNeat mock, String path, int stateSize) throws IOException {
        return new MarkovUnit(mock, fm.read(path), path, stateSize);
    }

    private List<String> getWords(List<String> lines) {
        logger.info("Obtaining the list of words from: '{}'.", this.path);
        List<String> words = new ArrayList<>();
        lines.forEach(line -> {
            line = line.replaceAll("\""," ");
            stream(line.split(" ")).forEach(word -> {
                String trimmed = lowerCase(word.trim());
                if (!"".equals(word))
                    words.add(trimmed);
            });
        });
        logger.info("{} words detected in '{}'.", words.size(), path);
        return words;
    }

    private Map<WordState, Map<String, Integer>> getRawChain(List<String> words) {
        logger.info("Building WordState(s) from the words found in '{}'.", path);
        Map<WordState, Map<String, Integer>> result = new HashMap<>();
        WordState currentState;
        Map<String, Integer> currentRawValue;
        Integer currentCount;
        int stop = words.size() - stateSize;
        String nextWord;
        for(int i = 0; i < stop; i++) {
            nextWord = words.get(i+stateSize);
            currentState = WordState.fromWords(words, stateSize, i);

            currentRawValue = result.get(currentState);
            if (null==currentRawValue) {
                currentRawValue = new HashMap<>();
                result.put(currentState, currentRawValue);
            }

            currentCount = currentRawValue.get(nextWord);
            if (null==currentCount) {
                currentCount = 0;
            }

            currentRawValue.put(nextWord, ++currentCount);
        }
        logger.info("{} WordState(s) detected in '{}'.", result.keySet().size(), path);
        return result;
    }

    private Map<WordState, WordStatistic> getChain(Map<WordState, Map<String, Integer>> rawChain) {
        return rawChain
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                                  e -> new WordStatistic(e.getValue())));
    }

    public String  generateText(Integer maxLength) {
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
            buff.append(next).append(' ');
        }
        return buff.subSequence(0, maxLength).toString();
    }
}
