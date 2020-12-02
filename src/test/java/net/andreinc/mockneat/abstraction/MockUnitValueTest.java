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

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import static java.time.DayOfWeek.TUESDAY;
import static net.andreinc.mockneat.abstraction.MockUnitValue.unit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MockUnitValueTest {

    @Test
    public void testGet() {
        MockUnitInt m = Constants.M.ints().range(0, 5);
        MockUnitValue muv = unit(m);

        Object o = muv.get();
        assertTrue(o instanceof Integer);

        int i = (Integer) o;
        assertTrue(0<=i && i < 5);
    }

    @Test
    public void testGetStr() {
        MockUnitDays m = Constants.M.days().before(TUESDAY);
        MockUnitValue muv = unit(m);

        String monday = muv.getStr();
        assertEquals("MONDAY", monday);
    }
}
