package net.andreinc.mockneat.abstraction;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.function.Supplier;

import static java.time.format.TextStyle.FULL;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public interface MockUnitMonth extends MockUnit<Month> {

    /**
     * <p>Transforms the current {@code MockUnitMonth} into a {@code MOckUnitString} representing the textual representation of the {@code Month} (Eg.: 'Jan' for January).</p>
     *
     * @param textStyle The style used to represent the day of the week. (Eg.: If we use {@code TextStyle.SHORT} the value for 'January' will be 'Jan')
     * @param locale The locale to use in order to represent the days of the week. (Eg.: If we use {@code Locale.ENGLISH} and {@code TextStyle.FULL} the value for January will be 'January').
     * @return A new {@code MockUnitString}
     */
    default MockUnitString display(TextStyle textStyle, Locale locale) {
        notNull(textStyle, "textStyle");
        notNull(locale, "locale");
        Supplier<String> supp = () -> supplier().get().getDisplayName(textStyle, locale);
        return () -> supp;
    }

    /**
     * <p>Transforms the current {@code MockUnitMonth} into a {@code MOckUnitString} representing the textual representation of the {@code Month} (Eg.: 'Jan' for January).</p>
     *
     * <p><em>Note:</em> The default locale will be {@code Locale.ENGLISH}</p>
     *
     * @param textStyle The style used to represent the day of the week. (Eg.: If we use {@code TextStyle.SHORT} the value for 'January' will be 'Jan')
     * @return A new {@code MockUnitString}
     */
    default MockUnitString display(TextStyle textStyle) {
        return display(textStyle, Locale.ENGLISH);
    }

    /**
     * <p>Transforms the current {@code MockUnitMonth} into a {@code MOckUnitString} representing the textual representation of the {@code Month} (Eg.: 'Jan' for January).</p>
     *
     * <p><em>Note:</em> The default locale will be {@code Locale.ENGLISH}</p>
     *
     * <p><em>Note:</em> The default locale will be {@code TextStyle.FULL}</p>
     * @return A new {@code MockUnitString}
     */
    default MockUnitString display() {
        return display(FULL);
    }
}
