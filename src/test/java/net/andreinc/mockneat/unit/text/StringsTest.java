package net.andreinc.mockneat.unit.text;

import net.andreinc.mockneat.alphabets.Alphabets;
import net.andreinc.mockneat.types.enums.StringType;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.types.enums.StringType.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.StringUtils.*;
import static org.junit.Assert.*;

public class StringsTest {

    @Test(expected = IllegalArgumentException.class)
    public void testSizeNegative() {
        M.strings().size(-10).val();
    }

    @Test
    public void testVariableSizes() {
        loop(
                STRING_CYCLES,
                MOCKS,
                m -> {
                    int size = m.ints().range(100, 1000).val();
                    String sized = m.strings().size(size).val();
                    assertEquals(size, sized.length());
                }
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullMockUnitIntSize() {
        loop(
                STRING_CYCLES,
                MOCKS,
                m -> m.strings().size(m.fromInts(new int[]{-10, -10, -20})).type(ALPHA_NUMERIC).val(),
                Assert::assertNotNull
        );
    }

    @Test
    public void testNumbers() {
        loop(
                STRING_CYCLES,
                MOCKS,
                m -> {
                    int size = m.ints().range(10, 100).val();
                    String numeric = m.strings().size(size).type(NUMBERS).val();
                    assertEquals(size, numeric.length());
                    assertTrue(isNumeric(numeric));
                }
        );
    }

    @Test
    public void testLetters() {
        loop(
                STRING_CYCLES,
                MOCKS,
                m -> {
                    int size = m.ints().range(10, 100).val();
                    String letters = m.strings().size(size).type(LETTERS).val();
                    assertEquals(size, letters.length());
                    assertTrue(isAlpha(letters));
                }
        );
    }

    @Test
    public void testAlphaNumeric() {
        loop(
                STRING_CYCLES,
                MOCKS,
                m -> {
                    int size = m.ints().range(10, 100).val();
                    String alphaNumeric = m.strings().size(size).type(ALPHA_NUMERIC).val();
                    assertEquals(size, alphaNumeric.length());
                    assertTrue(isAlphanumeric(alphaNumeric));
                }
        );
    }

    @Test
    public void testSpecialCharacters() {
        loop(
                STRING_CYCLES,
                MOCKS,
                m -> {
                    int size = m.ints().range(10, 100).val();
                    String specialChars = m.strings().size(size).type(SPECIAL_CHARACTERS).val();
                    assertEquals(size, specialChars.length());
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
    public void testHex() {
        loop(
                STRING_CYCLES,
                MOCKS,
                m -> {
                    int size = m.ints().range(10, 100).val();
                    String hex = m.strings().size(size).type(HEX).val();
                    assertEquals(size, hex.length());
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
    public void testType() {
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
                        default: throw new IllegalArgumentException("Invalid StringType.");
                    }
                }
        );
    }
}
