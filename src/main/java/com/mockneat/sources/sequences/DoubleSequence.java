package com.mockneat.sources.sequences;

/**
 * Created by andreinicolinciobanu on 26/10/2016.
 */
public class DoubleSequence extends NumberSequence<Double> {

    public DoubleSequence(Double start, Double stop, Double step, Boolean cycle) {
        super(start, stop, step, cycle);
    }

    @Override
    public Double nextValue() {
        if (cycle && currentValue.equals(stop)) {
            currentValue = start;
            return currentValue;
        }
        return currentValue++;
    }
}
