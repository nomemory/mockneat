package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.abstraction.models.ClassicPojo;
import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.util.Arrays;

import static net.andreinc.mockneat.types.enums.StringType.ALPHA_NUMERIC;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.junit.Assert.*;

public class MockUnitArrayMethodTest {

    @Test(expected = NullPointerException.class)
    public void testArrayNullClass() {
        Constants.M.constructor(ClassicPojo.class)
         .params(Constants.M.strings(), Constants.M.ints())
         .array(null, 10)
         .val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayNegativeSize() {
        Constants.M.constructor(ClassicPojo.class)
         .params(Constants.M.strings(), Constants.M.ints())
         .array(ClassicPojo.class, -10)
         .val();
    }

    @Test
    public void testArray() {
        loop(
            Constants.MOCK_CYCLES,
            Constants.MOCKS,
            m -> m.constructor(ClassicPojo.class)
                    .params(Constants.M.strings().type(ALPHA_NUMERIC),
                                 Constants.M.ints().range(0, 10))
                    .array(ClassicPojo.class, 10)
                    .val(),
            arr -> {
                assertEquals(10, arr.length);
                Arrays.stream(arr)
                        .forEach(el -> {
                            assertNotNull(el);
                            assertTrue(isAlphanumeric(el.getName()));
                            assertTrue(0<= el.getAge() && el.getAge() <= 10);
                        });
            }
        );
    }
}
