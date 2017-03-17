package net.andreinc.mockneat.unit.financial;

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static net.andreinc.mockneat.types.enums.CVVType.CVV3;
import static net.andreinc.mockneat.types.enums.CVVType.CVV4;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by andreinicolinciobanu on 08/02/2017.
 */
public class CVVSTest {

    @Test(expected = NullPointerException.class)
    public void testCVVTypeNotNull() throws Exception {
        Constants.M.cvvs().type(null).val();
    }

    @Test
    public void testCVV3() throws Exception {
        loop(Constants.CVVS_CYCLES, Constants.MOCKS, r -> r.cvvs().type(CVV3).val(), cvv -> {
            assertTrue(!isEmpty(cvv) && cvv.length()==3);
            assertTrue(isAlphanumeric(cvv));
        });
    }

    @Test
    public void testCVV4() throws Exception {
        loop(Constants.CVVS_CYCLES, Constants.MOCKS, r -> r.cvvs().type(CVV4).val(), cvv -> {
            assertTrue(null!=cvv && cvv.length()==4);
            assertTrue(isAlphanumeric(cvv));
        });
    }
}
