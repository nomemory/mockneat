package com.mockneat.random;

import com.mockneat.types.enums.CreditCardType;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static com.mockneat.LuhnUtils.luhnCheck;
import static java.util.Arrays.stream;
import static com.mockneat.random.RandTestConstants.CCS_CYCLES;
import static com.mockneat.random.RandTestConstants.RAND;
import static com.mockneat.random.RandTestConstants.RANDS;
import static com.mockneat.utils.FunctUtils.cycle;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CCSTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCreditCardTypeNotNull() throws Exception {
        CreditCardType type = null;
        CreditCardType[] types = null;
        RAND.ccs().type(type).val();
        RAND.ccs().types(types).val();
    }

    @Test
    public void testCreditCardCorrectLength() throws Exception {
        cycle(CCS_CYCLES, () ->
                stream(RANDS).forEach(r -> {
                    CreditCardType creditCardType = r.objs().from(CreditCardType.class).val();
                    String cc = r.ccs().type(creditCardType).val();
                    assertTrue(cc.length() == creditCardType.getLength());
                }));
    }

    @Test
    public void testCreditCardHasCorrectPrefix() throws Exception {
        cycle(CCS_CYCLES, () ->
                stream(RANDS).forEach(r -> {
                    CreditCardType creditCardType = r.objs().from(CreditCardType.class).val();
                    // Obtain the set of prefixes associated with the credit card type
                    Set<String> prefixes = creditCardType
                                                .getPrefixes()
                                                .stream()
                                                .map(list ->
                                                        list.stream()
                                                                .map(i -> i.toString())
                                                                .collect(Collectors.joining()))
                                                .collect(Collectors.toSet());
                    String cc = r.ccs().type(creditCardType).val();
                    // Test to see if the credit card
                    // is starting with a correct prefix
                    boolean test = false;
                    for(String prefix : prefixes) {
                        if (cc.startsWith(prefix)) {
                            test = true;
                            break;
                        }
                    }
                    assertTrue(test);
                })
        );
    }

    @Test
    public void nextCreditCardAreValidLuhn() throws Exception {
        cycle(CCS_CYCLES, () ->
                stream(RANDS)
                    .forEach(r -> {
                        CreditCardType type = r.objs().from(CreditCardType.class).val();
                        assertTrue(luhnCheck(r.ccs().type(type).val()));
                    }));
    }

}
