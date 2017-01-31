package com.mockneat.sources.sequences;

public class IntSequence extends NumberSequence<Integer> {

    public IntSequence(Integer start, Integer stop, Integer step, Boolean cycle) {
        super(start, stop, step, cycle);
    }

    @Override
    public synchronized Integer nextValue() {
        if (cycle && currentValue.equals(stop)) {
            currentValue = start;
            return currentValue;
        }
        return currentValue++;
    }
}
