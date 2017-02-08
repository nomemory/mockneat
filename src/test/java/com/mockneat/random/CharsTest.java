package com.mockneat.random;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static com.mockneat.alphabets.Alphabets.*;
import static com.mockneat.random.RandTestConstants.CHARS_CYCLES;
import static com.mockneat.random.RandTestConstants.RAND;
import static com.mockneat.random.RandTestConstants.RANDS;
import static com.mockneat.utils.FunctUtils.cycle;
import static java.util.Arrays.stream;

public class CharsTest {

    protected void inAlpabet(List<Character> alphabet, Supplier<Character> charSuppl) {
        Set<Character> possibleDigits = new HashSet<>(alphabet);
        cycle(CHARS_CYCLES, () -> {
            char c = charSuppl.get();
            Assert.assertTrue(possibleDigits.contains(c));
        });
    }

    @Test
    public void testAlphaNumeric() throws Exception {
        stream(RANDS).forEach(rand ->
                inAlpabet(ALPHA_NUMERIC, rand.chars()::val));
    }

    @Test
    public void testDigits() throws Exception {
        stream(RANDS).forEach(rand ->
                inAlpabet(DIGITS, rand.chars().digits()::val));
    }

    @Test
    public void testLowerLetters() throws Exception {
        stream(RANDS).forEach(rand ->
                inAlpabet(LETTERS_LOWERCASE, rand.chars().lowerLetters()::val));
    }

    @Test
    public void testUpperLetters() throws Exception {
        stream(RANDS).forEach(rand ->
                inAlpabet(LETTERS_UPPERCASE, rand.chars().upperLetters()::val));
    }

    @Test
    public void testLetters() throws Exception {
        stream(RANDS).forEach(rand ->
                inAlpabet(LETTERS, rand.chars().letters()::val));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromStringEmptyAlphabet() throws Exception {
        String alphabet = "";
        RAND.chars().from(alphabet).val();
    }

    @Test(expected = NullPointerException.class)
    public void testFromStringNullAlphabet() throws Exception {
        String alphabet = null;
        RAND.chars().from(alphabet).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromArrayEmpty() throws Exception {
        char[] alphabet = {};
        RAND.chars().from(alphabet).val();
    }

    @Test(expected = NullPointerException.class)
    public void testFromArrayNull() throws Exception {
        char[] alphabet = null;
        RAND.chars().from(alphabet).val();
    }
}
