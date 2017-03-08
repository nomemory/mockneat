package com.mockneat.mock.unit.financial;

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

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitString;
import com.mockneat.types.enums.CreditCardType;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static com.mockneat.mock.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.mock.utils.ValidationUtils.notEmptyTypes;
import static com.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;
import static com.mockneat.types.enums.DictType.CREDIT_CARD_NAMES;
import static org.apache.commons.lang3.Validate.notNull;

public class CreditCards implements MockUnitString {

    private final MockNeat mock;

    public CreditCards(MockNeat mock) {
        this.mock = mock;
    }

    @Override
    public Supplier<String> supplier() {
        return type(AMERICAN_EXPRESS).supplier();
    }

    public MockUnitString names() {
        return () -> mock.dicts().type(CREDIT_CARD_NAMES)::val;
    }

    public MockUnitString type(CreditCardType type) {
        notNull(type, INPUT_PARAMETER_NOT_NULL, "type");
        Supplier<String> supplier = () -> generateCreditCard(type);
        return () -> supplier;
    }

    public MockUnitString types(CreditCardType... types) {
        notEmptyTypes(types);
        CreditCardType creditCardType = mock.from(types).val();
        return type(creditCardType);
    }

    protected String generateCreditCard(CreditCardType creditCardType) {
        int arraySize = creditCardType.getLength();
        int cnt = arraySize - 1;

        int[] results = new int[arraySize];

        // Pick objs prefix
        List<Integer> prefix = mock.from(creditCardType.getPrefixes()).val();

        // Initialize the array with objs numbers
        // prefix + rest of the arrays
        for (int i = 0; i < cnt; i++)
            results[i] = (i < prefix.size()) ? prefix.get(i) :
                    mock.ints().range(0, 10).val();

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
        results[arraySize - 1] = (9 * sum) % 10;

        // Returning result
        StringBuilder buff = new StringBuilder();
        Arrays.stream(results).forEach(buff::append);
        return buff.toString();
    }
}
