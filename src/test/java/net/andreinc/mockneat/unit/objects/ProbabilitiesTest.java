package net.andreinc.mockneat.unit.objects;

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
import net.andreinc.mockneat.utils.LoopsUtils;
import org.junit.Assert;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.M;

public class ProbabilitiesTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNegative() throws Exception {
        M.probabilites(Integer.class)
                .add(-0.5, M.ints().bound(10))
                .add(1.5, M.ints().range(10, 20))
                .val();
    }

    @Test(expected = NullPointerException.class)
    public void testNull() throws Exception {
        M.probabilites(Integer.class)
                .add(null, M.ints().bound(10))
                .val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumNot0() throws Exception {
        M.probabilites(Integer.class)
                .add(0.2, M.fromInts(new int[]{1,2,3}))
                .add(0.5, M.fromInts(new int[]{4,5,6}))
                .val();
    }

    @Test
    public void testValues() {
        LoopsUtils.loop(
                Constants.PROBABILITIES_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.probabilites(Integer.class)
                                    .add(0.1, 1)
                                    .add(0.9, 2)
                                    .val(),
                val -> Assert.assertTrue(val.equals(2) || val.equals(1))
        );
    }

    @Test
    public void testValuesWIthMockUnits() {
        LoopsUtils.loop(
                Constants.PROBABILITIES_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.probabilites(String.class)
                                    .add(0.5, mockNeat.strings().size(10))
                                    .add(0.5, mockNeat.strings().size(15))
                                    .val(),
                val -> Assert.assertTrue(val.length()==10 ||
                                                    val.length() == 15)
        );
    }
}
