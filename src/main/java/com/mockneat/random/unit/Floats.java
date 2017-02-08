package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnit;

import java.util.Random;
import java.util.function.Supplier;

import static com.mockneat.utils.CheckUtils.checkFloatAlphabet;
import static com.mockneat.utils.CheckUtils.checkFloatBound;
import static com.mockneat.utils.CheckUtils.checkFloatBounds;

public class Floats implements RandUnit<Float> {
    private Random random;

    public Floats(Rand rand) {
        this.random = rand.getRandom();
    }

    @Override
    public Supplier<Float> supplier() {
        return random::nextFloat;
    }

    public RandUnit<Float> range(Float lowerBound, Float upperBound) {
        Supplier<Float> supp = () -> {
            checkFloatBounds(lowerBound, upperBound);
            if (Float.valueOf(upperBound - lowerBound).isInfinite()) {
                throw new IllegalArgumentException("Infinite bound difference.");
            } else {
                return random.nextFloat() * (upperBound - lowerBound) + lowerBound;
            }
        };
        return () -> supp;
    }

    public RandUnit<Float> bound(Float bound) {
        checkFloatBound(bound);
        return range(0f, bound);
    }

    public RandUnit<Float> from(float[] alphabet) {
        Supplier<Float> supp = () -> {
            checkFloatAlphabet(alphabet);
            int idx = random.nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
