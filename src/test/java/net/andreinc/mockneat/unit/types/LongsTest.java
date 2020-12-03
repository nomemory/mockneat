package net.andreinc.mockneat.unit.types;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static net.andreinc.mockneat.Constants.*;
import static java.util.Arrays.asList;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LongsTest {

    @Test
    public void testNextLongInCorrectRange() {
        long upperBound = 100;
        loop(FLOATS_CYCLES,
                MOCKS,
                r -> r.longs().bound(upperBound).val(),
                num -> assertTrue(num >= 0 && num < upperBound));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNegativeNotBound() {
        long upperBound = -100;
        M.longs().bound(upperBound).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextLongNullNotBound() {
        M.longs().bound(null).val();
    }

    @Test
    public void testNextLongInCorrectRange2() {
        long upperBound = 1L;
        loop(LONGS_CYCLES,
                MOCKS,
                r -> r.longs().bound(upperBound).val(),
                num -> assertEquals(0L, (long) num));
    }

    @Test
    public void testNextLongInCorrectRange3() {
        Long upperBound = Long.MAX_VALUE;
        loop(LONGS_CYCLES,
                MOCKS,
                r -> r.longs().bound(upperBound).val(),
                num -> assertTrue(num < upperBound));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNegativeNotBound2() {
        long lowerBound = -1;
        long upperBound = 100;
        M.longs().range(lowerBound, upperBound).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNegativeNotBound3() {
        long lowerBound = 100;
        long upperBound = -5;
        M.longs().range(lowerBound, upperBound).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextLongNullNotBound2() {
        M.longs().range(null, 10L).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextLongNullNotBound3() {
        M.longs().range(null, null).val();
    }

    @Test
    public void testNextLongInCorrectRange4() {
        long lowerBound = 5;
        long upperBound = 8;
        loop(LONGS_CYCLES,
                MOCKS,
                r -> r.longs().range(lowerBound, upperBound).val(),
                num -> assertTrue((num >= lowerBound) && (num < upperBound)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNonEqualBounds() {
        long lowerBound = 10, upperBound = 10;
        M.longs().range(lowerBound, upperBound).val();
    }

    @Test
    public void testNextLongCorrectValues() {
        long lowerBound = 10, upperBound = lowerBound + 1;
        loop(LONGS_CYCLES,
                MOCKS,
                r -> r.longs().range(lowerBound, upperBound).val(),
                num -> assertEquals((long) num, lowerBound));
    }

    @Test
    public void testNextLongInCorrectRange5() {
        long lowerBound = 0;
        long upperBound = Long.MAX_VALUE;
        loop(LONGS_CYCLES,
                MOCKS,
                r -> r.longs().range(lowerBound, upperBound).val(),
                num -> assertTrue(num >= lowerBound && num < upperBound));
    }

    @Test
    public void testNextCorrectValues() {
        long[] alphabet = { 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000 };
        Set<Long> helperSet = new HashSet<>(asList(ArrayUtils.toObject(alphabet)));
        loop(LONGS_CYCLES,
                MOCKS,
                r -> r.longs().from(alphabet).val(),
                num -> assertTrue(helperSet.contains(num)));
    }

    @Test(expected = NullPointerException.class)
    public void testNextNulLNotAlphabet() {
        M.longs().from(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextEmptyArrayNotAlphabet() {
        long[] alphabet = new long[]{};
        M.longs().from(alphabet).val();
    }

    /**
     * Test rangeClosed()
     */

    @Test(expected = IllegalArgumentException.class)
    public void testLongRangeClosedNegativeLowerBound() {
        M.longs().rangeClosed(-1, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLongRangeClosedNegativeUpperBound() {
        M.longs().rangeClosed(0, -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLongRangeClosedErroneusInterval() {
        M.longs().rangeClosed(Long.MAX_VALUE - 1, Long.MAX_VALUE - 5);
    }

    @Test
    public void testLongRangeClosedLowRange() {
        loop(LONGS_CYCLES, MOCKS, m -> m.longs().rangeClosed(0, 1).val(),
                i -> assertTrue(i == 0 || i == 1));
    }

    @Test
    public void testLongRangeClosedHighRange() {
        loop(LONGS_CYCLES,
                MOCKS,
                m -> m.longs().rangeClosed(Long.MAX_VALUE - 1, Long.MAX_VALUE).val(),
                i -> assertTrue(i == Long.MAX_VALUE || i == (Long.MAX_VALUE - 1)));
    }

    @Test
    public void testLongRangeClosed() {
        loop(LONGS_CYCLES,
                MOCKS,
                m -> m.longs().rangeClosed(0, 100).get(),
                i -> assertTrue(i >= 0 && i <= 100));
    }

    /** Test lowerBound **/

    @Test(expected = IllegalArgumentException.class)
    public void testLowerBoundNegativeBound() {
        M.longs().lowerBound(-1L);
    }

    @Test
    public void testLowerBoundZeroLowerBound() {
        M.longs().lowerBound(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLowerBoundIntegerMax() {
        M.longs().lowerBound(Long.MAX_VALUE);
    }

    @Test
    public void testLowerBound() {
        loop(LONGS_CYCLES, MOCKS,m -> m.longs().lowerBound(1000).get(), i -> assertTrue(i >= 1000));
    }

    @Test
    public void testLowerBoundHighInterval() {
        loop(LONGS_CYCLES,
                MOCKS,
                m -> m.longs().lowerBound(Long.MAX_VALUE - 1L).get(),
                i -> assertTrue(i == Long.MAX_VALUE || i == (Long.MAX_VALUE - 1)));
    }
}

