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
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.ArrayUtils.toObject;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FloatsTest {

    @Test
    public void testNextFloatInCorrectRange() throws Exception {
        loop(Constants.FLOATS_CYCLES,
                Constants.MOCKS,
                r -> r.floats().val(),
                n -> assertTrue(n >= 0.0 && n < 1.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatZeroNotBound() throws Exception {
        Constants.M.floats().bound(0.0f).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextFloat_NegativeNotBound() throws Exception {
        Constants.M.floats().bound(-5.0f).val();
    }

    @Test
    public void testNextFloatInCorrectRange2() throws Exception {
        Float bound = 0.01f;
        loop(Constants.FLOATS_CYCLES,
                Constants.MOCKS,
                r -> r.floats().bound(bound).val(),
                n -> assertTrue(n >= 0.0 && n < bound));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatNaNNotBound() throws Exception {
        Constants.M.floats().bound(Float.NaN).val();
    }

    @Test(expected = NullPointerException.class)
    public void nextFloat_NullNotBound() throws Exception {
        Float bound = null;
        Long count = stream(Constants.MOCKS).map(r -> r.floats().bound(bound).val()).count();
        assertNotNull(count);
    }

    @Test
    public void testNextFloatInCorrectRange3() throws Exception {
        Float lowerBound = 1.1987f;
        Float upperBound = Float.MAX_VALUE;

        loop(Constants.FLOATS_CYCLES,
                Constants.MOCKS,
                r -> r.floats().range(lowerBound, upperBound).val(),
                n -> assertTrue(n >= lowerBound && n < upperBound));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatBoundsNotEqual() throws Exception {
        Float lowerBound = 0.01f;
        Float upperBound = lowerBound;

        Constants.M.floats().range(lowerBound, upperBound).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatNaNNotBound2() throws Exception {
        Constants.M.floats().range(Float.NaN, 10.0f).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatNaNNotBound3() throws Exception {
        Constants.M.floats().range(10.0f, Float.NaN).val();
    }

    @Test(expected =  NullPointerException.class)
    public void testNextFloatNullNotBound2() throws Exception {
        Float f = null;
        Constants.M.floats().range(10.0f, f).val();
    }

    @Test(expected =  NullPointerException.class)
    public void testNextFloatNullNotBound3() throws Exception {
        Float f = null;
        Constants.M.floats().range(f, 10.0f).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatLesserUpperBound() throws Exception {
        Constants.M.floats().range(10.0f, 8.0f).val();
    }

    @Test
    public void testNextCorrectValues() throws Exception {
        float[] floats = { 1.0f, 5.0f, 10.0f, 15.0f, 20.52f };
        Set<Float> values = new HashSet<>(asList(toObject(floats)));
        loop(Constants.FLOATS_CYCLES,
                Constants.MOCKS,
                r -> r.floats().from(floats).val(),
                num -> assertTrue(values.contains(num)));
    }

    @Test
    public void testNextCorrectValues2() throws Exception {
        float[] floats = { 0.0f };
        loop(Constants.FLOATS_CYCLES,
                Constants.MOCKS,
                r -> r.floats().from(floats).val(),
                num -> assertTrue(num.equals(floats[0])));
    }

    @Test(expected = NullPointerException.class)
    public void testNextNullNotAlphabet() throws Exception {
        float[] alphabet = null;
        Constants.M.floats().from(alphabet).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextEmptyArrayNotAlphabet() throws Exception {
        Constants.M.floats().from(new float[]{}).val();
    }
}
