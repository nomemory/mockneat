package net.andreinc.mockneat.unit.types;

import net.andreinc.mockneat.Constants;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static java.util.Arrays.stream;
import static net.andreinc.mockneat.alphabets.Alphabets.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;

public class CharsTest {

    protected void inAlpabet(List<Character> alphabet, Supplier<Character> charSuppl) {
        Set<Character> possibleDigits = new HashSet<>(alphabet);
        loop(Constants.CHARS_CYCLES, () -> {
            char c = charSuppl.get();
            Assert.assertTrue(possibleDigits.contains(c));
        });
    }

    @Test
    public void testAlphaNumeric() {
        stream(Constants.MOCKS).forEach(rand ->
                inAlpabet(ALPHA_NUMERIC, rand.chars()::val));
    }

    @Test
    public void testDigits() {
        stream(Constants.MOCKS).forEach(rand ->
                inAlpabet(DIGITS, rand.chars().digits()::val));
    }

    @Test
    public void testLowerLetters() {
        stream(Constants.MOCKS).forEach(rand ->
                inAlpabet(LETTERS_LOWERCASE, rand.chars().lowerLetters()::val));
    }

    @Test
    public void testUpperLetters() {
        stream(Constants.MOCKS).forEach(rand ->
                inAlpabet(LETTERS_UPPERCASE, rand.chars().upperLetters()::val));
    }

    @Test
    public void testLetters() {
        stream(Constants.MOCKS).forEach(rand ->
                inAlpabet(LETTERS, rand.chars().letters()::val));
    }

    @Test
    public void testHexa() {
        stream(Constants.MOCKS).forEach(rand ->
                inAlpabet(HEXA, rand.chars().hex()::val));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromStringEmptyAlphabet() {
        String alphabet = "";
        Constants.M.chars().from(alphabet).val();
    }

    @Test(expected = NullPointerException.class)
    public void testFromStringNullAlphabet() {
        Constants.M.chars().from((String) null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromArrayEmpty() {
        char[] alphabet = {};
        Constants.M.chars().from(alphabet).val();
    }

    @Test(expected = NullPointerException.class)
    public void testFromArrayNull() {
        Constants.M.chars().from((char[]) null).val();
    }
}
