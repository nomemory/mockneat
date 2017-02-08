package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitMonth;

import java.time.Month;
import java.util.function.Supplier;

import static com.mockneat.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.utils.ValidationUtils.UPPER_MONTH_BIGGER_THAN_LOWER;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

public class Months implements RandUnitMonth {

    private Rand rand;

    public Months(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<Month> supplier() {
        return rand.objs().from(Month.class)::val;
    }

    public RandUnitMonth range(Month lower, Month upper) {
        notNull(lower, INPUT_PARAMETER_NOT_NULL, "lower");
        notNull(upper, INPUT_PARAMETER_NOT_NULL, "upper");
        isTrue(lower.getValue()<upper.getValue(), UPPER_MONTH_BIGGER_THAN_LOWER);
        Supplier<Month> supp = () -> {
            int idx = rand.ints().range(lower.getValue(), upper.getValue()).val();
            return Month.values()[idx];
        };
        return () -> supp;
    }
}
