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
 OTHERWISE, ARISING FROM, OUT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import java.util.*;

public class WordState {

    private String[] state;

    public WordState(String... strings) {
        this.state = strings;
    }

    public static WordState fromWords(List<String> words, int stateSize, int fromIdx) {
        String[] result = new String[stateSize];
        for(int i = 0; i < stateSize; i++) {
            result[i] = words.get(i + fromIdx);
        }
        return new WordState(result);
    }

    public WordState nextState(String word) {
        String[] newState = new String[state.length];
        System.arraycopy(state, 1, newState, 0, state.length-1);
        newState[state.length-1] = word;
        return new WordState(newState);
    }

    public String[] getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        WordState wordState = (WordState) o;
        return Arrays.equals(state, wordState.state);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(state);
    }

    @Override
    public String toString() {
        return "WordState{" +
                "state=" + state +
                '}';
    }
}
