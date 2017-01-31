package com.mockneat.sources.sequences;

public abstract class NumberSequence<T extends Number> {

    protected T start;
    protected T stop;
    protected T step;
    protected Boolean cycle = true;
    protected T currentValue = start;

    public T value() { return currentValue; }

    public NumberSequence(T start, T stop, T step, Boolean cycle) {
        this.start = start;
        this.stop = stop;
        this.step = step;
        this.cycle = cycle;
        this.currentValue = start;
    }

    public abstract T nextValue();
}
