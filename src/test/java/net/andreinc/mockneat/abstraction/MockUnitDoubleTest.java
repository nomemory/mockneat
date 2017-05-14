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

import java.util.Arrays;
import java.util.stream.DoubleStream;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class MockUnitDoubleTest {
    @Test
    public void testDoubleStream() throws Exception {
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.doubles().range(0.0, 10.0).doubleStream().val(),
                ds -> {
                    assertTrue(ds != null);
                    assertTrue(ds instanceof DoubleStream);
                    ds.limit(10).forEach(el -> {
                        assertTrue(0.0 <= el);
                        assertTrue(el < 10.0);
                    });
                }
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayPrimitiveWithNegativeSize() throws Exception {
        M.doubles().arrayPrimitive(-5).val();
    }

    @Test
    public void testArrayPrimitive() throws Exception {
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.doubles().range(0.0, 10.0).arrayPrimitive(10).val(),
                ad -> {
                    assertTrue(ad != null);
                    assertTrue(ad instanceof double[]);
                    assertTrue(ad.length == 10);
                    Arrays.stream(ad).forEach(el -> {
                        assertTrue(0.0 <= el);
                        assertTrue(el < 10.0);
                    });
                }
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayNegative() throws Exception {
        M.doubles().array(-10).val();
    }

    @Test
    public void testArray() throws Exception {
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.doubles().range(0.0, 10.0).array(100).val(),
                ad -> {
                    assertTrue(ad != null);
                    assertTrue(ad instanceof Double[]);
                    assertTrue(ad.length == 100);
                    Arrays.stream(ad).forEach(el ->
                            {
                                assertTrue(0.0 <= el);
                                assertTrue(el < 10.0);
                            });
                }
        );
    }
}
