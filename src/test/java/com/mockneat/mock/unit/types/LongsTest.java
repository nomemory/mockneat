package com.mockneat.mock.unit.types;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.mockneat.mock.Constants.*;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class LongsTest {

    @Test
    public void testNextLongInCorrectRange() throws Exception {
        long upperBound = 100;
        loop(FLOATS_CYCLES,
                RANDS,
                r -> r.longs().bound(upperBound).val(),
                num -> assertTrue(num >= 0 && num < upperBound));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNegativeNotBound() throws Exception {
        long upperBound = -100;
        RAND.longs().bound(upperBound).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextLongNullNotBound() throws Exception {
        Long bound = null;
        RAND.longs().bound(bound).val();
    }

    @Test
    public void testNextLongInCorrectRange2() throws Exception {
        long upperBound = 1L;
        loop(LONGS_CYCLES,
                RANDS,
                r -> r.longs().bound(upperBound).val(),
                num -> assertTrue(num.equals(0L)));
    }

    @Test
    public void testNextLongInCorrectRange3() throws Exception {
        Long upperBound = Long.MAX_VALUE;
        loop(LONGS_CYCLES,
                RANDS,
                r -> r.longs().bound(upperBound).val(),
                num -> assertTrue(num < upperBound));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNegativeNotBound2() throws Exception {
        long lowerBound = -1;
        long upperBound = 100;
        RAND.longs().range(lowerBound, upperBound).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNegativeNotBound3() throws Exception {
        long lowerBound = 100;
        long upperBound = -5;
        RAND.longs().range(lowerBound, upperBound).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextLongNullNotBound2() throws Exception {
        Long l = null;
        RAND.longs().range(l, 10L).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextLongNullNotBound3() throws Exception {
        Long l = null;
        RAND.longs().range(10L, l).val();
    }

    @Test
    public void testNextLongInCorrectRange4() throws Exception {
        long lowerBound = 5;
        long upperBound = 8;
        loop(LONGS_CYCLES,
                RANDS,
                r -> r.longs().range(lowerBound, upperBound).val(),
                num -> assertTrue((num >= lowerBound) && (num < upperBound)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNonEqualBounds() throws Exception {
        long lowerBound = 10, upperBound = 10;
        RAND.longs().range(lowerBound, upperBound).val();
    }

    @Test
    public void testNextLongCorrectValues() throws Exception {
        long lowerBound = 10, upperBound = lowerBound + 1;
        loop(LONGS_CYCLES,
                RANDS,
                r -> r.longs().range(lowerBound, upperBound).val(),
                num -> num.equals(lowerBound));
    }

    @Test
    public void testNextLongInCorrectRange5() throws Exception {
        long lowerBound = 0;
        long upperBound = Long.MAX_VALUE;
        loop(LONGS_CYCLES,
                RANDS,
                r -> r.longs().range(lowerBound, upperBound).val(),
                num -> assertTrue(num >= lowerBound && num < upperBound));
    }

    @Test
    public void testNextCorrectValues() throws Exception {
        long[] alphabet = { 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000 };
        Set<Long> helperSet = new HashSet<>(asList(ArrayUtils.toObject(alphabet)));
        loop(LONGS_CYCLES,
                RANDS,
                r -> r.longs().from(alphabet).val(),
                num -> assertTrue(helperSet.contains(num)));
    }

    @Test(expected = NullPointerException.class)
    public void testNextNulLNotAlphabet() throws Exception {
        long[] alphabet = null;
        RAND.longs().from(alphabet).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextEmptyArrayNotAlphabet() throws Exception {
        long[] alphabet = new long[]{};
        RAND.longs().from(alphabet).val();
    }
}
