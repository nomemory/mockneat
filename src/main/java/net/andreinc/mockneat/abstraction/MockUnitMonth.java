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
