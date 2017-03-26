package net.andreinc.mockneat.interfaces.mockunitgeneric;

import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.interfaces.MockUnitDays;
import net.andreinc.mockneat.interfaces.MockUnitInt;
import net.andreinc.mockneat.interfaces.MockUnitValue;
import org.junit.Test;

import static java.time.DayOfWeek.TUESDAY;
import static net.andreinc.mockneat.interfaces.MockUnitValue.unit;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 05/03/2017.
 */
public class MockUnitValueTest {

    @Test
    public void testGet() throws Exception {
        MockUnitInt m = Constants.M.ints().range(0, 5);
        MockUnitValue muv = unit(m);

        Object o = muv.get();
        assertTrue(o instanceof Integer);

        Integer i = (Integer) o;
        assertTrue(0<=i && i < 5);
    }

    @Test
    public void testGetStr() throws Exception {
        MockUnitDays m = Constants.M.days().before(TUESDAY);
        MockUnitValue muv = unit(m);

        String monday = muv.getStr();
        assertTrue(monday.equals("MONDAY"));
    }
}
