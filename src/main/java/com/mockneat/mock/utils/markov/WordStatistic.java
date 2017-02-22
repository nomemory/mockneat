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
import com.mockneat.types.Pair;

import java.util.*;

public class WordStatistic {

    private Double total = 0.0;
    private Pair<Double, String>[] associatedWords;
    private MockNeat rand;

    public WordStatistic(Map<String, Integer> rawWordCount) {
        this.associatedWords = new Pair[rawWordCount.size()];
        this.rand = MockNeat.threadLocal();
        processRawWordCount(rawWordCount);
    }

    protected void processRawWordCount(Map<String, Integer> rawLine) {
        this.total = getTotal(rawLine);
        int i = 0;
        double cv = 0;
        for(Map.Entry<String, Integer> entry : rawLine.entrySet()) {
            cv+=entry.getValue();
            associatedWords[i] = new Pair<>(cv, entry.getKey());
            i++;
        }
        Arrays.sort(associatedWords, (v1, v2) -> v1.getFirst().compareTo(v2.getFirst()));
    }

    protected Double getTotal(Map<String, Integer> rawLine) {
        return (double) rawLine.values().stream().mapToInt(Integer::intValue).sum();
    }

    public String nextWord() {
        double rd = rand.doubles().bound(total).val();
        for(int i = 0; i < associatedWords.length; ++i) {
            if (rd < associatedWords[i].getFirst()) {
                return associatedWords[i].getSecond();
            }
        }
        return "";
    }
}
