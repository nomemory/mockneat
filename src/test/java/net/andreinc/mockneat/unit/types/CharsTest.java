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
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

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
    public void testAlphaNumeric() throws Exception {
        stream(Constants.MOCKS).forEach(rand ->
                inAlpabet(ALPHA_NUMERIC, rand.chars()::val));
    }

    @Test
    public void testDigits() throws Exception {
        stream(Constants.MOCKS).forEach(rand ->
                inAlpabet(DIGITS, rand.chars().digits()::val));
    }

    @Test
    public void testLowerLetters() throws Exception {
        stream(Constants.MOCKS).forEach(rand ->
                inAlpabet(LETTERS_LOWERCASE, rand.chars().lowerLetters()::val));
    }

    @Test
    public void testUpperLetters() throws Exception {
        stream(Constants.MOCKS).forEach(rand ->
                inAlpabet(LETTERS_UPPERCASE, rand.chars().upperLetters()::val));
    }

    @Test
    public void testLetters() throws Exception {
        stream(Constants.MOCKS).forEach(rand ->
                inAlpabet(LETTERS, rand.chars().letters()::val));
    }

    @Test
    public void testHexa() throws Exception {
        stream(Constants.MOCKS).forEach(rand ->
                inAlpabet(HEXA, rand.chars().hex()::val));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromStringEmptyAlphabet() throws Exception {
        String alphabet = "";
        Constants.M.chars().from(alphabet).val();
    }

    @Test(expected = NullPointerException.class)
    public void testFromStringNullAlphabet() throws Exception {
        String alphabet = null;
        Constants.M.chars().from(alphabet).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromArrayEmpty() throws Exception {
        char[] alphabet = {};
        Constants.M.chars().from(alphabet).val();
    }

    @Test(expected = NullPointerException.class)
    public void testFromArrayNull() throws Exception {
        char[] alphabet = null;
        Constants.M.chars().from(alphabet).val();
    }
}
