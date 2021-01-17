package net.andreinc.mockneat.unit.time;

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

    /**
     * <p>Returns a {@code Days} object that can be used to generate a random {@code java.time.DayOfWeek} object.</p>
     *
     * <p><em>Note: </em> By default the {@code Days} object returns a random day of the week.</p>
     *
     * @return A re-usable {@code Days} object. The {@code Days} class implements {@code MockUnitDays} interface.
     */
    public static Days days() {
        return MockNeat.threadLocal().days();
    }

    protected Days() { }

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
