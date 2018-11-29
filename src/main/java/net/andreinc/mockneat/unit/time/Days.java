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
import net.andreinc.mockneat.abstraction.MockUnitDays;
import net.andreinc.mockneat.utils.ValidationUtils;

import java.time.DayOfWeek;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.UPPER_MONTH_BIGGER_THAN_LOWER;
import static net.andreinc.mockneat.utils.ValidationUtils.isTrue;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Days extends MockUnitBase implements MockUnitDays {

    public Days() { }

    public Days(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<DayOfWeek> supplier() {
        return mockNeat.from(DayOfWeek.class)::val;
    }

    /**
     * Creates a new {@code MockUnitDays} that is used to generate a day of the week in the closed ranged [{@code lower}, {@code upper}].
     *
     * @param lower The lower limit of the interval.
     * @param upper The upper limit of the interval.
     * @return A new {@code MockUnitDays}.
     */
    public MockUnitDays rangeClosed(DayOfWeek lower, DayOfWeek upper) {
        notNull(lower, "lower");
        notNull(upper, "upper");
        isTrue(lower.getValue()<upper.getValue(), UPPER_MONTH_BIGGER_THAN_LOWER);
        Supplier<DayOfWeek> supp = () -> {
            int idx = mockNeat.ints().range(lower.getValue()-1, upper.getValue()).val();
            return DayOfWeek.values()[idx];
        };
        return () -> supp;
    }

    /**
     * Creates a new {@code MockUnitDays} that is used to generate a day of the week in the open range [{@code lower}, {@code upper}).
     *
     * @param lower The lower limit of the interval.
     * @param upper The upper limit of the interval.
     * @return A new {@code MockUnitDays}
     */
    public MockUnitDays range(DayOfWeek lower, DayOfWeek upper) {
        notNull(lower, "lower");
        notNull(upper, "upper");
        isTrue(lower.getValue()<upper.getValue(), UPPER_MONTH_BIGGER_THAN_LOWER);
        Supplier<DayOfWeek> supp = () -> {
            int idx = mockNeat.ints().range(lower.getValue()-1, upper.getValue()-1).val();
            return DayOfWeek.values()[idx];
        };
        return () -> supp;
    }

    /**
     * Creates a {@code MockUnitDays} that is used to generate a day of the week before the given limit.
     *
     * @param before The upper limit.
     * @return A new {@code MockUnitDays}
     */
    public MockUnitDays before(DayOfWeek before) {
        notNull(before, "before");
        isTrue(before.getValue()-1>0, ValidationUtils.BEFORE_DAY_DIFFERENT_THAN_MONDAY);
        return range(DayOfWeek.values()[0], before);
    }


    /**
     * Creates a {@code MockUnitDays} that is used to generate a day of the week after the given limit.
     *
     * @param after The lower limit.
     * @return A new {@code MockUnitDays}.
     */
    public MockUnitDays after(DayOfWeek after) {
        notNull(after, "after");
        isTrue(after.getValue()-1<DayOfWeek.values().length-1, ValidationUtils.AFTER_DAY_DIFFERENT_THAN_SUNDAY);
        Supplier<DayOfWeek> supp = () -> {
            int idx = mockNeat.ints().range(after.getValue(), DayOfWeek.values().length).val();
            return DayOfWeek.values()[idx];
        };
        return () -> supp;
    }
}
