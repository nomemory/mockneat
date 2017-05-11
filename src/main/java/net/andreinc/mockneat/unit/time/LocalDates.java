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
import net.andreinc.mockneat.interfaces.MockUnitLocalDate;

import java.time.LocalDate;
import java.time.Month;
import java.util.function.Supplier;

import static java.time.LocalDate.*;
import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class LocalDates implements MockUnitLocalDate {

    public static final LocalDate EPOCH_START = ofEpochDay(0);

    private final MockNeat mock;

    public LocalDates(MockNeat mock) {
        this.mock = mock;
    }

    @Override
    public Supplier<LocalDate> supplier() {
        return between(EPOCH_START, LocalDate.now())::val;
    }

    public MockUnitLocalDate thisYear() {
        Supplier<LocalDate> supp = () -> {
            int year = now().getYear();
            int maxDays = now().lengthOfYear() + 1;
            int randDay = mock.ints().range(1, maxDays).val();
            return LocalDate.ofYearDay(year, randDay);
        };
        return () -> supp;
    }

    public MockUnitLocalDate thisMonth() {
        Supplier<LocalDate> supp = () -> {
            int year = now().getYear();
            Month month = now().getMonth();
            int lM = now().lengthOfMonth() + 1;
            int randDay = mock.ints().range(1, lM).val();
            return LocalDate.of(year, month, randDay);
        };
        return () -> supp;
    }

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
            long randEpoch = mock.longs().range(0, diff).val();
            return ofEpochDay(lowerEpoch + randEpoch);
        };
        return ()-> supp;
    }

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
