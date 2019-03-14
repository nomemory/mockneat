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
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.CreditCardType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static net.andreinc.mockneat.types.enums.CreditCardType.*;
import static net.andreinc.mockneat.types.enums.DictType.CREDIT_CARD_NAMES;
import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class CreditCards extends MockUnitBase implements MockUnitString {

    public static CreditCards creditCards() {
        return MockNeat.threadLocal().creditCards();
    }

    protected CreditCards() { }

    public CreditCards(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return type(AMERICAN_EXPRESS).supplier();
    }

    /**
     * Returns a new {@code MockUnitString} that is used to generate credit card names (not numbers). (Eg.: "Mastercard")
     *
     * @return A new {@link MockUnitString}.
     */
    public MockUnitString names() {
        return mockNeat.dicts().type(CREDIT_CARD_NAMES);
    }

    /**
     * Returns a new {@code MockUnitString} that is used to generate credit card numbers of a given type: {@link CreditCardType}.
     *
     * <p><em>Note: </em> Credit card numbers are financial information. The values are generated at random so don't use them in real-life scenarios.</p>
     *
     * @param type The type of the generated credit card number.
     * @return A new {@code MockUnitString}
     */
    public MockUnitString type(CreditCardType type) {
        notNull(type, "type");
        Supplier<String> supplier = () -> generateCreditCard(type);
        return () -> supplier;
    }

    /**
     * Returns a new {@code MockUnitString} that is used to generate custom credit card numbers with a given length and prefix.
     *
     * <p><em>Note: </em> Credit card numbers are financial information. The values are generated at random so don't use them in real-life scenarios.</p>
     *
     * @param length The length of the credit card number.
     * @param prefix The prefix
     * @return A new {@code MockUnitString}
     */
    public MockUnitString custom(int length, Integer... prefix) {
        notEmpty(prefix, "prefix");
        isTrue(length>=12 && length < 20, "Credit Card number length should be a value in the interval [12, 20).");
        Supplier<String> supplier = () -> generateCreditCard(length, prefix);
        return () -> supplier;
    }

    /**
     * Returns a new {@code MockUnitString} that is used to generate credit card numbers from the given {@code types}: {@link CreditCardType}.
     *
     * <p><em>Note: </em> Credit card numbers are financial information. The values are generated at random so don't use them in real-life scenarios.</p>
     *
     * @param types A var-arg array that contains the types of the desired credit card numbers.
     * @return A new {@code MockUnitString}
     */
    public MockUnitString types(CreditCardType... types) {
        notEmptyOrNullValues(types, "types");
        Supplier<String> supplier = () -> {
            CreditCardType creditCardType = mockNeat.from(types).val();
            return type(creditCardType).val();
        };
        return () -> supplier;
    }

    /**
     * Returns a new {@code MockUnitString} that is used to generate credit card numbers of type {@code AMERICAN_EXPRESS}.
     *
     * <p><em>Note: </em> This is a shortcut method for: {@code mockNeat.creditCards().type(AMERICAN_EXPRESS}</p>
     *
     * <p><em>Note: </em> Credit card numbers are financial information. The values are generated at random so don't use them in real-life scenarios.</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString amex() {
        return type(AMERICAN_EXPRESS);
    }


    /**
     * Returns a new {@code MockUnitString} that is used to generate credit card numbers of type {@code VISA_16}.
     *
     * <p><em>Note: </em> This is a shortcut method for: {@code mockNeat.creditCards().type(VISA_16}</p>
     *
     * <p><em>Note: </em> Credit card numbers are financial information. The values are generated at random so don't use them in real-life scenarios.</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString visa() {
        return type(VISA_16);
    }

    /**
     * Returns a new {@code MockUnitString} that is used to generate credit card numbers of type {@code MASTERCARD}.
     *
     * <p><em>Note: </em> This is a shortcut method for: {@code mockNeat.creditCards().type(MASTERCARD}</p>
     *
     * <p><em>Note: </em> Credit card numbers are financial information. The values are generated at random so don't use them in real-life scenarios.</p>
     *
     * @return A new {@code MockUnitString}
     */
    public MockUnitString masterCard() {
        return type(MASTERCARD);
    }

    private String generateCreditCard(int length, List<List<Integer>> prefixes) {
        int cnt = length - 1;

        int[] results = new int[length];

        // Pick objs prefix
        List<Integer> prefix = mockNeat.from(prefixes).val();

        // Initialize the array with objs numbers
        // prefix + rest of the arrays
        for (int i = 0; i < cnt; i++)
            results[i] = (i < prefix.size()) ? prefix.get(i) :
                    mockNeat.ints().range(0, 10).val();

        // Computing sum
        boolean dblFlag = true;
        int sum = 0;
        int dbl;
        while (cnt-- > 0) {
            if (dblFlag) {
                dbl = 2 * results[cnt];
                sum += (dbl > 9) ? (dbl % 10 + 1) : dbl;
            } else {
                sum += results[cnt];
            }
            dblFlag = !dblFlag;
        }
        // Add the check digit
        results[length - 1] = (9 * sum) % 10;

        // Returning result
        StringBuilder buff = new StringBuilder();
        Arrays.stream(results).forEach(buff::append);
        return buff.toString();
    }

    private List<Integer> fromNumber(int num) {
        List<Integer> list = new LinkedList<>();
        int tmp = num;
        while(tmp!=0) {
            list.add(0, tmp%10);
            tmp/=10;
        }
        return unmodifiableList(list);
    }

    private String generateCreditCard(int length, Integer... prefixes) {
        return generateCreditCard(length, Arrays.stream(prefixes).map(this::fromNumber).collect(toList()));
    }

    private String generateCreditCard(CreditCardType creditCardType) {
        return generateCreditCard(creditCardType.getLength(), creditCardType.getPrefixes());
    }
}
