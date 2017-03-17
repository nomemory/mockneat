package net.andreinc.mockneat.interfaces.mockunitgeneric;


import net.andreinc.mockneat.Constants;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MockUnitValMethodTest {

    @Test(expected = NullPointerException.class)
    public void testValFunctionNullFunction() throws Exception {
        Constants.M.ints().val(null);
    }


    @Test
    public void testValFunction() throws Exception {
        String zero = Constants.M.ints().range(0, 1).val(i -> i.toString());
        assertTrue("0".equals(zero));
    }
}
