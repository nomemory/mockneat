package net.andreinc.mockneat.interfaces.mockunitgeneric;

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 28/02/2017.
 */
public class MockUnitGenericComposeTest {

    @Test(expected = NullPointerException.class)
    public void testConsumerNullConsumer() throws Exception {
        Constants.M.ints().range(0, 100).consume(null);
    }

    @Test
    public void testConsume1() throws Exception {
        final StringBuilder buff = new StringBuilder();
        Constants.M.intSeq().list(10).consume(l ->
                l.forEach(e -> buff.append(e)));
        assertTrue("0123456789"
                .equals(buff.toString()));
    }
}
