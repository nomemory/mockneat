package com.mockneat.sources.random.unit;

import com.mockneat.sources.alphabets.Alphabets;
import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public class CharsDigits implements RandUnitGeneric<Character> {

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
}
