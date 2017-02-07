package com.mockneat.random.unit;

import com.mockneat.alphabets.Alphabets;
import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnit;
import com.mockneat.utils.NextUtils;

import java.util.function.Supplier;

import static com.mockneat.alphabets.Alphabets.*;
import static com.mockneat.utils.NextUtils.checkCharAlphabet;
import static com.mockneat.utils.NextUtils.checkStringAlpabet;

/**
 * Created by andreinicolinciobanu on 03/01/2017.
 */
public class Chars implements RandUnit<Character> {

    private Rand rand;

    public Chars(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<Character> supplier() {
        return () -> rand.objs().from(Alphabets.ALPHA_NUMERIC).val();
    }

    public RandUnit<Character> digits() {
        return rand.objs().from(DIGITS);
    }

    public RandUnit<Character> lowerLetters() {
        return rand.objs().from(LETTERS_LOWERCASE);
    }

    public RandUnit<Character> upperLetters() {
        return rand.objs().from(LETTERS_UPPERCASE);
    }

    public RandUnit<Character> letters() {
        return rand.objs().from(LETTERS);
    }

    public RandUnit<Character> from(String alphabet) {
        Supplier<Character> supp = () -> {
            checkStringAlpabet(alphabet);
            int idx = rand.getRandom().nextInt(alphabet.length());
            return alphabet.charAt(idx);
        };
        return () -> supp;
    }

    public RandUnit<Character> from(char[] alphabet) {
        Supplier<Character> supp = () -> {
            checkCharAlphabet(alphabet);
            int idx = rand.getRandom().nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
