package net.andreinc.mockneat.unit.types;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.MAX_VALUE;
import static java.util.Arrays.asList;
import static net.andreinc.mockneat.Constants.INTS_CYCLES;
import static net.andreinc.mockneat.Constants.M;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.ArrayUtils.toObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IntsTest {

    @Test
    public void testNextIntegerInCorrectRange() {
        int upperBound = 100;
        loop(INTS_CYCLES,
                MOCKS,
                r -> r.ints().bound(upperBound).val(),
                num -> assertTrue(num < 100 && num >= 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextIntegerNegativeNotBound() {
        int upperBound = -100;
        M.ints().bound(upperBound).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextIntegerNullNotBound() {
        M.ints().bound(null).val();
    }

    @Test
    public void testNextIntegerInCorrectRange2() {
        int upperBound = 1;
        loop(INTS_CYCLES,
                MOCKS,
                r -> r.ints().bound(upperBound).val(),
                num -> assertEquals(0, (int) num));
    }

    @Test
    public void testNextIntegerInCorrectRange3() {
        Integer upperBound = MAX_VALUE;
        loop(INTS_CYCLES,
                MOCKS,
                r -> r.ints().bound(upperBound).val(),
                num -> assertTrue(num < upperBound));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextIntegerNegativeNotBound2() {
        int lowerBound = -1;
        int upperBound = 100;
        M.ints().range(lowerBound, upperBound).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextIntegerNegativeNotBound3() {
        int lowerBound = 100;
        int upperBound = -5;
        M.ints().range(lowerBound, upperBound).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextIntegerNullNotBound2() {
        M.ints().range(null, 10).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextIntegerNullNotBound3() {
        M.ints().range(10, null).val();
    }

    @Test
    public void testNextIntegerInCorrectRange4() {
        int lowerBound = 5;
        int upperBound = 8;
        loop(INTS_CYCLES,
                MOCKS,
                r -> r.ints().range(lowerBound, upperBound).val(),
                num -> assertTrue((num >= lowerBound) && (num < upperBound)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextIntegerNonEqualBounds() {
        int lowerBound = 10, upperBound = 10;
        M.ints().range(lowerBound, upperBound).val();
    }

    @Test
    public void testNextIntegerCorrectValues() {
        int lowerBound = 10, upperBound = lowerBound + 1;
        loop(INTS_CYCLES,
                MOCKS,
                r -> r.ints().range(lowerBound, upperBound).val(),
                num -> assertEquals((int) num, lowerBound));
    }

    @Test
    public void testNextIntegerInCorrectRange5() {
        int lowerBound = 0;
        int upperBound = MAX_VALUE;
        loop(INTS_CYCLES,
                MOCKS,
                r -> r.ints().range(lowerBound, upperBound).val(),
                num -> assertTrue(num >= lowerBound && num < upperBound));
    }

    @Test
    public void testNextCorrectValues() {
        int[] alphabet = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
        Set<Integer> helperSet = new HashSet<>(asList(toObject(alphabet)));
        loop(INTS_CYCLES,
                MOCKS,
                r -> r.ints().from(alphabet).val(),
                num -> assertTrue(helperSet.contains(num)));
    }

    @Test(expected = NullPointerException.class)
    public void testNextNulLNotAlphabet() {
        M.ints().from(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextEmptyArrayNotAlphabet() {
        int[] alphabet = new int[]{};
        M.ints().from(alphabet).val();
    }

    /**
     * Test rangeClosed()
     */

    @Test(expected = IllegalArgumentException.class)
    public void testIntegerRangeClosedNegativeLowerBound() {
        M.ints().rangeClosed(-1, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIntegerRangeClosedNegativeUpperBound() {
        M.ints().rangeClosed(0, -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIntegerRangeClosedErroneusInterval() {
        M.ints().rangeClosed(MAX_VALUE - 1, MAX_VALUE - 5);
    }

    @Test
    public void testIntegerRangeClosedLowRange() {
        loop(INTS_CYCLES, MOCKS, m -> m.ints().rangeClosed(0, 1).val(),
                i -> assertTrue(i == 0 || i == 1));
    }

    @Test
    public void testIntegerRangeClosedHighRange() {
        loop(INTS_CYCLES, MOCKS, m -> m.ints().rangeClosed(MAX_VALUE - 1, MAX_VALUE).val(),
                i -> assertTrue(i == MAX_VALUE || i == (MAX_VALUE - 1)));
    }

    @Test
    public void testIntegerRangeClosed() {
        loop(INTS_CYCLES, MOCKS,
                m -> m.ints().rangeClosed(0, 100).get(),
                i -> assertTrue(i >= 0 && i <= 100));
    }

    /** Test lowerBound **/

    @Test(expected = IllegalArgumentException.class)
    public void testLowerBoundNegativeBound() {
        M.ints().lowerBound(-1);
    }

    @Test
    public void testLowerBoundZeroLowerBound() {
        M.ints().lowerBound(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLowerBoundIntegerMax() {
        M.ints().lowerBound(Integer.MAX_VALUE);
    }

    @Test
    public void testLowerBound() {
        loop(INTS_CYCLES, MOCKS,
                m -> m.ints().lowerBound(1000).get(),
                i -> assertTrue(i >= 1000));
    }

    @Test
    public void testLowerBoundHighInterval() {
        loop(INTS_CYCLES, MOCKS,
                m -> m.ints().lowerBound(MAX_VALUE - 1).get(),
                i -> assertTrue(i == MAX_VALUE || i == (MAX_VALUE - 1)));
    }
}
