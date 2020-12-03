package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.types.enums.StringType;
import net.andreinc.mockneat.unit.objects.model.FactoryMethods;
import net.andreinc.mockneat.utils.LoopsUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class FactoryTest {

    @Test(expected = NullPointerException.class)
    public void testFactoryNullClass() {
        Constants.M.factory(null, String.class).method("some").params("abc").val();
    }

    @Test(expected = NullPointerException.class)
    public void testFactorNullFactoryClass() {
        Constants.M.factory(String.class, null).method("some").params("abc").val();
    }

    @Test(expected = NullPointerException.class)
    public void testFactoryForgetMethod() {
        Constants.M.factory(StringBuilder.class, FactoryMethods.class)
                .params("ABC")
                .val();
    }

    @Test(expected = NullPointerException.class)
    public void testFactoryNullMethod() {
        Constants.M.factory(StringBuilder.class, FactoryMethods.class)
                .method(null)
                .params("ABC")
                .val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactoryForgetParams() {
        Constants.M.factory(StringBuilder.class, FactoryMethods.class)
                .method("buffBuilder")
                .val();
    }

    @Test(expected = NullPointerException.class)
    public void testFactoryNullParams() {
        Constants.M.factory(StringBuilder.class, FactoryMethods.class)
                .method("buffBuilder")
                .params((Object[]) null)
                .val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactoryBuilderInvalidMethod() {
        Constants.M.factory(StringBuilder.class, FactoryMethods.class)
                      .method("xxx")
                      .params("ABC")
                      .val();
    }

    @Test
    public void testFactoryBuilderConstantParam() {
        StringBuilder buff = Constants.M.factory(StringBuilder.class, FactoryMethods.class)
                                .method("buffBuilder")
                                .params("ABC")
                                .val();
        assertEquals("ABC", buff.toString());
    }

    @Test
    public void testFactoryBuilderMockParam() {
        LoopsUtils.loop(
                Constants.OBJS_CYCLES,
                Constants.MOCKS,
                m -> m.factory(StringBuilder.class, FactoryMethods.class)
                        .method("buffBuilder")
                        .params(Constants.M.strings().size(10).type(StringType.ALPHA_NUMERIC))
                        .val(),
                s -> {
                    assertNotNull(s);
                    assertEquals(10, s.length());
                    assertTrue(StringUtils.isAlphanumeric(s.toString()));
                }
        );
    }
}
