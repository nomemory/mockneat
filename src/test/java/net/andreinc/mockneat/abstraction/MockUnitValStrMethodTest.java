package net.andreinc.mockneat.abstraction;

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

import org.junit.Test;

import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.Constants.MOCK_CYCLES;
import static java.lang.Character.isDigit;
import static java.util.Arrays.stream;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.*;

public class MockUnitValStrMethodTest {

    @Test
    public void testValStrNulls() {
        loop(MOCK_CYCLES, MOCKS, m -> m.from(new Integer[]{ null, null, null }).valStr(),
                s -> assertEquals("", s));
    }

    @Test
    public void testStrValBools() {
        loop(MOCK_CYCLES, () -> stream(MOCKS).forEach(r -> {
            String b = r.bools().valStr();
            assertTrue(b.equals("false") || b.equals("true"));
        }));
    }

    @Test
    public void testStrValChars() {
        loop(MOCK_CYCLES, () -> stream(MOCKS).forEach(r -> {
            String c = r.chars().digits().valStr();
            assertTrue(c.length()==1 && isDigit(c.charAt(0)));
        }));
    }

    @Test
    public void testStrValDoubles() {
        loop(MOCK_CYCLES, () -> stream(MOCKS).forEach(r -> {
            String d = r.doubles().valStr();
            try { Double.valueOf(d); }
            catch (NumberFormatException e) { fail(); }
        }));
    }

    @Test
    public void testStrValInts() {
        loop(MOCK_CYCLES, () -> stream(MOCKS).forEach(r -> {
            String i = r.ints().valStr();
            try { Integer.valueOf(i); }
            catch (NumberFormatException e) { fail(); }
        }));
    }

    @Test
    public void testStrValLongs() {
        loop(MOCK_CYCLES, () -> stream(MOCKS).forEach(r -> {
            String l = r.longs().valStr();
            try { Long.valueOf(l); }
            catch (NumberFormatException e) { fail(); }
        }));
    }

    @Test
    public void testStrValFloats() {
        loop(MOCK_CYCLES, () -> stream(MOCKS).forEach(r -> {
            String f = r.floats().valStr();
            try { Float.valueOf(f); }
            catch (NumberFormatException e) { fail(); }
        }));
    }

}
