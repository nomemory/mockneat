package net.andreinc.mockneat.unit.time;

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

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitMonth;

import java.time.Month;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class Months extends MockUnitBase implements MockUnitMonth {

    public Months(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<Month> supplier() {
        return mockNeat.from(Month.class)::val;
    }

    public MockUnitMonth rangeClosed(Month lower, Month upper) {
        notNull(lower, "lower");
        notNull(upper, "upper");
        isTrue(lower.getValue() < upper.getValue(), UPPER_MONTH_BIGGER_THAN_LOWER);
        Supplier<Month> supp = () -> {
            int idx = mockNeat.ints().range(lower.getValue()-1, upper.getValue()).val();
            return Month.values()[idx];
        };
        return () -> supp;
    }

    public MockUnitMonth range(Month lower, Month upper) {
        notNull(lower, "lower");
        notNull(upper, "upper");
        isTrue(lower.getValue() < upper.getValue(), UPPER_MONTH_BIGGER_THAN_LOWER);
        Supplier<Month> supp = () -> {
            int idx = mockNeat.ints().range(lower.getValue()-1, upper.getValue()-1).val();
            return Month.values()[idx];
        };
        return () -> supp;
    }

    public MockUnitMonth before(Month before) {
        notNull(before, "before");
        isTrue(before.getValue()-1>0, BEFORE_MONTH_DIFFERENT_THAN_JANUARY);
        return range(Month.values()[0], before);
    }

    public MockUnitMonth after(Month after) {
        notNull(after, "after");
        isTrue(after.getValue()<Month.values().length-1, AFTER_MONTH_DIFFERENT_TNAN_DECEMBER);
        Supplier<Month> supp = () -> {
            int idx = mockNeat.ints().range(after.getValue(), Month.values().length).val();
            return Month.values()[idx];
        };
        return () -> supp;
    }
}
