package com.mockneat.random.unit;


import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.CreditCardType;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static com.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;
import static com.mockneat.utils.ValidationUtils.*;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
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
        CreditCardType creditCardType = rand.objs().from(types).val();
        return types(creditCardType);
    }

    protected String generateCreditCard(CreditCardType creditCardType) {
        int arraySize = creditCardType.getLength();
        int cnt = arraySize - 1;

        int[] results = new int[arraySize];

        // Pick random prefix
        List<Integer> prefix = rand.objs().from(creditCardType.getPrefixes()).val();

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
