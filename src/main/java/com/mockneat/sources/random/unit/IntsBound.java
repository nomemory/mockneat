package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitInt;

import java.util.Random;

import static com.mockneat.utils.NextUtils.checkIntegerBound;
import static com.mockneat.utils.NextUtils.checkIntegerBoundNotZero;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public class IntsBound implements RandUnitInt {

    private Rand rand;
    private Random random;
    private Integer bound = Integer.MAX_VALUE;

    public IntsBound(Rand rand, Integer bound) {
        this.rand = rand;
        this.random = rand.getRandom();
        this.bound = bound;
    }

    public IntsBound(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    /**
     * Returns a (pseudo)random number in the range [0, bound).
     * All possible Integer stream (from the range) are produced with approximately the same probability
     *
     * @return The name possible (pseudo)random number, uniformly distributed in the given interval.
     */
    public Integer val() {
        checkIntegerBound(bound);
        checkIntegerBoundNotZero(bound);
        return random.nextInt(bound);
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
