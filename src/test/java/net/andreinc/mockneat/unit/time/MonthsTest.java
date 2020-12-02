package net.andreinc.mockneat.unit.time;

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

import org.junit.Test;

import java.time.Month;
import java.util.EnumSet;
import java.util.Set;

import static net.andreinc.mockneat.Constants.DAYS_CYCLES;
import static net.andreinc.mockneat.Constants.MOCKS;
import static java.time.Month.DECEMBER;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class MonthsTest {

    @Test
    public void testMonths() {
        loop(DAYS_CYCLES, MOCKS, r ->
                assertTrue((r.months().val()) instanceof Month));
    }

    @Test
    public void testDaysInRange() {
        Set<Month> monthSet = EnumSet.of(OCTOBER, NOVEMBER);
        loop(DAYS_CYCLES,
                MOCKS,
                r -> r.months().range(OCTOBER, DECEMBER).val(),
                m -> assertTrue(monthSet.contains(m)));
    }
}
