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
    public void testDays() {
        loop(Constants.DAYS_CYCLES, Constants.MOCKS, r ->
                assertTrue((r.days().val()) instanceof DayOfWeek));
    }

    @Test
    public void testDaysInRange() {
        Set<DayOfWeek> dayOfWeekSet = EnumSet.of(TUESDAY, WEDNESDAY);
        loop(Constants.DAYS_CYCLES,
                Constants.MOCKS,
                r -> r.days().range(TUESDAY, THURSDAY).val(),
                d -> assertTrue(dayOfWeekSet.contains(d)));
    }

    @Test(expected = NullPointerException.class)
    public void testDaysInRangeNullLower() {
        Constants.M.days().range(null, MONDAY).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDaysInRangeNulLUpper() {
        Constants.M.days().range(MONDAY, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysRangeEqualBounds() {
        Constants.M.days().range(MONDAY, MONDAY).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysRangeIncorrectBounds() {
        Constants.M.days().range(SATURDAY, FRIDAY).val();
    }

    @Test
    public void testDaysInRangeClosed() {
        Set<DayOfWeek> dayOfWeekSet = EnumSet.of(TUESDAY, WEDNESDAY, THURSDAY);
        loop(Constants.DAYS_CYCLES,
                Constants.MOCKS,
                r -> r.days().rangeClosed(TUESDAY, THURSDAY).val(),
                d -> assertTrue(dayOfWeekSet.contains(d)));
    }

    @Test(expected = NullPointerException.class)
    public void testDaysInRangeClosedNullLower() {
        Constants.M.days().rangeClosed(null, MONDAY).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDaysInRangeClosedNulLUpper() {
        Constants.M.days().rangeClosed(MONDAY, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysRangeClosedEqualBounds() {
        Constants.M.days().rangeClosed(MONDAY, MONDAY).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysRangeClosedIncorrectBounds() {
        Constants.M.days().rangeClosed(SATURDAY, FRIDAY).val();
    }

    @Test
    public void testDaysBefore() {
        Set<DayOfWeek> set = EnumSet.of(MONDAY, TUESDAY, WEDNESDAY);
        loop(Constants.DAYS_CYCLES,
                Constants.MOCKS,
                r -> r.days().before(WEDNESDAY).val(),
                d -> assertTrue(set.contains(d)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysBeforeMonday() {
        Constants.M.days().before(MONDAY).val();
    }

    @Test
    public void testDaysBeforeTuesday() {
        Set<DayOfWeek> set = EnumSet.of(MONDAY);
        loop(Constants.DAYS_CYCLES,
                Constants.MOCKS,
                r -> r.days().before(TUESDAY).val(),
                d -> assertTrue(set.contains(d)));
    }

    @Test(expected = NullPointerException.class)
    public void testDaysBeforeNull() {
        DayOfWeek bound = null;
        Constants.M.days().before(bound).val();
    }

    @Test
    public void testDaysAfter() {
        Set<DayOfWeek> weekEnd = EnumSet.of(SUNDAY, SATURDAY);
        loop(Constants.DAYS_CYCLES,
                Constants.MOCKS,
                r -> r.days().after(FRIDAY).val(),
                d -> assertTrue(weekEnd.contains(d)));
    }

    @Test
    public void testDaysAfterSaturday() {
        Set<DayOfWeek> sunday = EnumSet.of(SUNDAY);
        loop(Constants.DAYS_CYCLES,
                Constants.MOCKS,
                r -> r.days().after(SATURDAY).val(),
                d -> assertTrue(sunday.contains(d)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysAfterSunday() {
        Constants.M.days().after(SUNDAY).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDaysAfterNull() {
        Constants.M.days().after(null).val();
    }
}
