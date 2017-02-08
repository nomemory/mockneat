package com.mockneat.random;

import com.mockneat.random.randunit.ToString;
import org.junit.Test;

import java.util.List;

import static com.mockneat.random.RandTestConstants.RAND;
import static com.mockneat.random.RandTestConstants.RANDS;
import static com.mockneat.random.RandTestConstants.RU_CYCLES;
import static com.mockneat.utils.FunctUtils.cycle;
import static java.lang.Character.isDigit;
import static java.util.Arrays.stream;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RandUnitTest {

    @Test public void testStrValBools() throws Exception {
        cycle(RU_CYCLES, () -> {
            stream(RANDS).forEach(r -> {
                String b = r.bools().valStr();
                assertTrue(b.equals("false") || b.equals("true"));
            });
        });
    }

    @Test public void testStrValChars() throws Exception {
        cycle(RU_CYCLES, () -> {
            stream(RANDS).forEach(r -> {
                String c = r.chars().digits().valStr();
                assertTrue(c.length()==1 && isDigit(c.charAt(0)));
            });
        });
    }

    @Test public void testStrValDoubles() throws Exception {
        cycle(RU_CYCLES, () -> {
            stream(RANDS).forEach(r -> {
                String d = r.doubles().valStr();
                try { Double.valueOf(d); }
                catch (NumberFormatException e) { fail(); }
            });
        });
    }

    @Test public void testStrValInts() throws Exception {
        cycle(RU_CYCLES, () -> {
            stream(RANDS).forEach(r -> {
                String i = r.ints().valStr();
                try { Integer.valueOf(i); }
                catch (NumberFormatException e) { fail(); }
            });
        });
    }

    @Test public void testStrValLongs() throws Exception {
        cycle(RU_CYCLES, () -> {
            stream(RANDS).forEach(r -> {
                String l = r.longs().valStr();
                try { Long.valueOf(l); }
                catch (NumberFormatException e) { fail(); }
            });
        });
    }

    @Test public void testStrValFloats() throws Exception {
        cycle(RU_CYCLES, () -> {
            stream(RANDS).forEach(r -> {
                String f = r.floats().valStr();
                try { Float.valueOf(f); }
                catch (NumberFormatException e) { fail(); }
            });
        });
    }

    @Test public void testStrValClass() throws Exception {
        final List<ToString> arr = RAND.compose().object(ToString.class).list(100).val();
        cycle(RU_CYCLES, () ->
            stream(RANDS).forEach(r ->
                    assertTrue(r.objs().from(arr).valStr().equals(ToString.CONST))));
    }
}
