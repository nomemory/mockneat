package com.mockneat.random.unit;

import com.mockneat.alphabets.Alphabets;
import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnit;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public class CharsDigits implements RandUnit<Character> {

    private Rand rand;

    public CharsDigits(Rand rand) {
        this.rand = rand;
    }

    /**
     * Returns a new digit from the rChars:
     * ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
     */
    @Override
    public Character val() {
        return rand.objs().from(Alphabets.DIGITS_ARR_CHR).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
