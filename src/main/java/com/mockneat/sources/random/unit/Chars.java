package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.FromAlphabetGenericUnit;

import static com.mockneat.utils.NextUtils.checkAlphabet;

/**
 * Created by andreinicolinciobanu on 03/01/2017.
 */
public class Chars implements FromAlphabetGenericUnit<Character> {

    private Rand rand;

    public Chars(Rand rand) {
        this.rand = rand;
    }

    public CharsDigits digits() {
        return new CharsDigits(rand);
    }

    public CharsLowerLetters lowerLetters() { return new CharsLowerLetters(rand); }

    public CharsUpperLetters upperLetters() { return new CharsUpperLetters(rand); }

    public CharsLetters letters() { return new CharsLetters(rand); }

    public CharsFrom from(String alphabet) { return new CharsFrom(rand, alphabet); }

    public CharsFrom from(char[] alphabet) { return new CharsFrom(rand, alphabet); }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
