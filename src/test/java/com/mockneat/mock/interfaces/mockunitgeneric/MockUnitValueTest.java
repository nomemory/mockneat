package com.mockneat.mock.interfaces.mockunitgeneric;

import com.mockneat.mock.interfaces.MockUnitDays;
import com.mockneat.mock.interfaces.MockUnitInt;
import com.mockneat.mock.interfaces.MockUnitValue;
import org.junit.Test;

import static com.mockneat.mock.Constants.M;
import static java.time.DayOfWeek.TUESDAY;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 05/03/2017.
 */
public class MockUnitValueTest {

    @Test
    public void testGet() throws Exception {
        MockUnitInt m = M.ints().range(0, 5);
        MockUnitValue muv = new MockUnitValue(m);

        Object o = muv.get();
        assertTrue(o instanceof Integer);

        Integer i = (Integer) o;
        assertTrue(0<=i && i < 5);
    }

    @Test
    public void testGetStr() throws Exception {
        MockUnitDays m = M.days().before(TUESDAY);
        MockUnitValue muv = new MockUnitValue(m);

        String monday = muv.getStr();
        assertTrue(monday.equals("MONDAY"));
    }
}
