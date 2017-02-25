package com.mockneat.mock.unit.types;

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
import com.mockneat.mock.utils.ValidationUtils;
import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnit;
import com.mockneat.types.enums.CharsType;
import org.apache.commons.lang3.Validate;
import java.util.function.Supplier;
import static com.mockneat.alphabets.Alphabets.*;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class Chars implements MockUnit<Character> {

    private MockNeat mock;

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

    public MockUnit<Character> type(CharsType type) {
        notNull(type, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "type");
        switch (type) {
            case DIGITS: return digits();
            case HEX: return hex();
            case LOWER_LETTERS: return lowerLetters();
            case UPPER_LETTERS: return upperLetters();
            case LETTERS: return letters();
            default: return letters();
        }
    }

    public MockUnit<Character> types(CharsType... types) {
        notEmpty(types, ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "types");
        CharsType type = mock.from(types).val();
        return type(type);
    }

    public MockUnit<Character> from(String alphabet) {
        Validate.notEmpty(alphabet, ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "alphabet");
        Supplier<Character> supp = () -> {
            int idx = mock.getRandom().nextInt(alphabet.length());
            return alphabet.charAt(idx);
        };
        return () -> supp;
    }

    public MockUnit<Character> from(char[] alphabet) {
        ValidationUtils.notEmpty(alphabet, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "alphabet");
        Supplier<Character> supp = () -> {
            int idx = mock.getRandom().nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
