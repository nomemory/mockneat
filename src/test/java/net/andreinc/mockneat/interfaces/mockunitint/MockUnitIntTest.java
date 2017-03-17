package net.andreinc.mockneat.interfaces.mockunitint;

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 07/03/2017.
 */
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
