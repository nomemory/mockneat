package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitInt;

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

    @Override
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
