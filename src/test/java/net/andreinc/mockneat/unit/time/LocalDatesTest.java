package net.andreinc.mockneat.unit.time;

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.MAX;
import static java.time.LocalDate.MIN;
import static java.time.LocalDate.now;
import static net.andreinc.mockneat.unit.time.LocalDates.EPOCH_START;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.*;

public class LocalDatesTest {

    @Test
    public void testLocalDate() {
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS, m -> m.localDates().val(), d -> {
            assertTrue(d.compareTo(EPOCH_START) >= 0);
            assertTrue(d.compareTo(now()) < 0);
        });
    }

    @Test
    public void testThisYear() {
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS, m -> m.localDates().thisYear().val(), d ->
                assertEquals(d.getYear(), now().getYear()));
    }

    @Test
    public void testThisMonth() {
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS, m -> m.localDates().thisMonth().val(), d -> {
            assertEquals(d.getYear(), now().getYear());
            assertSame(d.getMonth(), now().getMonth());
        });
    }

    @Test(expected = NullPointerException.class)
    public void testBetweenNullLower() {
        Constants.M.localDates().between(null, now()).val();
    }

    @Test(expected = NullPointerException.class)
    public void testBetweenNullUpper() {
        Constants.M.localDates().between(now(), null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBetweenEqualLowerAndUpper() {
        Constants.M.localDates().between(now(), now());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBetweenLowerBiggerThanUpper() {
        LocalDate tomorrow = now().plusDays(1);
        Constants.M.localDates().between(tomorrow, now());
    }

    @Test
    public void testBetween() {
        LocalDate lastCentury = LocalDate.ofYearDay(1900, 100);
        LocalDate nextCentury = LocalDate.ofYearDay(2150, 100);
        loop(Constants.LOCAL_DATES_CYCLES,
                Constants.MOCKS,
                m -> m.localDates().between(lastCentury, nextCentury).val(),
                d -> {
                    assertTrue(d.compareTo(lastCentury)>=0);
                    assertTrue(d.compareTo(nextCentury)<0);
                });
    }

    @Test
    public void testBetweenSingleDate() {
        LocalDate tomorrow = now().plusDays(1);
        loop(Constants.LOCAL_DATES_CYCLES,
                Constants.MOCKS,
                m -> m.localDates().between(now(), tomorrow).val(),
                d -> assertEquals(0, d.compareTo(now())));
    }

    @Test(expected = NullPointerException.class)
    public void testFutureNull() {
        Constants.M.localDates().future(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFutureMaxDate() {
        Constants.M.localDates().future(MAX).val();
    }

    @Test
    public void testFutureMaxMinus1Date() {
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS,
                m -> m.localDates().future(MAX.minusDays(1)).val(),
                d -> assertTrue(d.compareTo(now())>0 && d.compareTo(MAX.minusDays(1))<=0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFuturePast() {
        Constants.M.localDates().future(now().minusDays(5)).val();
    }

    @Test
    public void testFuture() {
        LocalDate fiveDaysInFuture = now().plusDays(5);
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS, m -> m.localDates().future(fiveDaysInFuture).val(),
                d -> assertTrue(d.compareTo(now())>0 && d.compareTo(fiveDaysInFuture)<=0));
    }

    @Test(expected = NullPointerException.class)
    public void testPastNull() {
        Constants.M.localDates().past(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPastMinDate() {
        Constants.M.localDates().past(MIN).val();
    }

    @Test
    public void testPastMinPlus1Date() {
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS,
                    m -> m.localDates().past(MIN.plusDays(1)).val(),
                    d -> assertTrue(d.compareTo(now())<0 &&
                                    d.compareTo(MIN.plusDays(1))>=0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPastFuture() {
        LocalDate future = now().plusDays(5);
        Constants.M.localDates().past(future).val();
    }

    @Test
    public void testPast() {
        LocalDate fiveDaysInThePast = now().minusDays(5);
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS,
                    m -> m.localDates().past(fiveDaysInThePast).val(),
                    d -> assertTrue(d.compareTo(fiveDaysInThePast)>=0 && d.compareTo(now())<=0));
    }
}
