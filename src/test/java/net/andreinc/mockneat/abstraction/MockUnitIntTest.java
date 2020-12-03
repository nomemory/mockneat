package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.util.Arrays;

import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.*;

public class MockUnitIntTest {

    @Test
    public void testIntStream() {
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints().range(0, 10).intStream().val(),
                ds -> {
                    assertNotNull(ds);
                    ds.limit(10).forEach(el -> {
                        assertTrue(0 <= el);
                        assertTrue(el < 10);
                    });
                }
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayPrimitiveWithNegativeSize() {
        Constants.M.ints().arrayPrimitive(-5).val();
    }

    @Test
    public void testArrayPrimitive() {
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints().range(0, 10).arrayPrimitive(10).val(),
                ai -> {
                    assertNotNull(ai);
                    assertEquals(10, ai.length);
                    Arrays.stream(ai).forEach(el -> {
                        assertTrue(0 <= el);
                        assertTrue(el < 10);
                    });
                }
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayNegative() {
        Constants.M.ints().array(-10).val();
    }
}
