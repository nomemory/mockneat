package net.andreinc.mockneat.abstraction;

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
