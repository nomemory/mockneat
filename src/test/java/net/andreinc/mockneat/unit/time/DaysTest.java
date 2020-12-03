package net.andreinc.mockneat.unit.time;

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.EnumSet;
import java.util.Set;

import static java.time.DayOfWeek.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DaysTest {

    @Test
    public void testDays() {
        loop(Constants.DAYS_CYCLES, Constants.MOCKS, r ->
                assertNotNull((r.days().val())));
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
        Constants.M.days().before(null).val();
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
