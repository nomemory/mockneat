package com.mockneat.random.unit.financial;

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
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import com.mockneat.random.Rand;
import com.mockneat.random.interfaces.RandUnitString;
import com.mockneat.types.enums.CreditCardType;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import static com.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;
import static com.mockneat.random.utils.ValidationUtils.*;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class CCS implements RandUnitString {

    private Rand rand;

    public CCS(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<String> supplier() {
        return type(AMERICAN_EXPRESS).supplier();
    }

    public RandUnitString type(CreditCardType type) {
        notNull(type, INPUT_PARAMETER_NOT_NULL, "type");
        Supplier<String> supplier = () -> generateCreditCard(type);
        return () -> supplier;
    }

    public RandUnitString types(CreditCardType... types) {
        notEmpty(types, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "types");
        CreditCardType creditCardType = rand.from(types).val();
        return types(creditCardType);
    }

    protected String generateCreditCard(CreditCardType creditCardType) {
        int arraySize = creditCardType.getLength();
        int cnt = arraySize - 1;

        int[] results = new int[arraySize];

        // Pick random prefix
        List<Integer> prefix = rand.from(creditCardType.getPrefixes()).val();

        // Initialize the array with random numbers
        // prefix + rest of the arrays
        for (int i = 0; i < cnt; i++)
            results[i] = (i < prefix.size()) ? prefix.get(i) :
                    rand.ints().range(0, 10).val();

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
