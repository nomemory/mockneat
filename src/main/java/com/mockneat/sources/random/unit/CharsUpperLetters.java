package com.mockneat.sources.random.unit;

import com.mockneat.sources.alphabets.Alphabets;
import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnit;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class CharsUpperLetters implements RandUnit<Character> {

    public Rand rand;

    public CharsUpperLetters(Rand rand){
        this.rand = rand;
    }

    @Override
    public Character val() {
        return rand.chars().from(Alphabets.LETTERS_UPPERCASE_ARR_CHR).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
