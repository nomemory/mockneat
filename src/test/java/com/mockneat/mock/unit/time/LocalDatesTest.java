package com.mockneat.mock.unit.time;

import org.junit.Test;

import java.time.LocalDate;

import static com.mockneat.mock.Constants.*;
import static com.mockneat.mock.unit.time.LocalDates.EPOCH_START;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static java.time.LocalDate.now;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 28/02/2017.
 */
public class LocalDatesTest {

    @Test
    public void testLocalDate() throws Exception {
        loop(LOCAL_DATES_CYCLES, RANDS, m -> m.localDates().val(), d -> {
            assertTrue(d.compareTo(EPOCH_START) >= 0);
            assertTrue(d.compareTo(now()) < 0);
        });
    }

    @Test
    public void testThisYear() throws Exception {
        loop(LOCAL_DATES_CYCLES, RANDS, m -> m.localDates().thisYear().val(), d ->
                assertTrue(d.getYear() == now().getYear()));
    }

    @Test
    public void testThisMonth() throws Exception {
        loop(LOCAL_DATES_CYCLES, RANDS, m -> m.localDates().thisMonth().val(), d -> {
            assertTrue(d.getYear() == now().getYear());
            assertTrue(d.getMonth() == now().getMonth());
        });
    }

    @Test(expected = NullPointerException.class)
    public void testBetweenNullLower() throws Exception {
        RAND.localDates().between(null, now()).val();
    }

    @Test(expected = NullPointerException.class)
    public void testBetweenNullUpper() throws Exception {
        RAND.localDates().between(now(), null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBetweenEqualLowerAndUpper() throws Exception {
        RAND.localDates().between(now(), now());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBetweenLowerBiggerThanUpper() throws Exception {
        LocalDate tomorrow = now().plusDays(1);
        RAND.localDates().between(tomorrow, now());
    }

    @Test
    public void testBetween() throws Exception {
        LocalDate lastCentury = LocalDate.ofYearDay(1900, 100);
        LocalDate nextCentury = LocalDate.ofYearDay(2150, 100);
        loop(LOCAL_DATES_CYCLES,
                RANDS,
                m -> m.localDates().between(lastCentury, nextCentury).val(),
                d -> {
                    assertTrue(d.compareTo(lastCentury)>=0);
                    assertTrue(d.compareTo(nextCentury)<0);
                });
    }
}
