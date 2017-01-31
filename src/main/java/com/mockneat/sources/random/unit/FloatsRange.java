package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;

import java.util.Random;
import java.util.stream.Stream;

import static com.mockneat.utils.NextUtils.checkFloatBounds;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public class FloatsRange implements RandUnitGeneric<Float> {

    private Rand rand;
    private Random random;

    private Float lowerBound = Float.MIN_VALUE;
    private Float upperBound = Float.MAX_VALUE;

    public FloatsRange(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    public FloatsRange(Rand rand, Float lowerBound, Float upperBound) {
        this.rand = rand;
        this.random = rand.getRandom();
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public FloatsRange lower(Float lowerBound) {
        this.lowerBound = lowerBound;
        return this;
    }

    public FloatsRange upper(Float upperBound) {
        this.upperBound = upperBound;
        return this;
    }

    /**
     * Returns a (pseudo)random number between [lowerBound, upperBound)
     * <p>
     * All possible double numbers from the interval are supposedly produced with the same
     * probability
     * @return
     */
    public Float val() {
        checkFloatBounds(lowerBound, upperBound);
        if (Float.valueOf(upperBound - lowerBound).isInfinite()) {
            throw new IllegalArgumentException("Infinite bound difference.");
        } else {
            return rand.floats().val() * (upperBound - lowerBound) + lowerBound;
        }
    }

    /**
     * Returns a names of (pseudo)random numbers between [lowerBound, upperBound)
     * <p>
     * All possible double numbers from the interval are supposedly produced with the same
     * probability
     *
     * @return
     */
    public Stream<Float> stream() {
        return Stream.generate(this::val);
    }
}
