package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.FromAlphabetGenericUnit;
import com.mockneat.random.unit.interfaces.RandUnit;

import static com.mockneat.utils.NextUtils.checkAlphabet;

/**
 * Created by andreinicolinciobanu on 03/01/2017.
 */
public class Chars implements FromAlphabetGenericUnit {

    private Rand rand;

    public Chars(Rand rand) {
        this.rand = rand;
    }

    public RandUnit<Character> digits() {
        return new CharsDigits(rand);
    }

    public RandUnit<Character> lowerLetters() { return new CharsLowerLetters(rand); }

    public RandUnit<Character> upperLetters() { return new CharsUpperLetters(rand); }

    public RandUnit<Character> letters() { return new CharsLetters(rand); }

    public RandUnit<Character> from(String alphabet) { return new CharsFrom(rand, alphabet); }

    public RandUnit<Character> from(char[] alphabet) { return new CharsFrom(rand, alphabet); }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
