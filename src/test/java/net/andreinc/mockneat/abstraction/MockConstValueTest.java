package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.abstraction.models.ToString;
import org.junit.Test;

import static net.andreinc.mockneat.abstraction.MockConstValue.constant;
import static org.junit.Assert.assertEquals;

public class MockConstValueTest {

    @Test
    public void testGet() {
        String s = "test";
        MockConstValue<String> mco = constant(s);
        assertEquals("test", mco.get());
    }

    @Test
    public void testGetStr() {
        ToString s = new ToString();
        MockConstValue<ToString> mco = constant(s);
        assertEquals(mco.getStr(), ToString.CONST);
    }

    @Test
    public void testGetStrNull() {
        MockConstValue<Object> mco = constant(null);
        assertEquals("", mco.getStr());
    }
}
