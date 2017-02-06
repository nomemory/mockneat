package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnit;
import com.mockneat.random.unit.interfaces.RandUnitInt;

import java.util.Random;

import static com.mockneat.utils.NextUtils.checkFloatAlphabet;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public class FloatsFrom implements RandUnit<Float> {

    private Rand rand;
    private float[] alphabet;
    private RandUnitInt randIdx;

    public FloatsFrom(Rand rand, float[] alphabet) {
        this.rand = rand;
        this.alphabet = alphabet;
        this.randIdx = rand.ints().withBound(alphabet.length);
    }

    @Override
    public Float val() {
        checkFloatAlphabet(alphabet);
        return alphabet[randIdx.val()];
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
