package net.andreinc.mockneat.unit.types;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static java.util.Arrays.stream;
import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.alphabets.Alphabets.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CharsTest {

    protected void inAlpabet(List<Character> alphabet, Supplier<Character> charSuppl) {
        Set<Character> possibleDigits = new HashSet<>(alphabet);
        loop(CHARS_CYCLES, () -> {
            char c = charSuppl.get();
            assertTrue(possibleDigits.contains(c));
        });
    }

    @Test
    public void testAlphaNumeric() {
        stream(MOCKS).forEach(rand ->
                inAlpabet(ALPHA_NUMERIC, rand.chars()::val));
    }

    @Test
    public void testDigits() {
        stream(MOCKS).forEach(rand ->
                inAlpabet(DIGITS, rand.chars().digits()::val));
    }

    @Test
    public void testLowerLetters() {
        stream(MOCKS).forEach(rand ->
                inAlpabet(LETTERS_LOWERCASE, rand.chars().lowerLetters()::val));
    }

    @Test
    public void testUpperLetters() {
        stream(MOCKS).forEach(rand ->
                inAlpabet(LETTERS_UPPERCASE, rand.chars().upperLetters()::val));
    }

    @Test
    public void testLetters() {
        stream(MOCKS).forEach(rand ->
                inAlpabet(LETTERS, rand.chars().letters()::val));
    }

    @Test
    public void testHexa() {
        stream(MOCKS).forEach(rand ->
                inAlpabet(HEXA, rand.chars().hex()::val));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromStringEmptyAlphabet() {
        String alphabet = "";
        M.chars().from(alphabet).val();
    }

    @Test(expected = NullPointerException.class)
    public void testFromStringNullAlphabet() {
        M.chars().from((String) null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromArrayEmpty() {
        char[] alphabet = {};
        M.chars().from(alphabet).val();
    }

    @Test(expected = NullPointerException.class)
    public void testFromArrayNull() {
        M.chars().from((char[]) null).val();
    }

    private static final char[] ALPHABET = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', '*'};

    @Test(expected = NullPointerException.class)
    public void testStringNullLength() {
        M.chars().from(ALPHABET)
                .string(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStringNegativeLength1() {
        int length = -1;
        M.chars().from(ALPHABET)
                .string(length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStringNegativeLength2() {
        int length = -1;
        M.chars().from(ALPHABET)
                .string(M.fromInts(new int[]{length}))
                .val();
    }

    @Test
    public void testStringZeroLength1() {
        int length = 0;
        loop(1,
                MOCKS,
                m -> m.chars().from(ALPHABET)
                        .string(length)
                        .val(),
                v -> assertEquals(length, v.length()));
    }

    @Test
    public void testStringZeroLength2() {
        int length = 0;
        loop(1,
                MOCKS,
                m -> m.chars().from(ALPHABET)
                        .string(M.fromInts(new int[]{length}))
                        .val(),
                v -> assertEquals(length, v.length()));
    }

    @Test
    public void testString() {
        loop(MOCK_CYCLES,
                MOCKS,
                m -> m.chars().from(ALPHABET)
                        .string(m.ints().range(1, 10))
                        .val(),
                v -> assertTrue(1 <= v.length() && v.length() < 10)
        );
    }

}
