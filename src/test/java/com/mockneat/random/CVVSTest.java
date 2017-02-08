package com.mockneat.random;

import org.junit.Test;

import static com.mockneat.random.RandTestConstants.CVVS_CYCLES;
import static com.mockneat.random.RandTestConstants.RAND;
import static com.mockneat.random.RandTestConstants.RANDS;
import static com.mockneat.types.enums.CVVType.CVV3;
import static com.mockneat.types.enums.CVVType.CVV4;
import static com.mockneat.utils.FunctUtils.cycle;
import static com.mockneat.utils.StringUtils.allDigits;
import static com.mockneat.utils.StringUtils.isEmpty;
import static java.util.Arrays.stream;
import static junit.framework.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 08/02/2017.
 */
public class CVVSTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCVVTypeNotNull() throws Exception {
        RAND.cvvs().type(null).val();
    }

    @Test
    public void testCVV3() throws Exception {
        cycle(CVVS_CYCLES, () ->
                stream(RANDS).forEach(r -> {
                    String cvv = r.cvvs().type(CVV3).val();
                    assertTrue(!isEmpty(cvv) && cvv.length()==3);
                    assertTrue(allDigits(cvv));
                }));
    }

    @Test
    public void testCVV4() throws Exception {
        cycle(CVVS_CYCLES, () ->
                stream(RANDS).forEach(r -> {
                    String cvv = r.cvvs().type(CVV4).val();
                    assertTrue(null!=cvv && cvv.length()==4);
                    assertTrue(allDigits(cvv));
                }));
    }
}
