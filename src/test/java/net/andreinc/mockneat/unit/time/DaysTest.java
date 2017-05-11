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

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.EnumSet;
import java.util.Set;

import static java.time.DayOfWeek.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class DaysTest {

    @Test
    public void testDays() throws Exception {
        loop(Constants.DAYS_CYCLES, Constants.MOCKS, r ->
                assertTrue((r.days().val()) instanceof DayOfWeek));
    }

    @Test
    public void testDaysInRange() throws Exception {
        DayOfWeek lower = TUESDAY;
        DayOfWeek upper = DayOfWeek.THURSDAY;
        Set<DayOfWeek> dayOfWeekSet = EnumSet.of(TUESDAY, WEDNESDAY);
        loop(Constants.DAYS_CYCLES,
                Constants.MOCKS,
                r -> r.days().range(lower, upper).val(),
                d -> assertTrue(dayOfWeekSet.contains(d)));
    }

    @Test(expected = NullPointerException.class)
    public void testDaysInRangeNullLower() throws Exception {
        Constants.M.days().range(null, MONDAY).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDaysInRangeNulLUpper() throws Exception {
        Constants.M.days().range(MONDAY, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysRangeEqualBounds() throws Exception {
        Constants.M.days().range(MONDAY, MONDAY).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysRangeIncorrectBounds() throws Exception {
        Constants.M.days().range(SATURDAY, FRIDAY).val();
    }

    @Test
    public void testDaysInRangeClosed() throws Exception {
        DayOfWeek lower = TUESDAY;
        DayOfWeek upper = DayOfWeek.THURSDAY;
        Set<DayOfWeek> dayOfWeekSet = EnumSet.of(TUESDAY, WEDNESDAY, THURSDAY);
        loop(Constants.DAYS_CYCLES,
                Constants.MOCKS,
                r -> r.days().rangeClosed(lower, upper).val(),
                d -> assertTrue(dayOfWeekSet.contains(d)));
    }

    @Test(expected = NullPointerException.class)
    public void testDaysInRangeClosedNullLower() throws Exception {
        Constants.M.days().rangeClosed(null, MONDAY).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDaysInRangeClosedNulLUpper() throws Exception {
        Constants.M.days().rangeClosed(MONDAY, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysRangeClosedEqualBounds() throws Exception {
        Constants.M.days().rangeClosed(MONDAY, MONDAY).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysRangeClosedIncorrectBounds() throws Exception {
        Constants.M.days().rangeClosed(SATURDAY, FRIDAY).val();
    }

    @Test
    public void testDaysBefore() throws Exception {
        DayOfWeek bound = WEDNESDAY;
        Set<DayOfWeek> set = EnumSet.of(MONDAY, TUESDAY, WEDNESDAY);
        loop(Constants.DAYS_CYCLES,
                Constants.MOCKS,
                r -> r.days().before(bound).val(),
                d -> assertTrue(set.contains(d)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysBeforeMonday() throws Exception {
        DayOfWeek bound = MONDAY;
        Constants.M.days().before(MONDAY).val();
    }

    @Test
    public void testDaysBeforeTuesday() throws Exception {
        DayOfWeek bound = TUESDAY;
        Set<DayOfWeek> set = EnumSet.of(MONDAY);
        loop(Constants.DAYS_CYCLES,
                Constants.MOCKS,
                r -> r.days().before(bound).val(),
                d -> assertTrue(set.contains(d)));
    }

    @Test(expected = NullPointerException.class)
    public void testDaysBeforeNull() throws Exception {
        DayOfWeek bound = null;
        Constants.M.days().before(bound).val();
    }

    @Test
    public void testDaysAfter() throws Exception {
        DayOfWeek after = FRIDAY;
        Set<DayOfWeek> weekEnd = EnumSet.of(SUNDAY, SATURDAY);
        loop(Constants.DAYS_CYCLES,
                Constants.MOCKS,
                r -> r.days().after(after).val(),
                d -> assertTrue(weekEnd.contains(d)));
    }

    @Test
    public void testDaysAfterSaturday() throws Exception {
        DayOfWeek after = SATURDAY;
        Set<DayOfWeek> sunday = EnumSet.of(SUNDAY);
        loop(Constants.DAYS_CYCLES,
                Constants.MOCKS,
                r -> r.days().after(after).val(),
                d -> assertTrue(sunday.contains(d)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysAfterSunday() throws Exception {
        Constants.M.days().after(SUNDAY).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDaysAfterNull() throws Exception {
        Constants.M.days().after(null).val();
    }
}
