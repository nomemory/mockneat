package net.andreinc.mockneat.unit.misc;

import net.andreinc.mockneat.MockNeat;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.Constants.SSC_CYCLES;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.*;

public class SSCsTest {

    @Test
    public void testSSC() {
        loop(
                SSC_CYCLES,
                MOCKS,
                m -> m.sscs().val(),
                ssc -> {

                    assertNotEquals("078-05-1120", ssc);
                    assertNotEquals("219-09-9999", ssc);
                    assertFalse(ssc.startsWith("666"));

                    String[] sscArr = ssc.split("-");

                    assertEquals(3, sscArr.length);

                    String aaa = sscArr[0];
                    String gg = sscArr[1];
                    String ssss = sscArr[2];

                    assertEquals(3, aaa.length());
                    assertEquals(2, gg.length());
                    assertEquals(4, ssss.length());
                }
        );
    }
}
