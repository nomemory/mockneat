package net.andreinc.mockneat.unit.financial;

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
        M.ibans().types(null, null).val();
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
