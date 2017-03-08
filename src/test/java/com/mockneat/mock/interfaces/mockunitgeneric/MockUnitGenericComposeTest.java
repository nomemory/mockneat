package com.mockneat.mock.interfaces.mockunitgeneric;

import org.junit.Test;

import static com.mockneat.mock.Constants.M;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 28/02/2017.
 */
public class MockUnitGenericComposeTest {

    @Test(expected = NullPointerException.class)
    public void testConsumerNullConsumer() throws Exception {
        M.ints().range(0, 100).consume(null);
    }

    @Test
    public void testConsume1() throws Exception {
        final StringBuilder buff = new StringBuilder();
        M.intSeq().list(10).consume(l ->
                l.forEach(e -> buff.append(e)));
        assertTrue("0123456789"
                .equals(buff.toString()));
    }
}
