package com.mockneat.sources.random.rand;

import com.mockneat.LuhnUtils;
import com.mockneat.types.enums.CreditCardType;
import org.junit.Test;

import static com.mockneat.LuhnUtils.luhnCheck;
import static java.util.Arrays.stream;
import static com.mockneat.sources.random.rand.RandTestConstants.NEXT_CREDIT_CARD_CYCLES;
import static com.mockneat.sources.random.rand.RandTestConstants.RAND;
import static com.mockneat.sources.random.rand.RandTestConstants.RANDS;
import static com.mockneat.utils.FunctUtils.cycle;
import static org.junit.Assert.assertTrue;

public class CCSTest {

    @Test(expected = IllegalArgumentException.class)
    public void nextCreditCardTypeNotNull() throws Exception {
        RAND.ccs().ofType(null).val();
    }

    @Test
    public void nextCreditCardAreValidLuhn() throws Exception {
        cycle(NEXT_CREDIT_CARD_CYCLES, () ->
                stream(RANDS)
                    .forEach(r -> {
                        CreditCardType type = r.objs().from(CreditCardType.class).val();
                        assertTrue(luhnCheck(r.ccs().ofType(type).val()));
                    }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextCVVTypeNotNull() throws Exception {
        RAND.cvvs().ofType(null).val();
    }

}
