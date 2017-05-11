package net.andreinc.mockneat.unit.objects;

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

import net.andreinc.mockneat.unit.objects.model.MultipleConstructors;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.types.enums.StringType.ALPHA_NUMERIC;
import static net.andreinc.mockneat.types.enums.StringType.LETTERS;
import static net.andreinc.mockneat.types.enums.StringType.NUMBERS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.StringUtils.isAlpha;
import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    @Test(expected = NullPointerException.class)
    public void testConstructNullClass() throws Exception {
        M.constructor(null).params("A").val();
    }

    @Test(expected = NullPointerException.class)
    public void testConstructNullParams() throws Exception {
        Object[] params = null;
        M.constructor(MultipleConstructors.class).params(params).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructNonExistentConstruct() {
        M.constructor(MultipleConstructors.class).params(1,2,3).val();
    }

    @Test
    public void testConstruct1() throws Exception {
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.constructor(MultipleConstructors.class)
                        .params(
                                "X",
                                "Y",
                                "Z",
                                "A"
                        ).val(),
                mc -> {
                    assertTrue("X".equals(mc.getX()));
                    assertTrue("Y".equals(mc.getY()));
                    assertTrue("Z".equals(mc.getZ()));
                    assertTrue("A".equals(mc.getA()));
                }
        );
    }

    @Test
    public void testConstruct2() throws Exception {
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.constructor(MultipleConstructors.class).params(
                        M.strings().size(5).type(NUMBERS),
                        M.strings().size(6).type(ALPHA_NUMERIC),
                        M.strings().size(7).type(LETTERS)
                ).val(),
                s -> {
                    String x = s.getX();
                    String y = s.getY();
                    String z = s.getZ();
                    assertTrue(x.length()==5);
                    assertTrue(isNumeric(x));
                    assertTrue(y.length()==6);
                    assertTrue(isAlphanumeric(y));
                    assertTrue(z.length()==7);
                    assertTrue(isAlpha(z));
                }
        );
    }
}
