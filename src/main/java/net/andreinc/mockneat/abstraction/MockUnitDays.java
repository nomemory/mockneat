package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.utils.ValidationUtils;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

import static java.time.format.TextStyle.FULL;
import static net.andreinc.mockneat.utils.MockUnitUtils.ifSupplierNotNullDo;

public interface MockUnitDays extends MockUnit<DayOfWeek> {

    /**
     * <p>Transforms the current {@code MockUnitDays} into a {@code MOckUnitString} representing the textual representation of the {@code DayOfTheWeek} (Eg.: 'Mon' for 'Monday').</p>
     *
     * @param textStyle The style used to represent the day of the week. (Eg.: If we use {@code TextStyle.SHORT} the value for 'Monday' will be 'Mon')
     * @param locale The locale to use in order to represent the days of the week. (Eg.: If we use {@code Locale.FRANCE} and {@code TextStyle.FULL} the value for 'Monday' will be 'lunedi').
     * @return A new {@code MockUnitString}
     */
    default MockUnitString display(TextStyle textStyle, Locale locale) {
        ValidationUtils.notNull(textStyle, "textStyle");
        ValidationUtils.notNull(locale, "locale");
        return () ->
                ifSupplierNotNullDo(supplier(), s -> s.getDisplayName(textStyle, locale));
    }

    /**
     * <p>Transforms the current {@code MockUnitDays} into a {@code MOckUnitString} representing the textual representation of the {@code DayOfTheWeek} (Eg.: 'Mon' for 'Monday').</p>
     *
     * <p><em>Note:</em> The locale used by default is {@code Locale.ENGLISH}.</p>
     *
     * @param textStyle The style used to represent the day of the week. (Eg.: If we use {@code TextStyle.SHORT} the value for 'Monday' will be 'Mon')
     * @return A new {@code MockUnitString}
     */
    default MockUnitString display(TextStyle textStyle) {
        ValidationUtils.notNull(textStyle, "textStyle");
        return display(textStyle, Locale.ENGLISH);
    }

    /**
     * <p>Transforms the current {@code MockUnitDays} into a {@code MOckUnitString} representing the textual representation of the {@code DayOfTheWeek} (Eg.: 'Mon' for 'Monday').</p>
     *
     * <p><em>Note:</em> The locale used by default is {@code Locale.ENGLISH}.</p>
     *
     * <p><em>Note:</em> The default text style is {@code TextStyle.FULL} </p>
     *
     * @return A new {@code MockUnitString}
     */
    default MockUnitString display() {
        return display(FULL);
    }
}
