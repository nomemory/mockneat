package net.andreinc.mockneat.unit.time;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitLocalDate;

import java.time.LocalDate;
import java.time.Month;
import java.util.function.Supplier;

import static java.time.LocalDate.*;
import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class LocalDates extends MockUnitBase implements MockUnitLocalDate {

    public static final LocalDate EPOCH_START = ofEpochDay(0);

    /**
     * <p>Returns a {@code LocalDates} object that can be used to generate arbitrary {@code LocalDate} objects.</p>
     *
     * @return A re-usable {@code LocalDates} object. The {@code LocalDates} implements {@code MockUnitLocalDate}.
     */
    public static LocalDates localDates() {
        return MockNeat.threadLocal().localDates();
    }

    protected LocalDates() { }

    public LocalDates(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<LocalDate> supplier() {
        return between(EPOCH_START, now())::val;
    }

    /**
     * <p>This method can be used to generate date objects from the current year.</p>
     *
     * @return A new {@code MockUnitLocalDate} object.
     */
    public MockUnitLocalDate thisYear() {
        Supplier<LocalDate> supp = () -> {
            int year = now().getYear();
            int maxDays = now().lengthOfYear() + 1;
            int randDay = mockNeat.ints().range(1, maxDays).val();
            return ofYearDay(year, randDay);
        };
        return () -> supp;
    }

    /**
     * <p>This method can be used to generate date objects from the current month.</p>
     *
     * @return A new {@code MockUnitLocalDate} object.
     */
    public MockUnitLocalDate thisMonth() {
        Supplier<LocalDate> supp = () -> {
            int year = now().getYear();
            Month month = now().getMonth();
            int lM = now().lengthOfMonth() + 1;
            int randDay = mockNeat.ints().range(1, lM).val();
            return of(year, month, randDay);
        };
        return () -> supp;
    }

    /**
     * <p>This method can be used to generate date objects in the defined range (lowerDate, upperDate).</p>
     *
     * @param lowerDate The lower limit of the interval.
     * @param upperDate The upper limit of the interval.
     * @return A new {@code MockUnitLocalDate} object.
     */
    public MockUnitLocalDate between(LocalDate lowerDate, LocalDate upperDate) {
        notNull(lowerDate, "lowerDate");
        notNull(upperDate, "upperDate");
        isTrue(lowerDate.compareTo(upperDate)<0,
                LOWER_DATE_SMALLER_THAN_UPPER_DATE,
                "lower", lowerDate,
                "upper", upperDate);
        Supplier<LocalDate> supp = () -> {
            long lowerEpoch = lowerDate.toEpochDay();
            long upperEpoch = upperDate.toEpochDay();
            long diff = upperEpoch - lowerEpoch;
            long randEpoch = mockNeat.longs().range(0L, diff).val();
            return ofEpochDay(lowerEpoch + randEpoch);
        };
        return ()-> supp;
    }

    /**
     * <p>This method can be used to generate date objects in the future.</p>
     *
     * @param maxDate The maxDate from the future.
     * @return A new {@code MockUnitLocalDate} object.
     */
    public MockUnitLocalDate future(LocalDate maxDate) {
        notNull(maxDate, "maxDate");
        isTrue(maxDate.compareTo(MAX.minusDays(1))<=0,
                MAX_DATE_NOT_BIGGER_THAN,
                "max", maxDate,
                "date", MAX.minusDays(1));
        isTrue(maxDate.plusDays(1).compareTo(now())>0,
                MAX_DATE_DIFFERENT_THAN_NOW,
                "max", maxDate,
                "now", now());
        return between(now().plusDays(1), maxDate.plusDays(1));
    }

    /**
     * <p>This method can be used to generate date objects in the past.</p>
     *
     * @param minDate The minDate from the past.
     * @return A new {@code MockUnitLocalDate}.
     */
    public MockUnitLocalDate past(LocalDate minDate) {
        notNull(minDate,  "minDate");
        isTrue(minDate.compareTo(MIN)>0,
                MIN_DATE_BIGGER_THAN,
                "min", minDate,
                "date", MIN);
        isTrue(minDate.minusDays(1).compareTo(now())<0,
                MIN_DATE_DIFFERENT_THAN_NOW,
                "min", minDate,
                "now", now());
        return between(minDate, now());
    }
}
