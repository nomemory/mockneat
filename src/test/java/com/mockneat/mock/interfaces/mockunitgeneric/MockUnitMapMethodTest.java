package com.mockneat.mock.interfaces.mockunitgeneric;


import com.mockneat.mock.interfaces.MockUnit;
import org.junit.Test;

import java.util.function.Function;

import static com.mockneat.mock.Constants.M;
import static java.util.Arrays.stream;

import static com.mockneat.mock.Constants.MOCKS;
import static com.mockneat.mock.Constants.MOCK_CYCLES;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 13/02/2017.
 */
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
                        .range(0, 5)
                        .mapToLong(x -> x +5)
                        .longStream().val()
                        .limit(10)
                        .forEach(x -> assertTrue(x >= 5l && x < 10l))));
    }

    @Test(expected = NullPointerException.class)
    public void testMapToLongNullFunct() {
        M.longs().range(0l, 5l).mapToLong(null).val();
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

    @Test
    public void testMap() {
        Function<Object, String> f = (x) -> x.toString() + "x";
        MockUnit r = M.ints().from(new int[]{5});
        int i = 100;
        while(i-->0) r = r.map(f);
        String result = r.valStr();
        for(int j = 1; j < result.length(); ++j) {
            assertTrue(result.charAt(j)=='x');
        }
    }

    @Test(expected = NullPointerException.class)
    public void testMapFunct() {
        M.ints().from(new int[]{5}).map(null).val();
    }
}
