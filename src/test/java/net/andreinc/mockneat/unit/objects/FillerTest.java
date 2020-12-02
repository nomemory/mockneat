package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.unit.objects.model.SimpleBean;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static net.andreinc.mockneat.Constants.M;

public class FillerTest {

    @Test
    public void test1() {
        SimpleBean s =  M.filler(SimpleBean::new)
                         .setter(SimpleBean::setS, M.from(new String[]{"A"}))
                         .val();

        Assert.assertEquals("A", s.getS());
    }

    @Test(expected = NullPointerException.class)
    public void testFillerNullSupp() {
        M.filler(null);
    }

    @Test(expected = NullPointerException.class)
    public void testFillerNullSetter() {
        M.filler(SimpleBean::new)
         .setter(null, M.from(new String[]{"A"}));
    }

    @Test(expected = NullPointerException.class)
    public void testFillerNullValue() {
        M.filler(SimpleBean::new)
         .setter(SimpleBean::setS, null);
    }

    @Test
    public void testFillerWithConstant() {
        List<SimpleBean> simpleBeans = M.filler(SimpleBean::new)
                                        .constant(SimpleBean::setS, "A")
                                        .list(ArrayList::new, 5)
                                        .val();

        simpleBeans.forEach(s -> Assert.assertEquals("A", s.getS()));
    }
}
