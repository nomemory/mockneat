package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitLong;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.mockneat.utils.NextUtils.checkLongBounds;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class LongsRange implements RandUnitLong {

    private Rand rand;
    private Random random;

    private Long lowerBound = Long.MIN_VALUE;
    private Long upperBound = Long.MAX_VALUE;

    public LongsRange(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    public LongsRange(Rand rand, Long lowerBound, Long upperBound) {
        this.rand = rand;
        this.random = rand.getRandom();
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public RandUnitLong lower(Long lowerBound) {
        this.lowerBound = lowerBound;
        return this;
    }

    public RandUnitLong upper(Long upperBound) {
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
    public Long val() {
        checkLongBounds(lowerBound, upperBound);
        if (random instanceof ThreadLocalRandom) {
            // Use the native implementation that is only available for ThreadLocalRandoms
            return ((ThreadLocalRandom) random).nextLong(lowerBound, upperBound);
        }
        return rand.longs().withBound(upperBound - lowerBound).val() + lowerBound;
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
