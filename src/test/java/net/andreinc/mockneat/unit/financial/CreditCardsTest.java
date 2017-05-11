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
import net.andreinc.mockneat.types.enums.CreditCardType;
import net.andreinc.mockneat.utils.LuhnUtils;
import net.andreinc.mockneat.utils.file.FileManager;
import org.apache.commons.validator.routines.CreditCardValidator;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static net.andreinc.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;
import static net.andreinc.mockneat.types.enums.CreditCardType.MASTERCARD;
import static net.andreinc.mockneat.types.enums.CreditCardType.VISA_13;
import static net.andreinc.mockneat.types.enums.DictType.CREDIT_CARD_NAMES;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
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
                LuhnUtils.luhnCheck(cc);
    }

    @Test
    public void testDefaultVal() throws Exception {
        loop(
                Constants.CCS_CYCLES,
                Constants.MOCKS,
                m -> m.creditCards().val(),
                c -> assertTrue(isValidCCOfType(c, AMERICAN_EXPRESS))
        );
    }

    @Test(expected = NullPointerException.class)
    public void testCreditCardTypeNotNull() throws Exception {
        CreditCardType type = null;
        Constants.M.creditCards().type(type).val();

    }

    @Test(expected = NullPointerException.class)
    public void testCreditCardTypesNotNull() throws Exception {
        CreditCardType[] types = null;
        Constants.M.creditCards().types(types).val();
    }

    @Test
    public void testCreditCardTypes() throws Exception {
        CreditCardType[] types = { MASTERCARD, VISA_13 };
        loop(
                Constants.CCS_CYCLES,
                Constants.MOCKS,
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
                Constants.CCS_CYCLES,
                Constants.MOCKS,
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
                Constants.CCS_CYCLES,
                Constants.MOCKS,
                m -> m.creditCards().type(AMERICAN_EXPRESS).val(),
                c -> assertTrue(ccv.isValid(c))
        );
    }

    @Test
    public void testCreditCardNames() throws Exception {
        Set<String> set = new HashSet<>(FM.getLines(CREDIT_CARD_NAMES));
        loop(
                Constants.CCS_CYCLES,
                Constants.MOCKS,
                r -> r.creditCards().names().val(),
                cn -> assertTrue(set.contains(cn))
        );
    }
}
