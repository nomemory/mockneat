package net.andreinc.mockneat.unit.time;

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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MonthsTest {

    @Test
    public void testMonths() {
        loop(DAYS_CYCLES, MOCKS, r ->
                assertNotNull((r.months().val())));
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
