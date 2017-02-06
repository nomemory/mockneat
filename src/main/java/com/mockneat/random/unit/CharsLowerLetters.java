package com.mockneat.random.unit;

import com.mockneat.alphabets.Alphabets;
import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnit;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class CharsLowerLetters implements RandUnit<Character> {

    private Rand rand;

    public CharsLowerLetters(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Character val() {
        return rand.chars().from(Alphabets.LETTERS_LOWERCASE_ARR_CHR).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
