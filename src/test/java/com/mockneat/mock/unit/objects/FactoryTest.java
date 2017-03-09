package com.mockneat.mock.unit.objects;

import com.mockneat.mock.unit.objects.model.FactoryMethods;
import com.mockneat.mock.utils.LoopsUtils;
import com.mockneat.types.enums.StringType;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static com.mockneat.mock.Constants.M;
import static com.mockneat.mock.Constants.MOCKS;
import static com.mockneat.mock.Constants.OBJS_CYCLES;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 08/03/2017.
 */
public class FactoryTest {

    @Test(expected = NullPointerException.class)
    public void testFactoryNullClass() throws Exception {
        M.factory(null, String.class).method("some").params("abc").val();
    }

    @Test(expected = NullPointerException.class)
    public void testFactorNullFactoryClass() throws Exception {
        M.factory(String.class, null).method("some").params("abc").val();
    }

    @Test(expected = NullPointerException.class)
    public void testFactoryForgetMethod() throws Exception {
        M.factory(StringBuilder.class, FactoryMethods.class)
                .params("ABC")
                .val();
    }

    @Test(expected = NullPointerException.class)
    public void testFactoryNullMethod() throws Exception {
        M.factory(StringBuilder.class, FactoryMethods.class)
                .method(null)
                .params("ABC")
                .val();
    }

    @Test(expected = NullPointerException.class)
    public void testFactoryForgetParams() throws Exception {
        M.factory(StringBuilder.class, FactoryMethods.class)
                .method("buffBuilder")
                .val();
    }

    @Test(expected = NullPointerException.class)
    public void testFactoryNullParams() throws Exception {
        Object[] params = null;
        M.factory(StringBuilder.class, FactoryMethods.class)
                .method("buffBuilder")
                .params(params)
                .val();
    }

    @Test(expected = ClassCastException.class)
    public void testFactoryBuilderClassCastException() throws Exception {
        String val = M.factory(String.class, FactoryMethods.class)
                      .method("buffBuilder")
                      .params("ABC")
                      .val();
        System.out.println(val);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactoryBuilderInvalidMethod() throws Exception {
        M.factory(StringBuilder.class, FactoryMethods.class)
                      .method("xxx")
                      .params("ABC")
                      .val();
    }

    @Test
    public void testFactoryBuilderConstantParam() throws Exception {
        StringBuilder buff = M.factory(StringBuilder.class, FactoryMethods.class)
                                .method("buffBuilder")
                                .params("ABC")
                                .val();
        assertTrue(buff.toString().equals("ABC"));
    }

    @Test
    public void testFactoryBuilderMockParam() throws Exception {
        LoopsUtils.loop(
                OBJS_CYCLES,
                MOCKS,
                m -> m.factory(StringBuilder.class, FactoryMethods.class)
                        .method("buffBuilder")
                        .params(M.strings().size(10).type(StringType.ALPHA_NUMERIC))
                        .val(),
                s -> {
                    assertTrue(s instanceof StringBuilder);
                    assertTrue(s.length() == 10);
                    assertTrue(StringUtils.isAlphanumeric(s.toString()));
                }
        );
    }
}
