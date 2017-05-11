package net.andreinc.mockneat.interfaces;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.interfaces.models.ClassicPojo;
import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.util.Arrays;

import static net.andreinc.mockneat.types.enums.StringType.ALPHA_NUMERIC;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.junit.Assert.assertTrue;

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
