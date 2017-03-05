package com.mockneat.mock.unit.financial;

import com.mockneat.mock.utils.file.FileManager;
import com.mockneat.types.enums.CreditCardType;
import org.apache.commons.validator.routines.CreditCardValidator;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static com.mockneat.mock.Constants.*;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static com.mockneat.mock.utils.LuhnUtils.luhnCheck;
import static com.mockneat.types.enums.CreditCardType.*;
import static com.mockneat.types.enums.DictType.CREDIT_CARD_NAMES;
import static org.junit.Assert.assertTrue;

public class CreditCardsTest {

    private FileManager FM = FileManager.getInstance();
    private static Map<CreditCardType, Set<String>> PREFIX = new EnumMap<>(CreditCardType.class);

    static {
        Arrays.stream(CreditCardType.values())
                .forEach(type -> {
                    Set<String> typeSet = type.getPrefixes()
                                                .stream()
                                                .map(list ->
                                                    list.stream()
                                                        .map(i -> i.toString())
                                                        .collect(Collectors.joining()))
                                                .collect(Collectors.toSet());
                    PREFIX.put(type, typeSet);
                });
    }

    private static boolean hasValdiPrefix(String cc, CreditCardType type) {
        return PREFIX.get(type)
                        .stream()
                        .filter(pref -> cc.startsWith(pref))
                        .findFirst()
                        .isPresent();
    }

    private static boolean isValidCCOfType(String cc, CreditCardType type) {
        return type.getLength() == cc.length() &&
                hasValdiPrefix(cc, type) &&
                luhnCheck(cc);
    }

    @Test
    public void testDefaultVal() throws Exception {
        loop(
                CCS_CYCLES,
                MOCKS,
                m -> m.creditCards().val(),
                c -> assertTrue(isValidCCOfType(c, AMERICAN_EXPRESS))
        );
    }

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
    public void testCreditCardTypes() throws Exception {
        CreditCardType[] types = { MASTERCARD, VISA_13 };
        loop(
                CCS_CYCLES,
                MOCKS,
                m -> {
                    String s = m.creditCards().types(types).val();
                    assertTrue(isValidCCOfType(s, MASTERCARD) ||
                               isValidCCOfType(s, VISA_13));
                }
        );
    }

    @Test
    public void testCreditCards() throws Exception {
        loop(
                CCS_CYCLES,
                MOCKS,
                m -> {
                    CreditCardType type = m.from(CreditCardType.class).val();
                    String cc = m.creditCards().type(type).val();
                    assertTrue(isValidCCOfType(cc, type));
                }
        );
    }

    @Test
    public void testCreditCardsAmexApacheValidator() throws Exception {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.AMEX);
        loop(
                CCS_CYCLES,
                MOCKS,
                m -> m.creditCards().type(AMERICAN_EXPRESS).val(),
                c -> assertTrue(ccv.isValid(c))
        );
    }

    @Test
    public void testCreditCardNames() throws Exception {
        Set<String> set = new HashSet<>(FM.getLines(CREDIT_CARD_NAMES));
        loop(
                CCS_CYCLES,
                MOCKS,
                r -> r.creditCards().names().val(),
                cn -> assertTrue(set.contains(cn))
        );
    }
}
