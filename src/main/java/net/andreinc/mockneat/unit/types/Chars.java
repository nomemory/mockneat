package net.andreinc.mockneat.unit.types;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.alphabets.Alphabets;
import net.andreinc.mockneat.interfaces.MockUnit;
import net.andreinc.mockneat.types.enums.CharsType;

import java.util.function.Supplier;

import static net.andreinc.mockneat.alphabets.Alphabets.*;
import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class Chars implements MockUnit<Character> {

    private final MockNeat mock;

    public Chars(MockNeat mock) {
        this.mock = mock;
    }

    @Override
    public Supplier<Character> supplier() {
        return mock.from(Alphabets.ALPHA_NUMERIC)::val;
    }

    public MockUnit<Character> digits() {
        return mock.from(DIGITS);
    }

    public MockUnit<Character> lowerLetters() {
        return mock.from(LETTERS_LOWERCASE);
    }

    public MockUnit<Character> upperLetters() {
        return mock.from(LETTERS_UPPERCASE);
    }

    public MockUnit<Character> letters() {
        return mock.from(LETTERS);
    }

    public MockUnit<Character> hex() { return mock.from(HEXA); }

    public MockUnit<Character> alphaNumeric() { return mock.from(ALPHA_NUMERIC); }

    public MockUnit<Character> type(CharsType type) {
        notNull(type, "type");
        switch (type) {
            case DIGITS: return digits();
            case HEX: return hex();
            case LOWER_LETTERS: return lowerLetters();
            case UPPER_LETTERS: return upperLetters();
            case LETTERS: return letters();
            case ALPHA_NUMERIC: return alphaNumeric();
            default: throw new IllegalArgumentException("Invalid CharsType");
        }
    }

    public MockUnit<Character> types(CharsType... types) {
        notEmptyOrNullValues(types, "types");
        CharsType type = mock.from(types).val();
        return type(type);
    }

    public MockUnit<Character> from(String alphabet) {
        notEmpty(alphabet, "alphabet");
        Supplier<Character> supp = () -> {
            int idx = mock.getRandom().nextInt(alphabet.length());
            return alphabet.charAt(idx);
        };
        return () -> supp;
    }

    public MockUnit<Character> from(char[] alphabet) {
        notEmpty(alphabet, "alphabet");
        Supplier<Character> supp = () -> {
            int idx = mock.getRandom().nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
