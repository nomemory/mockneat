package com.mockneat.random.unit.types;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import com.mockneat.alphabets.Alphabets;
import com.mockneat.random.Rand;
import com.mockneat.random.interfaces.RandUnit;
import com.mockneat.random.utils.ValidationUtils;
import com.mockneat.types.enums.CharsType;
import org.apache.commons.lang3.Validate;
import java.util.function.Supplier;
import static com.mockneat.alphabets.Alphabets.*;
import static com.mockneat.random.utils.ValidationUtils.*;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class Chars implements RandUnit<Character> {

    private Rand rand;

    public Chars(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<Character> supplier() {
        return rand.from(Alphabets.ALPHA_NUMERIC)::val;
    }

    public RandUnit<Character> digits() {
        return rand.from(DIGITS);
    }

    public RandUnit<Character> lowerLetters() {
        return rand.from(LETTERS_LOWERCASE);
    }

    public RandUnit<Character> upperLetters() {
        return rand.from(LETTERS_UPPERCASE);
    }

    public RandUnit<Character> letters() {
        return rand.from(LETTERS);
    }

    public RandUnit<Character> hex() { return rand.from(HEXA); }

    public RandUnit<Character> type(CharsType type) {
        notNull(type, INPUT_PARAMETER_NOT_NULL, "type");
        switch (type) {
            case DIGITS: return digits();
            case HEX: return hex();
            case LOWER_LETTERS: return lowerLetters();
            case UPPER_LETTERS: return upperLetters();
            case LETTERS: return letters();
            default: return letters();
        }
    }

    public RandUnit<Character> types(CharsType... types) {
        notEmpty(types, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "types");
        CharsType type = rand.from(types).val();
        return type(type);
    }

    public RandUnit<Character> from(String alphabet) {
        Validate.notEmpty(alphabet, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "alphabet");
        Supplier<Character> supp = () -> {
            int idx = rand.getRandom().nextInt(alphabet.length());
            return alphabet.charAt(idx);
        };
        return () -> supp;
    }

    public RandUnit<Character> from(char[] alphabet) {
        ValidationUtils.notEmpty(alphabet, INPUT_PARAMETER_NOT_NULL, "alphabet");
        Supplier<Character> supp = () -> {
            int idx = rand.getRandom().nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
