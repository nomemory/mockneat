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

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class MockUnitIntTest {

    @Test
    public void testIntStream() throws Exception {
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints().range(0, 10).intStream().val(),
                ds -> {
                    assertTrue(ds != null);
                    assertTrue(ds instanceof IntStream);
                    ds.limit(10).forEach(el -> {
                        assertTrue(0 <= el);
                        assertTrue(el < 10);
                    });
                }
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayPrimitiveWithNegativeSize() throws Exception {
        Constants.M.ints().arrayPrimitive(-5).val();
    }

    @Test
    public void testArrayPrimitive() throws Exception {
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints().range(0, 10).arrayPrimitive(10).val(),
                ai -> {
                    assertTrue(ai != null);
                    assertTrue(ai.length == 10);
                    assertTrue(ai instanceof int[]);
                    Arrays.stream(ai).forEach(el -> {
                        assertTrue(0 <= el);
                        assertTrue(el < 10);
                    });
                }
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayNegative() throws Exception {
        Constants.M.ints().array(-10).val();
    }
}
