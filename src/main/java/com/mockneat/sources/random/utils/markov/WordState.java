package com.mockneat.sources.random.utils.markov;

import java.util.*;

/**
 * Created by andreinicolinciobanu on 02/02/2017.
 */
public class WordState {

    private List<String> state;

    public WordState(String... strings) {
        this.state = Arrays.asList(strings);
    }

    public WordState(List<String> list) {
        this.state = list;
    }

    public static WordState fromWords(List<String> words, Integer stateSize, Integer fromIdx) {
        List<String> result = new ArrayList<>(stateSize);
        int size = stateSize;
        int idx = fromIdx;
        while(size-->0) {
            result.add(words.get(idx));
            idx++;
        }
        return new WordState(result);
    }

    public void nextState(String word) {

    }

    public List<String> getState() {
        return state;
    }

    protected void append(String word) {
        this.state.add(word);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordState wordState = (WordState) o;
        return Objects.equals(state, wordState.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public String toString() {
        return "WordState{" +
                "state=" + state +
                '}';
    }
}
