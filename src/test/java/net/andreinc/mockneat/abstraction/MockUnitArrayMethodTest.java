package net.andreinc.mockneat.abstraction;

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
