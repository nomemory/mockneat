package net.andreinc.mockneat.abstraction;

import org.junit.Test;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertEquals;

public class MockUnitFilterMethodTest {

    @Test(expected = NullPointerException.class)
    public void testFilterNullFunc() {
        M.ints().filter(null);
    }

    @Test(expected = NullPointerException.class)
    public void testDistinctByNullFunc() {
        M.ints().distinctBy(null);
    }

    @Test
    public void testFilterEven() {
        loop(MOCK_CYCLES,
                MOCKS,
                m -> m.ints().filter(it -> it % 2 == 0).val(),
                v -> assertEquals(0, v % 2)
        );
    }

    @Test
    public void testDistinct() {
        loop(MOCK_CYCLES,
                MOCKS,
                m -> m.ints().rangeClosed(1, 10).distinct().set(10).val(),
                v -> assertEquals(10, v.size())
        );
    }

}
