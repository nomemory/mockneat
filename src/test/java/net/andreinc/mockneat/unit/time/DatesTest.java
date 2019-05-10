package net.andreinc.mockneat.unit.time;

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static net.andreinc.mockneat.Constants.M;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class DatesTest {

    private Date EPOCH_START = new Date(0);
    private Date MAX = new GregorianCalendar(999_999_999, 12, 3).getTime();

    private static Date now() {
        return new Date();
    }

    private static Date tommorow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    private static Date yesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    private static Date lastCentury() {
        return new GregorianCalendar(
                M.ints().rangeClosed(1900, 1999).get(), // Year
                M.ints().range(0, 12).get(), // Month
                M.ints().range(1, 10).get() // Days
        )
                .getTime();
    }

    private static Date nextCentury() {
        return new GregorianCalendar(
                M.ints().rangeClosed(2100, 2199).get(), // Year
                M.ints().range(0, 12).get(), // Month
                M.ints().range(1, 10).get() // Days
        )
                .getTime();
    }

    @Test
    public void testLocalDate() throws Exception {
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS, m -> m.dates().val(), d -> {
            assertTrue(d.compareTo(EPOCH_START) >= 0);
            assertTrue(d.compareTo(now()) < 0);
        });
    }

    @Test
    public void testThisYear() throws Exception {
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS, m -> m.dates().thisYear().val(), d ->
                assertTrue(d.getYear() == now().getYear()));
    }

    @Test
    public void testThisMonth() throws Exception {
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS, m -> m.dates().thisMonth().val(), d -> {
            assertTrue(d.getYear() == now().getYear());
            assertTrue(d.getMonth() == now().getMonth());
        });
    }

    @Test(expected = NullPointerException.class)
    public void testBetweenNullLower() throws Exception {
        M.dates().between(null, now()).val();
    }

    @Test(expected = NullPointerException.class)
    public void testBetweenNullUpper() throws Exception {
        M.dates().between(now(), null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBetweenEqualLowerAndUpper() throws Exception {
        M.dates().between(now(), now());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBetweenLowerBiggerThanUpper() throws Exception {
        M.dates().between(tommorow(), now());
    }

    @Test
    public void testBetween() throws Exception {
        Date lastCentury = lastCentury();
        Date nextCentury = nextCentury();
        loop(Constants.LOCAL_DATES_CYCLES,
                Constants.MOCKS,
                m -> m.dates().between(lastCentury, nextCentury).val(),
                d -> {
                    assertTrue(d.compareTo(lastCentury) >= 0);
                    assertTrue(d.compareTo(nextCentury) < 0);
                });
    }

    @Test(expected = NullPointerException.class)
    public void testFutureNull() throws Exception {
        M.dates().future(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFutureMaxDate() throws Exception {
        M.dates().future(MAX).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFuturePast() throws Exception {
        M.dates().future(yesterday()).val();
    }

    @Test
    public void testFuture() throws Exception {
        Date tommorow = tommorow();
        loop(Constants.LOCAL_DATES_CYCLES, Constants.MOCKS, m -> m.dates().future(tommorow).val(),
                d -> assertTrue(d.compareTo(now()) > 0 && d.compareTo(tommorow) <= 0));
    }

    @Test(expected = NullPointerException.class)
    public void testPastNull() throws Exception {
        M.dates().past(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPastMinDate() throws Exception {
        M.dates().past(EPOCH_START).val();
    }
}
