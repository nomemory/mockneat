package net.andreinc.mockneat.unit.regex;

import org.junit.Test;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;

public class RegexTest {

    @Test(expected = NullPointerException.class)
    public void testNulLRegex() throws Exception {
        M.regex(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRegex() throws Exception {
        M.regex("1{").val();
    }

    @Test
    public void testFromRegex() throws Exception {
        loop(
                REGEX_CYLCES,
                MOCKS,
                m -> m.regex("[0-3]([a-c]|[e-g]{1,2})").val(),
                r -> r.matches("[0-3]([a-c]|[e-g]{1,2})")
        );
    }
}
