package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;

import java.util.Random;

import static com.mockneat.utils.NextUtils.checkFloatBound;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public class FloatsBound implements RandUnitGeneric<Float> {

    private Rand rand;
    private Random random;
    private Float bound = Float.MAX_VALUE;

    public FloatsBound(Rand rand, Float bound) {
        this.rand = rand;
        this.random = rand.getRandom();
        this.bound = bound;
    }

    public FloatsBound(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    /**
     * Returns a (pseudo)random number between [0, bound).
     * <p>
     * All possible double numbers from the interval are supposedly produced with the same
     * probability
     *
     * @return
     */
    public Float val() {
        checkFloatBound(bound);
        return rand.floats().inRange(0.0f, bound).val();
    }
}
