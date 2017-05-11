package net.andreinc.mockneat.unit.financial;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

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
