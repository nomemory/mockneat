package net.andreinc.mockneat.abstraction;

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

import net.andreinc.mockneat.utils.ValidationUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static java.util.Date.from;
import static net.andreinc.mockneat.utils.MockUnitUtils.ifSupplierNotNullDo;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public interface MockUnitLocalDate extends MockUnit<LocalDate> {

    /**
     * <p>Transforms an existing {@code MockUnitLocalDate} into a {@code MockUnit<java.util.Date>}.</p>
     *
     * @return A new {@code MockUnit<java.util.Date>}.
     */
    default MockUnit<Date> toUtilDate() {
        return () -> ifSupplierNotNullDo(supplier(),
                localDate -> from(localDate
                                    .atStartOfDay(ZoneId.systemDefault())
                                    .toInstant()));
    }

    /**
     * <p>Transforms an existing {@code MockUnitLocalDate} into a {@code MockUnitString} - the textual representation of the date object.</p>
     *
     * @param format The format of the date. (Eg.: "yyyy:MM:dd")
     * @return A new {@code MockUnitString}
     */
    default MockUnitString display(String format) {
        return display(format, Locale.getDefault());
    }

    /**
     * <p>Transforms an existing {@code MockUnitLocalDate} into a {@code MockUnitString} - the textual representation of the date object.</p>
     *
     * @param locale the locale. (Eg.: Locale.FRANCE)
     * @param format The format of the date. (Eg.: "yyyy:MM:dd")
     * @return A new {@code MockUnitString}
     */
    default MockUnitString display(String format, Locale locale) {
        ValidationUtils.notNull(format, "format");
        ValidationUtils.notNull(locale, "locale");
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format, locale);
        return () -> ifSupplierNotNullDo(supplier(), localDate -> localDate.format(dtf));
    }

    default MockUnitString display(DateTimeFormatter dtf, Locale locale) {
        notNull(dtf, "dtf");
        notNull(locale, "locale");
        return () -> ifSupplierNotNullDo(supplier(), localDate -> dtf.format(localDate));
    }

    default MockUnitString display(DateTimeFormatter dtf) {
        return display(dtf, Locale.getDefault());
    }
}
