package com.mockneat.mock.unit.text;

import com.mockneat.alphabets.Alphabets;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.mockneat.mock.Constants.*;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static com.mockneat.types.enums.StringType.*;
import static java.util.stream.IntStream.range;
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
        loop(STRING_CYCLES, MOCKS, (m) -> {
            int size = M.ints().range(100, 1000).val();
            String sized = M.strings().size(size).val();
            assertTrue(size == sized.length());
        });
    }

    @Test
    public void testNumbers() throws Exception {
        loop(STRING_CYCLES, MOCKS, (m) -> {
            int size = M.ints().range(10, 100).val();
            String numeric = M.strings().size(size).type(NUMBERS).val();
            assertTrue(size == numeric.length());
            assertTrue(isNumeric(numeric));
        });
    }

    @Test
    public void testLetters() throws Exception {
        loop(STRING_CYCLES, MOCKS, (m) -> {
            int size = M.ints().range(10, 100).val();
            String letters = M.strings().size(size).type(LETTERS).val();
            assertTrue(size == letters.length());
            assertTrue(isAlpha(letters));
        });
    }

    @Test
    public void testAlphaNumeric() throws Exception {
        loop(STRING_CYCLES, MOCKS, (m) -> {
            int size = M.ints().range(10, 100).val();
            String alphaNumeric = M.strings().size(size).type(ALPHA_NUMBERIC).val();
            assertTrue(size == alphaNumeric.length());
            assertTrue(isAlphanumeric(alphaNumeric));
        });
    }

    @Test
    public void testSpecialCharacters() throws Exception {
        Set<Character> specials = new HashSet<>(Alphabets.SPECIAL_CHARACTERS);
        loop(STRING_CYCLES, MOCKS, (m) -> {
            int size = M.ints().range(10, 100).val();
            String specialChars = M.strings().size(size).type(SPECIAL_CHARACTERS).val();
            assertTrue(size == specialChars.length());
            range(0, size).forEach(i -> {
                if (!specials.contains(specialChars.charAt(i))) fail();
            });
        });
    }

    @Test
    public void testHex() throws Exception {
        Set<Character> hexChars = new HashSet<>(Alphabets.HEXA);
        loop(STRING_CYCLES, MOCKS, (m) -> {
            int size = M.ints().range(10, 100).val();
            String hex = M.strings().size(size).type(HEX).val();
            assertTrue(size == hex.length());
            range(0, size).forEach(i -> {
                if (!hexChars.contains(hex.charAt(i)))
                    fail();
            });
        });
    }
}
