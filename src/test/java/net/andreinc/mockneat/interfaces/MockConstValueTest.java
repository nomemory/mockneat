package net.andreinc.mockneat.interfaces;

import net.andreinc.mockneat.interfaces.models.ToString;
import org.junit.Test;

import static net.andreinc.mockneat.interfaces.MockConstValue.val;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 05/03/2017.
 */
public class MockConstValueTest {

    @Test
    public void testGet() throws Exception {
        String s = "test";
        MockConstValue mco = val(s);
        assertTrue(mco.get() instanceof String);
        assertTrue("test".equals(mco.get()));
    }

    @Test
    public void testGetStr() throws Exception {
        ToString s = new ToString();
        MockConstValue mco = val(s);
        assertTrue(mco.getStr() instanceof String);
        assertTrue(mco.getStr().equals(ToString.CONST));
    }

    @Test
    public void testGetStrNull() throws Exception {
        MockConstValue mco = val(null);
        assertTrue(mco.getStr().equals(""));
    }
}
