package com.mockneat.mock.unit.objects;

import com.mockneat.mock.unit.objects.model.MultipleConstructors;
import org.junit.Test;

import static com.mockneat.mock.Constants.*;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static com.mockneat.types.enums.StringType.*;
import static org.apache.commons.lang3.StringUtils.isAlpha;
import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 08/03/2017.
 */
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
