package com.mockneat.mock.interfaces.mockunitgeneric;


import org.junit.Test;

import static com.mockneat.mock.Constants.M;
import static org.junit.Assert.assertTrue;

public class MockUnitValMethodTest {

    @Test(expected = NullPointerException.class)
    public void testValFunctionNullFunction() throws Exception {
        M.ints().val(null);
    }


    @Test
    public void testValFunction() throws Exception {
        String zero = M.ints().range(0, 1).val(i -> i.toString());
        assertTrue("0".equals(zero));
    }
}
