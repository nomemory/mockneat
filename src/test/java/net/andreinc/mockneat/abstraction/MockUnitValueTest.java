package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.time.DayOfWeek;

import static java.time.DayOfWeek.TUESDAY;
import static net.andreinc.mockneat.abstraction.MockUnitValue.unit;
import static org.junit.Assert.*;

public class MockUnitValueTest {

    @Test
    public void testGet() {
        MockUnitInt m = Constants.M.ints().range(0, 5);
        MockUnitValue<Integer> muv = unit(m);

        Integer o = muv.get();
        assertNotNull(o);

        int i = o;
        assertTrue(0<=i && i < 5);
    }

    @Test
    public void testGetStr() {
        MockUnitDays m = Constants.M.days().before(TUESDAY);
        MockUnitValue<DayOfWeek> muv = unit(m);

        String monday = muv.getStr();
        assertEquals("MONDAY", monday);
    }
}
