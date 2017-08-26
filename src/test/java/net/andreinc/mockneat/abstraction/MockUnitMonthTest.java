package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.Constants;
import org.junit.Assert;
import org.junit.Test;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static java.util.Arrays.asList;
import static net.andreinc.mockneat.Constants.M;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;

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

public class MockUnitMonthTest {

    @Test(expected = NullPointerException.class)
    public void testDisplayNullTextStyle() {
        M.months().display(null, Locale.getDefault()).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDisplayNullLocale() {
        M.months().display(TextStyle.FULL_STANDALONE, null).val();
    }

    @Test
    public void testDisplay() {

        final Set<String> possibleResults =
                new HashSet<>(asList("Jan", "Feb", "Mar"));

        loop(
                Constants.MONTH_CYCLES,
                Constants.MOCKS,
                mock -> mock.months()
                            .before(Month.APRIL)
                            .display(TextStyle.SHORT, Locale.ENGLISH)
                            .val(),
                month -> Assert.assertTrue(possibleResults.contains(month))
        );
    }

    @Test
    public void testDisplayDefaultLocale() {

        final Set<String> possibleResults =
                new HashSet<>(asList("Jan", "Feb", "Mar"));

        loop(
                Constants.MONTH_CYCLES,
                Constants.MOCKS,
                mock -> mock.months().before(Month.APRIL).display(TextStyle.SHORT).val(),
                month -> Assert.assertTrue(possibleResults.contains(month))
        );
    }


    @Test
    public void testDisplayDefaultLocaleAndDefaultTextStyle() {
        final Set<String> possibleResults =
                new HashSet<>(asList("January", "February", "March"));

        loop(
                Constants.MONTH_CYCLES,
                Constants.MOCKS,
                mock -> mock.months().before(Month.APRIL).display().val(),
                month -> Assert.assertTrue(possibleResults.contains(month))
        );
    }
}
