package com.mockneat.sources.sequences;

public class LongSequence extends NumberSequence<Long> {

    public LongSequence(Long start, Long stop, Long step, Boolean cycle) {
        super(start, stop, step, cycle);
    }

    @Override
    public Long nextValue() {
        if (cycle && currentValue.equals(stop)) {
            currentValue = start;
            return currentValue;
        }
        return currentValue++;
    }
}
