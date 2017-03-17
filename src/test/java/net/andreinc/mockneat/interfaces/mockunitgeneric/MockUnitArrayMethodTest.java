package net.andreinc.mockneat.interfaces.mockunitgeneric;

import net.andreinc.mockneat.interfaces.models.ClassicPojo;
import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.util.Arrays;

import static net.andreinc.mockneat.types.enums.StringType.ALPHA_NUMERIC;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 07/03/2017.
 */
public class MockUnitArrayMethodTest {

    @Test(expected = NullPointerException.class)
    public void testArrayNullClass() throws Exception {
        Constants.M.constructor(ClassicPojo.class)
         .params(Constants.M.strings(), Constants.M.ints())
         .array(null, 10)
         .val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayNegativeSize() throws Exception {
        Constants.M.constructor(ClassicPojo.class)
         .params(Constants.M.strings(), Constants.M.ints())
         .array(ClassicPojo.class, -10)
         .val();
    }

    @Test
    public void testArray() throws Exception {
        loop(
            Constants.MOCK_CYCLES,
            Constants.MOCKS,
            m -> m.constructor(ClassicPojo.class)
                    .params(Constants.M.strings().type(ALPHA_NUMERIC),
                                 Constants.M.ints().range(0, 10))
                    .array(ClassicPojo.class, 10)
                    .val(),
            arr -> {
                assertTrue(arr.length == 10);
                Arrays.stream(arr)
                        .forEach(el -> {
                            assertTrue(el != null);
                            assertTrue(isAlphanumeric(el.getName()));
                            assertTrue(0<= el.getAge() && el.getAge() <= 10);
                        });
            }
        );
    }
}
