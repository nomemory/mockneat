package net.andreinc.mockneat.abstraction;


import net.andreinc.mockneat.utils.DateUtils;

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
     * Use {@code mapToDate()} method instead.
     *
     * @return A new {@code MockUnit<java.util.Date>}.
     */
    @Deprecated
    default MockUnit<Date> toUtilDate() {
        return () -> ifSupplierNotNullDo(supplier(),
                localDate -> from(localDate
                                    .atStartOfDay(ZoneId.systemDefault())
                                    .toInstant()));
    }

    /**
     * <p>Transforms an existing {@code MockUnitLocalDate} into a {@code MockUnit<java.util.Date>}.</p>
     *
     * @return A new {@code MockUnit<java.util.Date>}.
     */
    default MockUnit<Date> mapToDate() {
        return () -> ifSupplierNotNullDo(supplier(),
                DateUtils::convertToDateViaInstant);
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
        notNull(format, "format");
        notNull(locale, "locale");
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format, locale);
        return () -> ifSupplierNotNullDo(supplier(), localDate -> localDate.format(dtf));
    }

    @Deprecated
    default MockUnitString display(DateTimeFormatter dtf, Locale locale) {
        notNull(dtf, "dtf");
        notNull(locale, "locale");
        return () -> ifSupplierNotNullDo(supplier(), dtf::format);
    }

    @Deprecated
    default MockUnitString display(DateTimeFormatter dtf) {
        return display(dtf, Locale.getDefault());
    }
}
