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

import static net.andreinc.mockneat.Constants.DOUBLES_CYCLES;
import static net.andreinc.mockneat.Constants.MOCKS;
import static java.util.Arrays.asList;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.ArrayUtils.toObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DoublesTest {

    @Test
    public void testNextDoubleInCorrectRange() {
        loop(DOUBLES_CYCLES,
                MOCKS,
                r -> r.doubles().val(),
                n -> assertTrue(n >= 0.0 && n < 1.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleZeroNotBound() {
        Constants.M.doubles().bound(0.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNegativeNotBound() {
        Constants.M.doubles().bound(-5.0).val();
    }

    @Test
    public void testNextDoubleInCorrectRange2() {
        final double bound = 0.01;
        loop(DOUBLES_CYCLES,
                MOCKS,
                r -> r.doubles().bound(bound).val(),
                n -> assertTrue(n >= 0.0 && n < bound));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound() {
        Constants.M.doubles().bound(Double.NaN).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextDoubleNullNotBound() {
        Double bound = null;
        Constants.M.doubles().bound(bound).val();
    }

    @Test
    public void testNextDoubleInCorrectRange3() {
        Double lowerBound = 1.1987;
        double upperBound = Double.MAX_VALUE;

        loop(DOUBLES_CYCLES,
                MOCKS,
                r -> r.doubles().range(lowerBound, upperBound).val(),
                n -> assertTrue(n >= lowerBound && n < upperBound));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleBoundsNotEqual() {
        Constants.M.doubles().range(0.01, 0.01).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound2() {
        Constants.M.doubles().range(Double.NaN, 10.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound3() {
        Constants.M.doubles().range(10.0, Double.NaN).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleInfinityNotBound2() {
        Constants.M.doubles().range(Double.POSITIVE_INFINITY, 10.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleLesserUpperBound() {
        Constants.M.doubles().range(10.0, 8.0).val();
    }

    @Test
    public void testNextDoubleCorrectValues() {
        double[] doubles = { 1.0, 5.0, 10.0, 15.0, 20.52 };
        Set<Double> values = new HashSet<>(asList(toObject(doubles)));

        loop(DOUBLES_CYCLES,
                MOCKS,
                r -> r.doubles().from(doubles).val(),
                num -> assertTrue(values.contains(num)));
    }

    @Test
    public void testnNextDoubleCorrectValues2() {
        double[] doubles = { 0.0 };
        loop(DOUBLES_CYCLES,
                MOCKS,
                r -> r.doubles().from(doubles).val(),
                num -> assertEquals(num, doubles[0], 0.0));
    }

    @Test(expected = NullPointerException.class)
    public void testNextDoubleNullNotAlphabet() {
        double[] alphabet = null;
        Constants.M.doubles().from(alphabet).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleEmptyArrayNotAlphabet() {
        Constants.M.doubles().from(new double[]{}).val();
    }
}
