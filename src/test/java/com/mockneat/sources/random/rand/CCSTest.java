package com.mockneat.sources.random.rand;

import com.mockneat.LuhnUtils;
import com.mockneat.types.enums.CreditCardType;
import org.junit.Test;

import static java.util.Arrays.stream;
import static com.mockneat.sources.random.rand.RandTestConstants.NEXT_CREDIT_CARD_CYCLES;
import static com.mockneat.sources.random.rand.RandTestConstants.RAND;
import static com.mockneat.sources.random.rand.RandTestConstants.RANDS;
import static com.mockneat.utils.FunctUtils.cycle;
import static org.junit.Assert.assertTrue;

public class CCSTest {


    /**
     * Tests if the method:
     * > String number(CreditCardType type)
     * Doesn't accept a null type
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void nextCreditCardTypeNotNull() throws Exception {
        RAND.ccs().ofType(null).val();
    }

    /**
     * Test if the credit cards generated are Luhn valid
     * @throws Exception
     */
    @Test
    public void nextCreditCardAreValidLuhn() throws Exception {
        cycle(NEXT_CREDIT_CARD_CYCLES, () ->
                stream(RANDS)
                    .forEach(r -> {
                        CreditCardType type = r.objs().from(CreditCardType.class).val();
                        assertTrue(LuhnUtils.luhn(r.ccs().ofType(type).val()));
                    }));
    }

    /**
     * Tests if the method:
     * > String cvv(CVVType type)
     * Doesn't accept a null type
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void nextCVVTypeNotNull() throws Exception {
        RAND.cvvs().ofType(null).val();
    }


}
