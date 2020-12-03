package net.andreinc.mockneat.abstraction;

import org.junit.Test;

import java.util.Arrays;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.*;

public class MockUnitDoubleTest {
    @Test
    public void testDoubleStream() {
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.doubles().range(0.0, 10.0).doubleStream().val(),
                ds -> {
                    assertNotNull(ds);
                    ds.limit(10).forEach(el -> {
                        assertTrue(0.0 <= el);
                        assertTrue(el < 10.0);
                    });
                }
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayPrimitiveWithNegativeSize() {
        M.doubles().arrayPrimitive(-5).val();
    }

    @Test
    public void testArrayPrimitive() {
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.doubles().range(0.0, 10.0).arrayPrimitive(10).val(),
                ad -> {
                    assertNotNull(ad);
                    assertEquals(10, ad.length);
                    Arrays.stream(ad).forEach(el -> {
                        assertTrue(0.0 <= el);
                        assertTrue(el < 10.0);
                    });
                }
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayNegative() {
        M.doubles().array(-10).val();
    }

    @Test
    public void testArray() {
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.doubles().range(0.0, 10.0).array(100).val(),
                ad -> {
                    assertNotNull(ad);
                    assertEquals(100, ad.length);
                    Arrays.stream(ad).forEach(el ->
                            {
                                assertTrue(0.0 <= el);
                                assertTrue(el < 10.0);
                            });
                }
        );
    }
}
