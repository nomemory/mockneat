package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitDouble;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.mockneat.utils.NextUtils.checkDoubleBound;
import static com.mockneat.utils.NextUtils.checkDoubleBoundNotZero;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class DoublesBound implements RandUnitDouble {

    private Rand rand;
    private Random random;
    private Double bound = Double.MAX_VALUE;

    public DoublesBound(Rand rand, Double bound) {
        this.rand = rand;
        this.random = rand.getRandom();
        this.bound = bound;
    }

    public DoublesBound(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    @Override
    public Double val() {

        checkDoubleBound(bound);
        checkDoubleBoundNotZero(bound);

        if (this.random instanceof ThreadLocalRandom) {
            return ((ThreadLocalRandom) random).nextDouble(bound);
        }

        return rand.doubles().inRange(0.0, bound).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
