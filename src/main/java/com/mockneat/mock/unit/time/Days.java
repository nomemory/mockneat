package com.mockneat.mock.unit.time;

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

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitDays;
import com.mockneat.mock.utils.ValidationUtils;

import java.time.DayOfWeek;
import java.util.function.Supplier;

import static com.mockneat.mock.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

public class Days implements MockUnitDays {

    private MockNeat mock;

    public Days(MockNeat mock) {
        this.mock = mock;
    }

    @Override
    public Supplier<DayOfWeek> supplier() {
        return mock.from(DayOfWeek.class)::val;
    }

    public MockUnitDays rangeClosed(DayOfWeek lower, DayOfWeek upper) {
        notNull(lower, INPUT_PARAMETER_NOT_NULL, "lower");
        notNull(upper, INPUT_PARAMETER_NOT_NULL, "upper");
        isTrue(lower.getValue()<upper.getValue(), ValidationUtils.UPPER_MONTH_BIGGER_THAN_LOWER);
        Supplier<DayOfWeek> supp = () -> {
            int idx = mock.ints().range(lower.getValue()-1, upper.getValue()).val();
            return DayOfWeek.values()[idx];
        };
        return () -> supp;
    }

    public MockUnitDays range(DayOfWeek lower, DayOfWeek upper) {
        notNull(lower, INPUT_PARAMETER_NOT_NULL, "lower");
        notNull(upper, INPUT_PARAMETER_NOT_NULL, "upper");
        isTrue(lower.getValue()<upper.getValue(), ValidationUtils.UPPER_MONTH_BIGGER_THAN_LOWER);
        Supplier<DayOfWeek> supp = () -> {
            int idx = mock.ints().range(lower.getValue()-1, upper.getValue()-1).val();
            return DayOfWeek.values()[idx];
        };
        return () -> supp;
    }

    public MockUnitDays before(DayOfWeek before) {
        notNull(before, INPUT_PARAMETER_NOT_NULL, "before");
        isTrue(before.getValue()-1>0, ValidationUtils.BEFORE_DAY_DIFFERENT_THAN_MONDAY);
        return range(DayOfWeek.values()[0], before);
    }

    public MockUnitDays after(DayOfWeek after) {
        notNull(after, INPUT_PARAMETER_NOT_NULL, "after");
        isTrue(after.getValue()-1<DayOfWeek.values().length-1, ValidationUtils.AFTER_DAY_DIFFERENT_THAN_SUNDAY);
        Supplier<DayOfWeek> supp = () -> {
            int idx = mock.ints().range(after.getValue(), DayOfWeek.values().length).val();
            return DayOfWeek.values()[idx];
        };
        return () -> supp;
    }
}
