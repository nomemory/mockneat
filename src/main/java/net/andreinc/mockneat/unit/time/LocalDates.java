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
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

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

    public LocalDates() { }

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
            long randEpoch = mockNeat.longs().range(0, diff).val();
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
