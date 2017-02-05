package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnit;

import java.util.Random;

public class Floats implements RandUnit<Float> {
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

    @Override
    public Rand getRand() {
        return this.rand;
    }

    public RandUnit<Float> inRange() {
        return new FloatsRange(rand);
    }

    public RandUnit<Float> inRange(Float lowerBound, Float upperBound) {
        return new FloatsRange(rand, lowerBound, upperBound);
    }

    public RandUnit<Float> withBound() {
        return new FloatsBound(rand);
    }

    public RandUnit<Float> withBound(Float bound) {
        return new FloatsBound(rand, bound);
    }

    public RandUnit<Float> from(float[] alphabet) {
        return new FloatsFrom(rand, alphabet);
    }
}
