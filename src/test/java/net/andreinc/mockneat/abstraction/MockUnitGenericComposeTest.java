package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MockUnitGenericComposeTest {

    @Test(expected = NullPointerException.class)
    public void testConsumerNullConsumer() {
        Constants.M.ints().range(0, 100).consume(null);
    }

    @Test
    public void testConsume1() {
        final StringBuilder buff = new StringBuilder();
        Constants.M.intSeq().list(10).consume(l -> l.forEach(buff::append));
        assertEquals("0123456789", buff.toString());
    }
}
