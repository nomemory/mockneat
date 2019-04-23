package net.andreinc.mockneat.unit.time;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitDate;

import java.util.Date;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.DateUtils.convertToLocalDateViaMilisecond;
import static net.andreinc.mockneat.utils.ValidationUtils.LOWER_DATE_SMALLER_THAN_UPPER_DATE;
import static net.andreinc.mockneat.utils.ValidationUtils.isTrue;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Dates extends MockUnitBase implements MockUnitDate {

    public static Dates dates() {
        return MockNeat.threadLocal().dates();
    }

    public Dates(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<Date> supplier() {
        return mockNeat
                .localDates()
                .mapToDate()
                .supplier();
    }

    /**
     * <p>This method can be used to generate date objects from the current year.</p>
     *
     * @return A new {@code MockUnitDate} object.
     */
    public MockUnitDate thisYear() {
        return mockNeat
                .localDates()
                .thisYear()
                .mapToDate();
    }

    /**
     * <p>This method can be used to generate date objects from the current month.</p>
     *
     * @return A new {@code MockUnitDate} object.
     */
    public MockUnitDate thisMonth() {
        return mockNeat
                .localDates()
                .thisMonth()
                .mapToDate();
    }

    /**
     * <p>This method can be used to generate date objects in the defined range (lowerDate, upperDate).</p>
     *
     * @param lowerDate The lower limit of the interval.
     * @param upperDate The upper limit of the interval.
     * @return A new {@code MockUnitDate} object.
     */
    public MockUnitDate between(Date lowerDate, Date upperDate) {
        notNull(lowerDate, "lowerDate");
        notNull(upperDate, "upperDate");
        isTrue(lowerDate.compareTo(upperDate) < 0,
                     LOWER_DATE_SMALLER_THAN_UPPER_DATE,
                "lower", lowerDate,
                "upper", upperDate);

        return mockNeat
                    .localDates()
                    .between(
                            convertToLocalDateViaMilisecond(lowerDate),
                            convertToLocalDateViaMilisecond(upperDate)
                    )
                    .mapToDate();
    }

    /**
     * <p>This method can be used to generate date objects in the future.</p>
     *
     * @param maxDate The maxDate from the future.
     * @return A new {@code MockUnitDate} object.
     */
    public MockUnitDate future(Date maxDate) {
        notNull(maxDate, "maxDate");
        return mockNeat
                .localDates()
                .future(convertToLocalDateViaMilisecond(maxDate))
                .mapToDate();
    }

    /**
     * <p>This method can be used to generate date objects in the past.</p>
     *
     * @param minDate The minDate from the past.
     * @return A new {@code MockUnitDate}.
     */
    public MockUnitDate past(Date minDate) {
        notNull(minDate, "minDate");
        return mockNeat
                    .localDates()
                    .future(convertToLocalDateViaMilisecond(minDate))
                    .mapToDate();
    }
}
