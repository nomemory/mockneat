package com.mockneat.random;

import com.mockneat.utils.FunctUtils;
import org.junit.Test;

import static com.mockneat.random.RandTestConstants.RAND;

/**
 * Created by andreinicolinciobanu on 08/02/2017.
 */
public class CVVSTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCVVTypeNotNull() throws Exception {
        RAND.cvvs().type(null).val();
    }

}
