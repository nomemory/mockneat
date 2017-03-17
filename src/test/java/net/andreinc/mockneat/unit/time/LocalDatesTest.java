package net.andreinc.mockneat.unit.time;

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.MAX;
import static java.time.LocalDate.MIN;
import static java.time.LocalDate.now;
import static net.andreinc.mockneat.unit.time.LocalDates.EPOCH_START;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 28/02/2017.
 */
public class LocalDatesTest {

    @Test
    public void testLocalDate() throws Exception {
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS, m -> m.localDates().val(), d -> {
            assertTrue(d.compareTo(EPOCH_START) >= 0);
            assertTrue(d.compareTo(now()) < 0);
        });
    }

    @Test
    public void testThisYear() throws Exception {
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS, m -> m.localDates().thisYear().val(), d ->
                assertTrue(d.getYear() == now().getYear()));
    }

    @Test
    public void testThisMonth() throws Exception {
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS, m -> m.localDates().thisMonth().val(), d -> {
            assertTrue(d.getYear() == now().getYear());
            assertTrue(d.getMonth() == now().getMonth());
        });
    }

    @Test(expected = NullPointerException.class)
    public void testBetweenNullLower() throws Exception {
        Constants.M.localDates().between(null, now()).val();
    }

    @Test(expected = NullPointerException.class)
    public void testBetweenNullUpper() throws Exception {
        Constants.M.localDates().between(now(), null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBetweenEqualLowerAndUpper() throws Exception {
        Constants.M.localDates().between(now(), now());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBetweenLowerBiggerThanUpper() throws Exception {
        LocalDate tomorrow = now().plusDays(1);
        Constants.M.localDates().between(tomorrow, now());
    }

    @Test
    public void testBetween() throws Exception {
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
    public void testBetweenSingleDate() throws Exception {
        LocalDate tomorrow = now().plusDays(1);
        loop(Constants.LOCAL_DATES_CYCLES,
                Constants.MOCKS,
                m -> m.localDates().between(now(), tomorrow).val(),
                d -> assertTrue(d.compareTo(now())==0));
    }

    @Test(expected = NullPointerException.class)
    public void testFutureNull() throws Exception {
        Constants.M.localDates().future(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFutureMaxDate() throws Exception {
        Constants.M.localDates().future(MAX).val();
    }

    @Test
    public void testFutureMaxMinus1Date() throws Exception {
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS,
                m -> m.localDates().future(MAX.minusDays(1)).val(),
                d -> assertTrue(d.compareTo(now())>0 && d.compareTo(MAX.minusDays(1))<=0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFuturePast() throws Exception {
        Constants.M.localDates().future(now().minusDays(5)).val();
    }

    @Test
    public void testFuture() throws Exception {
        LocalDate fiveDaysInFuture = now().plusDays(5);
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS, m -> m.localDates().future(fiveDaysInFuture).val(),
                d -> assertTrue(d.compareTo(now())>0 && d.compareTo(fiveDaysInFuture)<=0));
    }

    @Test(expected = NullPointerException.class)
    public void testPastNull() throws Exception {
        Constants.M.localDates().past(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPastMinDate() throws Exception {
        Constants.M.localDates().past(MIN).val();
    }

    @Test
    public void testPastMinPlus1Date() throws Exception {
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS,
                    m -> m.localDates().past(MIN.plusDays(1)).val(),
                    d -> assertTrue(d.compareTo(now())<0 &&
                                    d.compareTo(MIN.plusDays(1))>=0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPastFuture() throws Exception {
        LocalDate future = now().plusDays(5);
        Constants.M.localDates().past(future).val();
    }

    @Test
    public void testPast() throws Exception {
        LocalDate fiveDaysInThePast = now().minusDays(5);
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS,
                    m -> m.localDates().past(fiveDaysInThePast).val(),
                    d -> assertTrue(d.compareTo(fiveDaysInThePast)>=0 && d.compareTo(now())<=0));
    }
}
