package net.andreinc.mockneat.unit.text;


import net.andreinc.mockneat.alphabets.Alphabets;
import net.andreinc.mockneat.types.enums.StringType;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.types.enums.StringType.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.StringUtils.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by andreinicolinciobanu on 02/03/2017.
 */
public class StringsTest {

    @Test(expected = IllegalArgumentException.class)
    public void testSizeNegative() throws Exception {
        M.strings().size(-10).val();
    }

    @Test
    public void testVariableSizes() throws Exception {
        loop(
                STRING_CYCLES,
                MOCKS,
                (m) -> {
                    int size = M.ints().range(100, 1000).val();
                    String sized = M.strings().size(size).val();
                    assertTrue(size == sized.length());
                }
        );
    }

    @Test
    public void testNumbers() throws Exception {
        loop(
                STRING_CYCLES,
                MOCKS,
                (m) -> {
                    int size = M.ints().range(10, 100).val();
                    String numeric = M.strings().size(size).type(NUMBERS).val();
                    assertTrue(size == numeric.length());
                    assertTrue(isNumeric(numeric));
                }
        );
    }

    @Test
    public void testLetters() throws Exception {
        loop(
                STRING_CYCLES,
                MOCKS,
                (m) -> {
                    int size = M.ints().range(10, 100).val();
                    String letters = M.strings().size(size).type(LETTERS).val();
                    assertTrue(size == letters.length());
                    assertTrue(isAlpha(letters));
                }
        );
    }

    @Test
    public void testAlphaNumeric() throws Exception {
        loop(
                STRING_CYCLES,
                MOCKS,
                (m) -> {
                    int size = M.ints().range(10, 100).val();
                    String alphaNumeric = M.strings().size(size).type(ALPHA_NUMERIC).val();
                    assertTrue(size == alphaNumeric.length());
                    assertTrue(isAlphanumeric(alphaNumeric));
                }
        );
    }

    @Test
    public void testSpecialCharacters() throws Exception {
        loop(
                STRING_CYCLES,
                MOCKS,
                (m) -> {
                    int size = M.ints().range(10, 100).val();
                    String specialChars = M.strings().size(size).type(SPECIAL_CHARACTERS).val();
                    assertTrue(size == specialChars.length());
                    verifySpecialChars(specialChars);
                }
        );
    }

    private void verifySpecialChars(String str) {
        Set<Character> specials = new HashSet<>(Alphabets.SPECIAL_CHARACTERS);
        range(0, str.length()).forEach(i -> {
            if (!specials.contains(str.charAt(i))) fail();
        });
    }

    @Test
    public void testHex() throws Exception {
        loop(
                STRING_CYCLES,
                MOCKS,
                (m) -> {
                    int size = M.ints().range(10, 100).val();
                    String hex = M.strings().size(size).type(HEX).val();
                    assertTrue(size == hex.length());
                    verifyHex(hex);
                }
        );
    }

    private void verifyHex(String str) {
        Set<Character> hexChars = new HashSet<>(Alphabets.HEXA);
        range(0, str.length()).forEach(i -> {
            if (!hexChars.contains(str.charAt(i)))
                fail();
        });
    }

    @Test
    public void testType() throws Exception {
        loop(
                STRING_CYCLES,
                MOCKS,
                m -> {
                    StringType type = m.from(StringType.class).val();
                    String str = m.strings().type(type).val();
                    switch (type) {
                        case NUMBERS: { assertTrue(isNumeric(str)); break; }
                        case ALPHA_NUMERIC: { assertTrue(isAlphanumeric(str)); break; }
                        case LETTERS: { assertTrue(isAlpha(str)); break; }
                        case HEX : { verifyHex(str); break; }
                        case SPECIAL_CHARACTERS: { verifySpecialChars(str); break; }
                    }
                }
        );
    }
}
