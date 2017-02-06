package com.mockneat.random.utils.markov;

import java.util.*;

/**
 * Created by andreinicolinciobanu on 02/02/2017.
 */
public class WordState {

    private String[] state;

    public WordState(String... strings) {
        this.state = strings;
    }

    public static WordState fromWords(List<String> words, Integer stateSize, Integer fromIdx) {
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
