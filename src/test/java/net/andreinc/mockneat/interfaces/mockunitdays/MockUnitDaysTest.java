package net.andreinc.mockneat.interfaces.mockunitdays;

import org.junit.Test;

import java.time.format.TextStyle;

import static net.andreinc.mockneat.Constants.*;
import static java.time.format.TextStyle.FULL;
import static java.time.format.TextStyle.SHORT;
import static java.util.Locale.FRANCE;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 07/03/2017.
 */
public class MockUnitDaysTest {

    @Test(expected = NullPointerException.class)
    public void testDisplayNullTextStyle() throws Exception {
        M.days().display(null, FRANCE).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDisplayNullLocale() throws Exception {
        M.days().display(TextStyle.SHORT, null).val();
    }

    @Test
    public void testDisplay() throws Exception {
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
    public void textDisplayOverloadedNullTextStyle() throws Exception {
        M.days().display(null).val();
    }

    @Test
    public void testDisplayOverloaded() throws Exception {
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
    public void testDisplaySimple() throws Exception {
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
