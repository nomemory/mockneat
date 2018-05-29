package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.unit.objects.model.SimpleBean;
import org.junit.Assert;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.M;

public class FillerTest {

    @Test
    public void test1() {
        SimpleBean s =  M.filler(() -> new SimpleBean())
                         .setter(SimpleBean::setS, M.from(new String[]{"A"}))
                         .val();

        Assert.assertTrue(s.getS().equals("A"));
    }
}
