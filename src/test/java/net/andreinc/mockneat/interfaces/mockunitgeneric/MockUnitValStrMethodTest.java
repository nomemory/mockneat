package net.andreinc.mockneat.interfaces.mockunitgeneric;

import org.junit.Test;

import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.Constants.MOCK_CYCLES;
import static java.lang.Character.isDigit;
import static java.util.Arrays.stream;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MockUnitValStrMethodTest {

    @Test
    public void testValStrNulls() throws Exception {
        loop(MOCK_CYCLES, MOCKS, m -> m.from(new Integer[]{ null, null, null }).valStr(),
                s -> assertTrue("".equals(s)));
    }

    @Test
    public void testStrValBools() throws Exception {
        loop(MOCK_CYCLES, () -> {
            stream(MOCKS).forEach(r -> {
                String b = r.bools().valStr();
                assertTrue(b.equals("false") || b.equals("true"));
            });
        });
    }

    @Test
    public void testStrValChars() throws Exception {
        loop(MOCK_CYCLES, () -> {
            stream(MOCKS).forEach(r -> {
                String c = r.chars().digits().valStr();
                assertTrue(c.length()==1 && isDigit(c.charAt(0)));
            });
        });
    }

    @Test
    public void testStrValDoubles() throws Exception {
        loop(MOCK_CYCLES, () -> {
            stream(MOCKS).forEach(r -> {
                String d = r.doubles().valStr();
                try { Double.valueOf(d); }
                catch (NumberFormatException e) { fail(); }
            });
        });
    }

    @Test
    public void testStrValInts() throws Exception {
        loop(MOCK_CYCLES, () -> {
            stream(MOCKS).forEach(r -> {
                String i = r.ints().valStr();
                try { Integer.valueOf(i); }
                catch (NumberFormatException e) { fail(); }
            });
        });
    }

    @Test
    public void testStrValLongs() throws Exception {
        loop(MOCK_CYCLES, () -> {
            stream(MOCKS).forEach(r -> {
                String l = r.longs().valStr();
                try { Long.valueOf(l); }
                catch (NumberFormatException e) { fail(); }
            });
        });
    }

    @Test
    public void testStrValFloats() throws Exception {
        loop(MOCK_CYCLES, () -> {
            stream(MOCKS).forEach(r -> {
                String f = r.floats().valStr();
                try { Float.valueOf(f); }
                catch (NumberFormatException e) { fail(); }
            });
        });
    }

}
