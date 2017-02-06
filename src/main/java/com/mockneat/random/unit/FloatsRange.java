package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnit;

import static com.mockneat.utils.NextUtils.checkFloatBounds;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public class FloatsRange implements RandUnit<Float> {

    private Rand rand;

    private Float lowerBound = Float.MIN_VALUE;
    private Float upperBound = Float.MAX_VALUE;

    public FloatsRange(Rand rand) {
        this.rand = rand;
    }

    public FloatsRange(Rand rand, Float lowerBound, Float upperBound) {
        this.rand = rand;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public RandUnit<Float> lower(Float lowerBound) {
        this.lowerBound = lowerBound;
        return this;
    }

    public RandUnit<Float> upper(Float upperBound) {
        this.upperBound = upperBound;
        return this;
    }

    @Override
    public Float val() {
        checkFloatBounds(lowerBound, upperBound);
        if (Float.valueOf(upperBound - lowerBound).isInfinite()) {
            throw new IllegalArgumentException("Infinite bound difference.");
        } else {
            return rand.floats().val() * (upperBound - lowerBound) + lowerBound;
        }
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }

}
