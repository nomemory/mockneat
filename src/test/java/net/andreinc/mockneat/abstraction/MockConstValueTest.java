package net.andreinc.mockneat.abstraction;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.abstraction.models.ToString;
import org.junit.Test;

import static net.andreinc.mockneat.abstraction.MockConstValue.constant;
import static org.junit.Assert.assertTrue;

public class MockConstValueTest {

    @Test
    public void testGet() throws Exception {
        String s = "test";
        MockConstValue mco = constant(s);
        assertTrue(mco.get() instanceof String);
        assertTrue("test".equals(mco.get()));
    }

    @Test
    public void testGetStr() throws Exception {
        ToString s = new ToString();
        MockConstValue mco = constant(s);
        assertTrue(mco.getStr().equals(ToString.CONST));
    }

    @Test
    public void testGetStrNull() throws Exception {
        MockConstValue mco = constant(null);
        assertTrue(mco.getStr().equals(""));
    }
}
