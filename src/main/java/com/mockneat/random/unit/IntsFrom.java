package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitInt;

import java.util.Random;

import static com.mockneat.utils.NextUtils.checkIntegerAlphabet;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class IntsFrom implements RandUnitInt {
    private Rand rand;
    private int[] alphabet;
    private RandUnitInt randIdx;

    public IntsFrom(Rand rand, int[] alphabet) {
        this.rand = rand;
        this.alphabet = alphabet;
        this.randIdx = rand.ints().withBound(alphabet.length);
    }

    @Override
    public Integer val() {
        checkIntegerAlphabet(alphabet);
        return alphabet[randIdx.val()];
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
