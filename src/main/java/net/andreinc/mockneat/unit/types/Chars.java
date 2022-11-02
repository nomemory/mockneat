package net.andreinc.mockneat.unit.types;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitChar;
import net.andreinc.mockneat.types.enums.CharsType;

import java.util.function.Supplier;

import static net.andreinc.mockneat.alphabets.Alphabets.*;
import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class Chars extends MockUnitBase implements MockUnitChar {

    /**
     * <p>Returns a {@code Chars} object that can be used to generate arbitrary {@code Character} values.</p>
     *
     * <p><em>Note:</em> Without any additional constraint, the {@code Chars} object will generate alphanumeric characters.</p>
     *
     * @return A re-usable {@code Chars} instance. The class implements {@code MockUnitChar}.
     */
    public static Chars chars() {
        return MockNeat.threadLocal().chars();
    }

    protected Chars() {}

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
     * <p>Returns a new {@code MockUnitChar} that is used to generate arbitrary {@code Character} values. The generated characters are digits.</p>
     *
     * @return A new {@code MockUnitChar} that generates digits characters (Eg.: '0', '1', etc.)
     */
    public MockUnitChar digits() {
        return mockNeat.from(DIGITS)::supplier;
    }

    /**
     * <p>Returns a new {@code MockUnitChar} that is used to generate arbitrary {@code Character} values. The generated characters are lower-case letters.</p>
     *
     * @return A new {@code MockUnitChar} that generates lower-letter characters (Eg.: 'a', 'z', etc.)
     */
    public MockUnitChar lowerLetters() {
        return mockNeat.from(LETTERS_LOWERCASE)::supplier;
    }

    /**
     * <p>Returns a new {@code MockUnitChar} that is used to generate arbitrary {@code Character} values. The generated characters are upper-case letters.</p>
     *
     * @return A new {@code MockUnitChar} that generate lower-letters characters (Eg.: 'A', 'J', etc.)
     */
    public MockUnitChar upperLetters() {
        return mockNeat.from(LETTERS_UPPERCASE)::supplier;
    }

    /**
     * <p>Returns a new {@code MockUnitChar} that is used to generate arbitrary {@code Character} values. The generated values are letters.</p>
     *
     * <p><em>Note: </em> The generated values are both lower-case and uppercase.</p>
     *
     * @return A new {@code MockUnitChar} that generate letters characters (Eg.: 'a', 'X', etc.)
     */
    public MockUnitChar letters() {
        return mockNeat.from(LETTERS)::supplier;
    }

    /**
     * <p>Returns a new {@code MockUnitChar} that is used to generate arbitrary {@code Character} values. The generated values are HEXA-characters.</p>
     *
     * <p><em>Note: </em> By HEXA-characters we mean: {@code {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9","a", "b", "c", "d", "e", "f"}}</p>
     *
     * @return A new {@code MockUnitChar} that generated HEXA-characters.
     */
    public MockUnitChar hex() { return mockNeat.from(HEXA)::supplier; }

    /**
     * <p>Returns a new {@code MockUnitChar} that is used to generate arbitrary {@code Character} values. The generated values are alphanumeric. The ' ' (space) character is not included.</p>
     *
     * @return A new {@code MockUnitChar} that generate alpha-numeric characters.
     */
    public MockUnitChar alphaNumeric() { return mockNeat.from(ALPHA_NUMERIC)::supplier; }

    /**
     * <p>Returns a new {@code MockUnitChar} that is used to generate arbitrary {@code Character} values. The generated characters match the supplied type: {@link CharsType}</p>
     *
     * @param type The type of the characters.
     * @return A new {@code MockUnitChar} that generates {@code Character} values of the given {@code type}.
     */
    public MockUnitChar type(CharsType type) {
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
     * <p>Returns a new {@code MockUnitChar} that is used to generate arbitrary {@code Character} values. The generated characters match one of the supplied types: {@link CharsType} </p>
     *
     * @param types A var-arg array of types. Everything you call {@code val()} on the {@code MockUnit} a new character is generated matching one of the types.
     * @return A new {@code MockUnitChar} that generates {@code Character} values of the given type(s).
     */
    public MockUnitChar types(CharsType... types) {
        notEmptyOrNullValues(types, "types");
        Supplier<Character> supplier = () -> {
            CharsType type = mockNeat.from(types).val();
            return type(type).get();
        };
        return () -> supplier;
    }

    /**
     * <p>Creates a new {@code MockUnitChar} that is used to generate arbitrary characters contained in the {@code alphabet}.</p>
     *
     * @param alphabet The {@code alphabet} from where the characters are randomly picked.
     * @return A new {@code MockUnitChar} that generates {@code Character} values from the given {@code alphabet}.
     */
    public MockUnitChar from(String alphabet) {
        notEmpty(alphabet, "alphabet");
        return from(alphabet.toCharArray());
    }

    /**
     * <p>Creates a new {@code MockUnitChar} that is used to generate arbitrary characters contained in the {@code alphabet}.</p>
     *
     * @param alphabet The {@code alphabet} from where the characters are randomly picked.
     * @return A new {@code MockUnitChar} that generates {@code Character} values from the given {@code alphabet}.
     */
    public MockUnitChar from(char[] alphabet) {
        notEmpty(alphabet, "alphabet");
        Supplier<Character> supp = () -> {
            int idx = mockNeat.getRandom().nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }
}
