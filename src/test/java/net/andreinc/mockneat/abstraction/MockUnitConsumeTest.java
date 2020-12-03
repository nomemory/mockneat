package net.andreinc.mockneat.abstraction;

import org.junit.Test;

import static org.junit.Assert.assertNull;

import static net.andreinc.mockneat.Constants.M;

public class MockUnitConsumeTest {

    @Test(expected = NullPointerException.class)
    public void testConsumeNulLConsumer() {
        M.ints().range(0, 100).consume(null);
    }

    @Test(expected = NullPointerException.class)
    public void testConsumeWithBiConsumerNullBiConsumer() {
        M.ints().range(0, 100).consume(10, null);
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
        M.from(new String[]{ null }).consume(10, (i, val) -> assertNull(val));
    }
}
