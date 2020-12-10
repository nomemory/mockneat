package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MockUnitValAndGetMethodTest {

    @Test(expected = NullPointerException.class)
    public void testValFunctionNullFunction() {
        Constants.M.ints().val(null);
    }

    @Test(expected = NullPointerException.class)
    public void testGetFunctionNullFunction() {
        Constants.M.ints().get(null);
    }


    @Test
    public void testValFunction() {
        String zero = Constants.M.ints().range(0, 1).val(Object::toString);
        assertEquals("0", zero);
    }

    @Test
    public void getGetFunction() {
        String zero = Constants.M.ints().range(0, 1).val(Object::toString);
        assertEquals("0", zero);
    }
}
