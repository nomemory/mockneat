package net.andreinc.mockneat.unit.user;

import org.junit.Assert;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.GENDERS_CYCLES;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;

/**
 * Created by andreinicolinciobanu on 25/03/17.
 */
public class GendersTest {
    @Test
    public void testGendersLong() throws Exception {
        loop(
                GENDERS_CYCLES,
                MOCKS,
                m -> m.genders().val(),
                gender -> Assert.assertTrue(gender.equals("Male") || gender.equals("Female"))
        );
    }

    @Test
    public void testGendersLetter() throws Exception {
        loop(
                GENDERS_CYCLES,
                MOCKS,
                m -> m.genders().letter().val(),
                gender -> Assert.assertTrue(gender.equals("M") || gender.equals("F"))
        );
    }
}
