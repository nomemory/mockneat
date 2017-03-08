package com.mockneat.mock.interfaces.mockunitgeneric;

import com.mockneat.mock.interfaces.models.ClassicPojo;
import org.junit.Test;

import java.util.Arrays;

import static com.mockneat.mock.Constants.*;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static com.mockneat.types.enums.StringType.ALPHA_NUMERIC;
import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 07/03/2017.
 */
public class MockUnitArrayMethodTest {

    @Test(expected = NullPointerException.class)
    public void testArrayNullClass() throws Exception {
        M.constructor(ClassicPojo.class)
         .params(M.strings(), M.ints())
         .array(null, 10)
         .val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayNegativeSize() throws Exception {
        M.constructor(ClassicPojo.class)
         .params(M.strings(), M.ints())
         .array(ClassicPojo.class, -10)
         .val();
    }

    @Test
    public void testArray() throws Exception {
        loop(
            MOCK_CYCLES,
            MOCKS,
            m -> m.constructor(ClassicPojo.class)
                    .params(M.strings().type(ALPHA_NUMERIC),
                                 M.ints().range(0, 10))
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
