package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;

import java.util.Random;

public class Floats implements RandUnitGeneric<Float> {
    private Rand rand;
    private Random random;

    public Floats(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    /**
     * Returns a (pseudo)random number (Float) in the [0.0f, 1.0f) range.
     * <p>
     * All possible Float stream are produced with approximately the same probability.
     * <p>
     * Negative stream can also be produced.
     *
     * @return The name (pseudo)random number, uniformly distributed.
     */
    public Float val() {
        return random.nextFloat();
    }

    public FloatsRange inRange() {
        return new FloatsRange(rand);
    }

    public FloatsRange inRange(Float lowerBound, Float upperBound) {
        return new FloatsRange(rand, lowerBound, upperBound);
    }

    public FloatsBound withBound() {
        return new FloatsBound(rand);
    }

    public FloatsBound withBound(Float bound) {
        return new FloatsBound(rand, bound);
    }

    public FloatsFrom from(float[] alphabet) {
        return new FloatsFrom(rand, alphabet);
    }
}
