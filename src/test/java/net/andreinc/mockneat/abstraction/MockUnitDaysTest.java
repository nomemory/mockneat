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

import org.junit.Test;


import static net.andreinc.mockneat.Constants.*;
import static java.time.format.TextStyle.FULL;
import static java.time.format.TextStyle.SHORT;
import static java.util.Locale.FRANCE;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class MockUnitDaysTest {

    @Test(expected = NullPointerException.class)
    public void testDisplayNullTextStyle() {
        M.days().display(null, FRANCE).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDisplayNullLocale() {
        M.days().display(SHORT, null).val();
    }

    @Test
    public void testDisplay() {
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.days().display(FULL, FRANCE).val(),
                ds ->
                    assertTrue(
                        "lundi".equals(ds) ||
                        "mardi".equals(ds) ||
                        "mercredi".equals(ds) ||
                        "jeudi".equals(ds) ||
                        "vendredi".equals(ds) ||
                        "samedi".equals(ds) ||
                        "dimanche".equals(ds)
                    )
        );
    }

    @Test(expected = NullPointerException.class)
    public void textDisplayOverloadedNullTextStyle() {
        M.days().display(null).val();
    }

    @Test
    public void testDisplayOverloaded() {
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.days().display(SHORT).val(),
                d -> assertTrue(
                        "Mon".equals(d) ||
                        "Tue".equals(d) ||
                        "Wed".equals(d) ||
                        "Thu".equals(d) ||
                        "Fri".equals(d) ||
                        "Sat".equals(d) ||
                        "Sun".equals(d)
                )

        );
    }

    @Test
    public void testDisplaySimple() {
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.days().display().val(),
                d -> assertTrue(
                        "Monday".equals(d) ||
                        "Tuesday".equals(d) ||
                        "Wednesday".equals(d) ||
                        "Thursday".equals(d) ||
                        "Friday".equals(d) ||
                        "Saturday".equals(d) ||
                        "Sunday".equals(d)
                )
        );
    }
}
