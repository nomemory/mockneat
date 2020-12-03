package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MockUnitValMethodTest {

    @Test(expected = NullPointerException.class)
    public void testValFunctionNullFunction() {
        Constants.M.ints().val(null);
    }


    @Test
    public void testValFunction() {
        String zero = Constants.M.ints().range(0, 1).val(Object::toString);
        assertEquals("0", zero);
    }
}
