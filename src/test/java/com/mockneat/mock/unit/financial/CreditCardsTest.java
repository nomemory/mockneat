package com.mockneat.mock.unit.financial;

import com.mockneat.mock.utils.file.FileManager;
import com.mockneat.types.enums.CreditCardType;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mockneat.mock.Constants.*;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static com.mockneat.mock.utils.LuhnUtils.luhnCheck;
import static com.mockneat.types.enums.DictType.CREDIT_CARD_NAMES;
import static org.junit.Assert.assertTrue;

public class CreditCardsTest {

    private FileManager FM = FileManager.getInstance();

    @Test(expected = NullPointerException.class)
    public void testCreditCardTypeNotNull() throws Exception {
        CreditCardType type = null;
        M.creditCards().type(type).val();

    }

    @Test(expected = NullPointerException.class)
    public void testCreditCardTypesNotNull() throws Exception {
        CreditCardType[] types = null;
        M.creditCards().types(types).val();
    }

    @Test
    public void testCreditCardCorrectLength() throws Exception {
        loop(CCS_CYCLES, MOCKS, r -> {
            CreditCardType creditCardType = r.from(CreditCardType.class).val();
            String cc =  r.creditCards().type(creditCardType).val();
            assertTrue(cc.length() == creditCardType.getLength());
        });
    }

    @Test
    public void testCreditCardHasCorrectPrefix() throws Exception {
        loop(CCS_CYCLES, MOCKS, r -> {
                    CreditCardType creditCardType = r.from(CreditCardType.class).val();
                    // Obtain the set of prefixes associated with the credit card type
                    Set<String> prefixes = creditCardType
                                                .getPrefixes()
                                                .stream()
                                                .map(list ->
                                                        list.stream()
                                                                .map(i -> i.toString())
                                                                .collect(Collectors.joining()))
                                                .collect(Collectors.toSet());
                    String cc = r.creditCards().type(creditCardType).val();
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
                });
    }

    @Test
    public void testCreditCardAreValidLuhn() throws Exception {
        loop(CCS_CYCLES,
                MOCKS,
                r -> {
                    CreditCardType c = r.from(CreditCardType.class).val();
                    return r.creditCards().type(c).val();
                },
                c -> assertTrue(luhnCheck(c)));
    }

    @Test
    public void testCreditCardNames() throws Exception {
        Set<String> set = new HashSet<>(FM.getLines(CREDIT_CARD_NAMES));
        loop(CCS_CYCLES, MOCKS, r -> r.creditCards().names().val(), cn ->
            assertTrue(set.contains(cn)));
    }
}
