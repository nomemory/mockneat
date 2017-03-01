package com.mockneat.mock.unit.time;

import org.junit.Test;

import java.time.Month;
import java.util.EnumSet;
import java.util.Set;

import static com.mockneat.mock.Constants.DAYS_CYCLES;
import static com.mockneat.mock.Constants.MOCKS;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static java.time.Month.DECEMBER;
import static java.time.Month.NOVEMBER;
import static java.time.Month.OCTOBER;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 16/02/2017.
 */
public class MonthsTest {

    @Test
    public void testMonths() throws Exception {
        loop(DAYS_CYCLES, MOCKS, r ->
                assertTrue((r.months().val()) instanceof Month));
    }

    @Test
    public void testDaysInRange() throws Exception {
        Month lower = OCTOBER;
        Month upper = DECEMBER;
        Set<Month> monthSet = EnumSet.of(OCTOBER, NOVEMBER);
        loop(DAYS_CYCLES,
                MOCKS,
                r -> r.months().range(lower, upper).val(),
                m -> assertTrue(monthSet.contains(m)));
    }
}
