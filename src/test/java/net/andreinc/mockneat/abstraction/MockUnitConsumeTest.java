package net.andreinc.mockneat.abstraction;

import org.junit.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import static org.junit.Assert.assertTrue;

import static net.andreinc.mockneat.Constants.M;

public class MockUnitConsumeTest {

    @Test(expected = NullPointerException.class)
    public void testConsumeNulLConsumer() {
        Consumer<Integer> consumer = null;
        M.ints().range(0, 100).consume(consumer);
    }

    @Test(expected = NullPointerException.class)
    public void testConsumeWithBiConsumerNullBiConsumer() {
        BiConsumer<Integer, Integer> biConsumer = null;
        M.ints().range(0, 100).consume(10, biConsumer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBiConsumerNegativeTimes() {
        M.ints().range(0, 100).consume(-1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBiConsumerZeroTimes() {
        M.ints().range(0, 100).consume(0, null);
    }

    @Test
    public void testBiConsumer() {
        M.from(new String[]{ null }).consume(10, (i, val) -> assertTrue(val == null));
    }
}
