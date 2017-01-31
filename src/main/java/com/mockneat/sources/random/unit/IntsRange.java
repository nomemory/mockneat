package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitInt;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.mockneat.utils.NextUtils.checkIntegerBounds;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public class IntsRange implements RandUnitInt {
    private Rand rand;
    private Random random;

    private Integer lowerBound = Integer.MIN_VALUE;
    private Integer upperBound = Integer.MAX_VALUE;

    public IntsRange(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    public IntsRange(Rand rand, Integer lowerBound, Integer upperBound) {
        this.rand = rand;
        this.random = rand.getRandom();
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public IntsRange lower(Integer lowerBound) {
        this.lowerBound = lowerBound;
        return this;
    }

    public IntsRange upper(Integer upperBound) {
        this.upperBound = upperBound;
        return this;
    }

    /**
     * Returns a (pseudo)random number in the range [lowerBound, upperBound).
     * All possible Integer stream (from the range) are produced with approximately the same probability.
     *
     * @return The name possible (pseudo)random number, uniformly distributed in the given interval.
     */
    @Override
    public Integer val() {
        checkIntegerBounds(lowerBound, upperBound);

        if (random instanceof ThreadLocalRandom) {
            return ((ThreadLocalRandom) random).nextInt(lowerBound, upperBound);
        }

        return random.nextInt(upperBound - lowerBound) + lowerBound;
    }
}
