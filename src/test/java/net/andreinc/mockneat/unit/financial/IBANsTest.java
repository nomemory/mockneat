package net.andreinc.mockneat.unit.financial;

import net.andreinc.mockneat.types.enums.IBANType;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 08/05/17.
 */
public class IBANsTest {

    private static final IBANCheckDigit ICD = new IBANCheckDigit();

    @Test(expected = NullPointerException.class)
    public void testNullType() throws Exception {
        M.ibans().type(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNullTypes() throws Exception {
        M.ibans().types(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyTypes() throws Exception {
        IBANType[] types = new IBANType[]{};
        M.ibans().types(types).val();
    }

    @Test
    public void testCheckDigitsInGeneral() throws Exception {
        loop(
                IBANS_CYCLES,
                MOCKS,
                r -> r.ibans().val(),
                IBAN -> assertTrue(ICD.isValid(IBAN))
        );
    }
}
