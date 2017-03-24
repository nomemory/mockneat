package net.andreinc.mockneat.unit.misc;

import org.junit.Test;

import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.Constants.SSC_CYCLES;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 24/03/17.
 */
public class SSCsTest {

    @Test
    public void testSSC() throws Exception {
        loop(
                SSC_CYCLES,
                MOCKS,
                m -> m.sccs().val(),
                ssc -> {

                    assertTrue(!ssc.equals("078-05-1120"));
                    assertTrue(!ssc.equals("219-09-9999"));
                    assertTrue(!ssc.startsWith("666"));

                    String[] sscArr = ssc.split("-");

                    assertTrue(sscArr.length == 3);

                    String aaa = sscArr[0];
                    String gg = sscArr[1];
                    String ssss = sscArr[2];

                    assertTrue(aaa.length() == 3);
                    assertTrue(gg.length() == 2);
                    assertTrue(ssss.length() == 4);
                }
        );
    }
}
