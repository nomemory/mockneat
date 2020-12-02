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

import net.andreinc.mockneat.types.enums.IBANType;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class IBANsTest {

    private static final IBANCheckDigit ICD = new IBANCheckDigit();

    @Test(expected = NullPointerException.class)
    public void testNullType() {
        M.ibans().type(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNullTypes() {
        IBANType ibanType = null;
        M.ibans().types(ibanType, ibanType).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyTypes() {
        IBANType[] types = new IBANType[]{};
        M.ibans().types(types).val();
    }

    @Test
    public void testIbanType() {
        loop(
                IBANS_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.ibans().type(IBANType.AUSTRIA).val(),
                iban -> assertTrue(ICD.isValid(iban))
        );
    }

    @Test
    public void testCheckDigitsInGeneral() {
        loop(
                IBANS_CYCLES,
                MOCKS,
                r -> r.ibans().val(),
                IBAN -> assertTrue(ICD.isValid(IBAN))
        );
    }
}
