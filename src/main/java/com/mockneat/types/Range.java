package com.mockneat.types;

/**
 * Created by andreinicolinciobanu on 20/12/2016.
 */
public class Range<T extends Number> {

    private T lowerBound;
    private T upperBound;

    public Range(T lowerBound, T uppeerBound) {
        this.lowerBound = lowerBound;
        this.upperBound = uppeerBound;
    }

    public T getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(T lowerBound) {
        this.lowerBound = lowerBound;
    }

    public T getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(T upperBound) {
        this.upperBound = upperBound;
    }

    public Boolean isConstant() { return lowerBound.equals(upperBound); }
}
