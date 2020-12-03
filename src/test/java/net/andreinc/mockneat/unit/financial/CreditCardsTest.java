package net.andreinc.mockneat.unit.financial;

import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.types.enums.CreditCardType;
import net.andreinc.mockneat.utils.LuhnUtils;
import net.andreinc.mockneat.utils.file.FileManager;
import org.apache.commons.validator.routines.CreditCardValidator;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static net.andreinc.mockneat.Constants.CCS_CYCLES;
import static net.andreinc.mockneat.Constants.M;
import static net.andreinc.mockneat.types.enums.CreditCardType.*;
import static net.andreinc.mockneat.types.enums.DictType.CREDIT_CARD_NAMES;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class CreditCardsTest {

    private final FileManager FM = FileManager.getInstance();
    private static final Map<CreditCardType, Set<String>> PREFIX = new EnumMap<>(CreditCardType.class);

    static {
        Arrays.stream(CreditCardType.values())
                .forEach(type -> {
                    Set<String> typeSet = type.getPrefixes()
                                                .stream()
                                                .map(list ->
                                                    list.stream()
                                                        .map(Object::toString)
                                                        .collect(Collectors.joining()))
                                                .collect(Collectors.toSet());
                    PREFIX.put(type, typeSet);
                });
    }

    private static boolean hasValidPrefix(String cc, CreditCardType type) {
        return PREFIX.get(type)
                        .stream()
                        .anyMatch(cc::startsWith);
    }

    private static boolean isValidCCOfType(String cc, CreditCardType type) {
        return type.getLength() == cc.length() &&
                hasValidPrefix(cc, type) &&
                LuhnUtils.luhnCheck(cc);
    }

    @Test
    public void testDefaultVal() {
        loop(
                CCS_CYCLES,
                Constants.MOCKS,
                m -> m.creditCards().val(),
                c -> assertTrue(isValidCCOfType(c, AMERICAN_EXPRESS))
        );
    }

    @Test(expected = NullPointerException.class)
    public void testCreditCardTypeNotNull() {
        M.creditCards().type(null).val();

    }

    @Test(expected = NullPointerException.class)
    public void testCreditCardTypesNotNull() {
        M.creditCards().types((CreditCardType[]) null).val();
    }

    @Test
    public void testCreditCardTypes() {
        CreditCardType[] types = { MASTERCARD, VISA_13 };
        loop(
                CCS_CYCLES,
                Constants.MOCKS,
                m -> {
                    String s = m.creditCards().types(types).val();
                    assertTrue(isValidCCOfType(s, MASTERCARD) ||
                               isValidCCOfType(s, VISA_13));
                }
        );
    }

    @Test
    public void testCreditCards() {
        loop(
                CCS_CYCLES,
                Constants.MOCKS,
                m -> {
                    CreditCardType type = m.from(CreditCardType.class).val();
                    String cc = m.creditCards().type(type).val();
                    assertTrue(isValidCCOfType(cc, type));
                }
        );
    }

    @Test
    public void testCreditCardsAmexApacheValidator() {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.AMEX);
        loop(
                CCS_CYCLES,
                Constants.MOCKS,
                m -> m.creditCards().type(AMERICAN_EXPRESS).val(),
                c -> assertTrue(ccv.isValid(c))
        );
    }

    @Test
    public void testCreditCardNames() {
        Set<String> set = new HashSet<>(FM.getLines(CREDIT_CARD_NAMES));
        loop(
                CCS_CYCLES,
                Constants.MOCKS,
                r -> r.creditCards().names().val(),
                cn -> assertTrue(set.contains(cn))
        );
    }

    @Test
    public void testAmex() {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.AMEX);
        assertTrue(ccv.isValid(M.creditCards().amex().val()));
    }

    @Test
    public void testMastercard() {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.MASTERCARD);
        assertTrue(ccv.isValid(M.creditCards().masterCard().val()));
    }

    @Test
    public void testVisa() {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.VISA);
        assertTrue(ccv.isValid(M.creditCards().visa().val()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCustom0length() {
        M.creditCards().custom(0, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCustomNegativeLength() {
        M.creditCards().custom(Integer.MIN_VALUE, 10).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCustomEmptyArray() {
        M.creditCards().custom(10).val();
    }

    @Test
    public void testCustomArray() {
        CreditCardValidator ccv = CreditCardValidator.genericCreditCardValidator();
        loop(CCS_CYCLES, () -> {
            int prefix = M.ints().range(100, Integer.MAX_VALUE).val();
            int length = M.ints().range(12, 20).val();
            String cc = M.creditCards().custom(length, prefix).val();
            assertTrue(ccv.isValid(cc));
        });
    }
}
