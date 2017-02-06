package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnit;

import static com.mockneat.utils.NextUtils.checkFloatBound;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public class FloatsBound implements RandUnit<Float> {

    private Rand rand;
    private Float bound = Float.MAX_VALUE;

    public FloatsBound(Rand rand, Float bound) {
        this.rand = rand;
        this.bound = bound;
    }

    public FloatsBound(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Float val() {
        checkFloatBound(bound);
        return rand.floats().inRange(0.0f, bound).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
