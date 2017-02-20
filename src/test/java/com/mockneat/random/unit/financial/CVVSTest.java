package com.mockneat.random.unit.financial;

import org.junit.Test;

import static com.mockneat.random.RandTestConstants.CVVS_CYCLES;
import static com.mockneat.random.RandTestConstants.RAND;
import static com.mockneat.random.RandTestConstants.RANDS;
import static com.mockneat.types.enums.CVVType.CVV3;
import static com.mockneat.types.enums.CVVType.CVV4;
import static com.mockneat.random.utils.FunctUtils.loop;
import static java.util.Arrays.stream;
import static junit.framework.Assert.assertTrue;
import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by andreinicolinciobanu on 08/02/2017.
 */
public class CVVSTest {

    @Test(expected = NullPointerException.class)
    public void testCVVTypeNotNull() throws Exception {
        RAND.cvvs().type(null).val();
    }

    @Test
    public void testCVV3() throws Exception {
        loop(CVVS_CYCLES, RANDS, r -> r.cvvs().type(CVV3).val(), cvv -> {
            assertTrue(!isEmpty(cvv) && cvv.length()==3);
            assertTrue(isAlphanumeric(cvv));
        });
    }

    @Test
    public void testCVV4() throws Exception {
        loop(CVVS_CYCLES, RANDS, r -> r.cvvs().type(CVV4).val(), cvv -> {
            assertTrue(null!=cvv && cvv.length()==4);
            assertTrue(isAlphanumeric(cvv));
        });
    }
}
