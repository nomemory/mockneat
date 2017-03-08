package com.mockneat.mock.interfaces.mockunitdouble;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.DoubleStream;

import static com.mockneat.mock.Constants.*;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;


/**
 * Created by andreinicolinciobanu on 07/03/2017.
 */
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
