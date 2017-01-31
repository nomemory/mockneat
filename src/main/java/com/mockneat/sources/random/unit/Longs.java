package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.FromAlphabetLongUnit;
import com.mockneat.sources.random.unit.interfaces.RandUnitLong;

import java.util.Random;

/**
 * Created by andreinicolinciobanu on 02/01/2017.
 */
public class Longs implements RandUnitLong, FromAlphabetLongUnit {

    private Rand rand;
    private Random random;

    public Longs(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    @Override
    public Long val() {
        return random.nextLong();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }

    public LongsBound withBound(Long bound) {
        return new LongsBound(rand, bound);
    }

    public LongsBound withBound() {
        return new LongsBound(rand);
    }

    public LongsRange inRange(Long lowerBound, Long upperBound) {
        return new LongsRange(rand, lowerBound, upperBound);
    }

    public LongsRange inRange() {
        return new LongsRange(rand);
    }

    /**
     * Returns a (pseudo)random val from the rChars array.
     * All possible Long stream (from the array) are produced with approximately the same probability.
     *
     * @param alphabet Must be non-null and non-empty (rChars.length!=0)
     * @return The name possible (pseudo)random Long, uniformly distributed from the rChars array.
     */
    public LongsFrom from(long[] alphabet) {
        return new LongsFrom(rand, alphabet);
    }

}
