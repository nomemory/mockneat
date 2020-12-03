package net.andreinc.mockneat.unit.time;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitMonth;

import java.time.Month;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class Months extends MockUnitBase implements MockUnitMonth {

    public static Months months() {
        return MockNeat.threadLocal().months();
    }

    protected Months() { }

    public Months(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<Month> supplier() {
        return mockNeat.from(Month.class)::val;
    }

    /**
     * <p>Returns a new {@code MockUnitMonth} that can be used to generate {@code Month} objects in the given range: [lower, upper].</p>
     *
     * @param lower The lower bound of the interval.
     * @param upper The upper bound of the interval.
     *
     * @return A new {@code MockUnitMonth}
     */
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

    /**
     * <p>Returns a new {@code MockUnitMonth} that can be used to generate {@code Month} objects in a given range: [lower, upper)</p>
     *
     * @param lower The upper bound of the interval.
     * @param upper The lower bound of the interval.
     *
     * @return A new {@code MockUnitMonth} object.
     */
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

    /**
     * <p>Returns a new {@code MockUnitMonth} that can be used to generate {@code Month} objects before a given month: [January, before).</p>
     *
     * @param before The upper bound of the interval.
     * @return A new {@code MockUnitMonth} object.
     */
    public MockUnitMonth before(Month before) {
        notNull(before, "before");
        isTrue(before.getValue()-1>0, BEFORE_MONTH_DIFFERENT_THAN_JANUARY);
        return range(Month.values()[0], before);
    }

    /**
     * <p>Returns a new {@code MockUnitMonth} that can be used to generate {@code Month} objects after a given month: (after, December].</p>
     *
     * @param after The lower bound of the interval.
     * @return A new {@code MockUnitMonth} object.
     */
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
