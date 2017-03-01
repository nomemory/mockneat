package com.mockneat.mock.unit.time;

import org.junit.Test;

import java.time.DayOfWeek;
import java.util.EnumSet;
import java.util.Set;

import static com.mockneat.mock.Constants.DAYS_CYCLES;
import static com.mockneat.mock.Constants.M;
import static com.mockneat.mock.Constants.MOCKS;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static java.time.DayOfWeek.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 16/02/2017.
 */
public class DaysTest {

    @Test
    public void testDays() throws Exception {
        loop(DAYS_CYCLES, MOCKS, r ->
                assertTrue((r.days().val()) instanceof DayOfWeek));
    }

    @Test
    public void testDaysInRange() throws Exception {
        DayOfWeek lower = TUESDAY;
        DayOfWeek upper = DayOfWeek.THURSDAY;
        Set<DayOfWeek> dayOfWeekSet = EnumSet.of(TUESDAY, WEDNESDAY);
        loop(DAYS_CYCLES,
                MOCKS,
                r -> r.days().range(lower, upper).val(),
                d -> assertTrue(dayOfWeekSet.contains(d)));
    }

    @Test(expected = NullPointerException.class)
    public void testDaysInRangeNullLower() throws Exception {
        M.days().range(null, MONDAY).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDaysInRangeNulLUpper() throws Exception {
        M.days().range(MONDAY, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysRangeEqualBounds() throws Exception {
        M.days().range(MONDAY, MONDAY).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysRangeIncorrectBounds() throws Exception {
        M.days().range(SATURDAY, FRIDAY).val();
    }

    @Test
    public void testDaysInRangeClosed() throws Exception {
        DayOfWeek lower = TUESDAY;
        DayOfWeek upper = DayOfWeek.THURSDAY;
        Set<DayOfWeek> dayOfWeekSet = EnumSet.of(TUESDAY, WEDNESDAY, THURSDAY);
        loop(DAYS_CYCLES,
                MOCKS,
                r -> r.days().rangeClosed(lower, upper).val(),
                d -> assertTrue(dayOfWeekSet.contains(d)));
    }

    @Test(expected = NullPointerException.class)
    public void testDaysInRangeClosedNullLower() throws Exception {
        M.days().rangeClosed(null, MONDAY).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDaysInRangeClosedNulLUpper() throws Exception {
        M.days().rangeClosed(MONDAY, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysRangeClosedEqualBounds() throws Exception {
        M.days().rangeClosed(MONDAY, MONDAY).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysRangeClosedIncorrectBounds() throws Exception {
        M.days().rangeClosed(SATURDAY, FRIDAY).val();
    }

    @Test
    public void testDaysBefore() throws Exception {
        DayOfWeek bound = WEDNESDAY;
        Set<DayOfWeek> set = EnumSet.of(MONDAY, TUESDAY, WEDNESDAY);
        loop(DAYS_CYCLES,
                MOCKS,
                r -> r.days().before(bound).val(),
                d -> assertTrue(set.contains(d)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysBeforeMonday() throws Exception {
        DayOfWeek bound = MONDAY;
        M.days().before(MONDAY).val();
    }

    @Test
    public void testDaysBeforeTuesday() throws Exception {
        DayOfWeek bound = TUESDAY;
        Set<DayOfWeek> set = EnumSet.of(MONDAY);
        loop(DAYS_CYCLES,
                MOCKS,
                r -> r.days().before(bound).val(),
                d -> assertTrue(set.contains(d)));
    }

    @Test(expected = NullPointerException.class)
    public void testDaysBeforeNull() throws Exception {
        DayOfWeek bound = null;
        M.days().before(bound).val();
    }

    @Test
    public void testDaysAfter() throws Exception {
        DayOfWeek after = FRIDAY;
        Set<DayOfWeek> weekEnd = EnumSet.of(SUNDAY, SATURDAY);
        loop(DAYS_CYCLES,
                MOCKS,
                r -> r.days().after(after).val(),
                d -> assertTrue(weekEnd.contains(d)));
    }

    @Test
    public void testDaysAfterSaturday() throws Exception {
        DayOfWeek after = SATURDAY;
        Set<DayOfWeek> sunday = EnumSet.of(SUNDAY);
        loop(DAYS_CYCLES,
                MOCKS,
                r -> r.days().after(after).val(),
                d -> assertTrue(sunday.contains(d)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysAfterSunday() throws Exception {
        M.days().after(SUNDAY).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDaysAfterNull() throws Exception {
        M.days().after(null).val();
    }
}
