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
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.alphabets.Alphabets;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.types.enums.CharsType;

import java.util.function.Supplier;

import static net.andreinc.mockneat.alphabets.Alphabets.*;
import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class Chars extends MockUnitBase implements MockUnit<Character> {

    public Chars() {}

    public Chars(MockNeat mock) {
        super(mock);
    }

    /**
     * <p>Returns the internal {@code Supplier<Character>} that is used to generate arbitrary {@code Character} values. The generated characters are alphanumeric.</p>
     *
     * @return A {@code Supplier<Character>} used to generate arbitrary {@code Character} values.
     */
    @Override
    public Supplier<Character> supplier() {
        return mockNeat.from(ALPHA_NUMERIC)::val;
    }


    /**
     * <p>Returns a new {@code MockUnit<Character>} that is used to generate arbitrary {@code Character} values. The generated characters are digits.</p>
     *
     * @return A new {@code MockUnit<Character>} that generates digits characters (Eg.: '0', '1', etc.)
     */
    public MockUnit<Character> digits() {
        return mockNeat.from(DIGITS);
    }

    /**
     * <p>Returns a new {@code MockUnit<Character>} that is used to generate arbitrary {@code Character} values. The generated characters are lower-case letters.</p>
     *
     * @return A new {@code MockUnit<Character>} that generates lower-letter characters (Eg.: 'a', 'z', etc.)
     */
    public MockUnit<Character> lowerLetters() {
        return mockNeat.from(LETTERS_LOWERCASE);
    }

    /**
     * <p>Returns a new {@code MockUnit<Character>} that is used to generate arbitrary {@code Character} values. The generated characters are upper-case letters.</p>
     *
     * @return A new {@code MockUnit<Character>} that generate lower-letters characters (Eg.: 'A', 'J', etc.)
     */
    public MockUnit<Character> upperLetters() {
        return mockNeat.from(LETTERS_UPPERCASE);
    }

    /**
     * <p>Returns a new {@code MockUnit<Character>} that is used to generate arbitrary {@code Character} values. The generated values are letters.</p>
     *
     * <p><em>Note: </em> The generated values are both lower-case and uppercase.</p>
     *
     * @return A new {@code MockUnit<Character>} that generate letters characters (Eg.: 'a', 'X', etc.)
     */
    public MockUnit<Character> letters() {
        return mockNeat.from(LETTERS);
    }

    /**
     * <p>Returns a new {@code MockUnit<Character>} that is used to generate arbitrary {@code Character} values. The generated values are HEXA-characters.</p>
     *
     * <p><em>Note: </em> By HEXA-characters we mean: {@code {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9","a", "b", "c", "d", "e", "f"}}</p>
     *
     * @return A new {@code MockUnit<Character>} that generated HEXA-characters.
     */
    public MockUnit<Character> hex() { return mockNeat.from(HEXA); }

    /**
     * <p>Returns a new {@code MockUnit<Character>} that is used to generate arbitrary {@code Character} values. The generated values are alphanumeric. The ' ' (space) character is not included.</p>
     *
     * @return A new {@code MockUnit<Character>} that generate alpha-numeric characters.
     */
    public MockUnit<Character> alphaNumeric() { return mockNeat.from(ALPHA_NUMERIC); }

    /**
     * <p>Returns a new {@code MockUnit<Character>} that is used to generate arbitrary {@code Character} values. The generated characters match the supplied type: {@link CharsType}</p>
     *
     * @param type The type of the characters.
     * @return A new {@code MockUnit<Character>} that generates {@code Character} values of the given {@code type}.
     */
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

    /**
     * <p>Returns a new {@code MockUnit<Character>} that is used to generate arbitrary {@code Character} values. The generated characters match one of the supplied types: {@link CharsType} </p>
     *
     * @param types A var-arg array of types. Everything you call {@code val()} on the {@code MockUnit} a new character is generated matching one of the types.
     * @return A new {@code MockUnit<Character>} that generates {@code Character} values of the given type(s).
     */
    public MockUnit<Character> types(CharsType... types) {
        notEmptyOrNullValues(types, "types");
        CharsType type = mockNeat.from(types).val();
        return type(type);
    }

    /**
     * <p>Creates a new {@code MockUnit<Character>} that is used to generate arbitrary characters contained in the {@code alphabet}.</p>
     *
     * @param alphabet The {@code alphabet} from where the characters are randomly picked.
     * @return A new {@code MockUnit<Character>} that generates {@code Character} values from the given {@code alphabet}.
     */
    public MockUnit<Character> from(String alphabet) {
        notEmpty(alphabet, "alphabet");
        Supplier<Character> supp = () -> {
            int idx = mockNeat.getRandom().nextInt(alphabet.length());
            return alphabet.charAt(idx);
        };
        return () -> supp;
    }

    /**
     * <p>Creates a new {@code MockUnit<Character>} that is used to generate arbitrary characters contained in the {@code alphabet}.</p>
     *
     * @param alphabet The {@code alphabet} from where the characters are randomly picked.
     * @return A new {@code MockUnit<Character>} that generates {@code Character} values from the given {@code alphabet}.
     */
    public MockUnit<Character> from(char[] alphabet) {
        notEmpty(alphabet, "alphabet");
        Supplier<Character> supp = () -> {
            int idx = mockNeat.getRandom().nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
