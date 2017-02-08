package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitDays;

import java.time.DayOfWeek;
import java.util.function.Supplier;

import static com.mockneat.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.utils.ValidationUtils.UPPER_MONTH_BIGGER_THAN_LOWER;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

public class Days implements RandUnitDays {

    private Rand rand;

    public Days(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<DayOfWeek> supplier() {
        return rand.objs().from(DayOfWeek.class)::val;
    }

    public RandUnitDays range(DayOfWeek lower, DayOfWeek upper) {
        notNull(lower, INPUT_PARAMETER_NOT_NULL, "lower");
        notNull(upper, INPUT_PARAMETER_NOT_NULL, "upper");
        isTrue(lower.getValue()<upper.getValue(), UPPER_MONTH_BIGGER_THAN_LOWER);
        Supplier<DayOfWeek> supp = () -> {
            int idx = rand.ints().range(lower.getValue(), upper.getValue()).val();
            return DayOfWeek.values()[idx];
        };
        return () -> supp;
    }
}
