package net.andreinc.mockneat.abstraction;

import org.junit.Test;

import java.time.LocalDate;
import java.util.function.Function;

import static java.util.Arrays.stream;
import static net.andreinc.mockneat.Constants.M;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.Constants.MOCK_CYCLES;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ALL")
public class MockUnitMapMethodTest {
    @Test
    public void testMapToIntInts() {
        loop(MOCK_CYCLES, () ->
                stream(MOCKS).forEach(r ->
                    r.ints()
                        .range(0, 5)
                        .mapToInt(x -> x + 5)
                        .intStream().val()
                        .limit(10)
                        .forEach(x -> assertTrue(x >= 5 && x < 10))));
    }

    @Test(expected = NullPointerException.class)
    public void testMapToIntsNullFunct() {
        M.ints().range(0, 5).mapToInt(null).val();
    }

    @Test
    public void testMapToLong() {
        loop(MOCK_CYCLES, () ->
            stream(MOCKS).forEach(r ->
                    r.longs()
                        .range(0L, 5L)
                        .mapToLong(x -> x +5)
                        .longStream().val()
                        .limit(10)
                        .forEach(x -> assertTrue(x >= 5L && x < 10L))));
    }

    @Test(expected = NullPointerException.class)
    public void testMapToLongNullFunct() {
        M.longs().range(0L, 5L).mapToLong(null).val();
    }

    @Test
    public void testMapToDoubleDoubles() {
        loop(MOCK_CYCLES, () ->
            stream(MOCKS).forEach(r ->
                        r.doubles()
                            .range(0.5, 5.0)
                            .mapToDouble(x -> x + 5.0)
                            .doubleStream().val()
                            .limit(10)
                            .forEach(x -> assertTrue(x >= 5.0 && x < 10.0))));
    }

    @Test(expected = NullPointerException.class)
    public void testMapToDoubleNullFunct() {
        M.doubles().range(0.5, 10.0).mapToDouble(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapNullFunct() {
        M.ints().from(new int[]{ 5, 7, 8}).map(null).val();
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void testMap() {
        Function<Object, String> f = (x) -> x.toString() + "x";
        MockUnit r = M.ints().from(new int[]{5});
        int i = 100;
        while(i-->0) r = r.map(f);
        String result = r.valStr();
        for(int j = 1; j < result.length(); ++j) {
            assertEquals('x', result.charAt(j));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testMapFunct() {
        M.ints().from(new int[]{5}).map(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapToLocalDateNullDateTransformer() {
        M.ints().mapToLocalDate(null);
    }

    @Test
    public void testMapLocalDate() {
       loop(MOCK_CYCLES,
            MOCKS,
            m -> m.ints()
                  .from(new int[] { 2015, 2012, 2013 })
                  .mapToLocalDate(i -> LocalDate.of(i, 1, 1)).get(),
            ld -> assertTrue(ld.getYear() == 2015 || ld.getYear() == 2012 || ld.getYear() == 2013));
    }
}
