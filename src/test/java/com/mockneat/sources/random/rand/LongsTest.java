package com.mockneat.sources.random.rand;

import com.mockneat.utils.ArrayUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.mockneat.sources.random.rand.RandTestConstants.*;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static com.mockneat.utils.ArrayUtils.toWrapperArray;
import static com.mockneat.utils.FunctUtils.cycle;
import static org.junit.Assert.assertTrue;

public class LongsTest {
    /**
     * Tests if the method:
     * >> Long nextLong(Long bound)
     * Returns random stream between [0, 100)
     *
     * @throws Exception
     */
    @Test
    public void testNextLongInCorrectRange() throws Exception {

        long upperBound = 100;

        cycle(NEXT_FLOAT_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.longs().withBound(upperBound).val())
                    .forEach(num -> assertTrue(num >= 0 && num < upperBound)));
    }

    /**
     * Tests if the method:
     * >> Long nextLong(Long bound)
     * Doesn't accept negative bounds
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNegativeNotBound() throws Exception {
        long upperBound = -100;
        RAND.longs().withBound(upperBound).val();
    }

    /**
     * Tests if the method:
     * >> Long nextLong(Long bound)
     * Doesn't accept null bounds
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNullNotBound() throws Exception {
        Long bound = null;
        RAND.longs().withBound(bound).val();
    }

    /**
     * Tests if the method:
     * >> Long nextLong(Long bound)
     * When bound == 1 returns only 0
     *
     * @throws Exception
     */
    @Test
    public void testNextLongInCorrectRange2() throws Exception {
        long upperBound = 1L;
        cycle(NEXT_LONG_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.longs().withBound(upperBound).val())
                    .forEach(num -> assertTrue(num.equals(0L))));
    }

    /**
     * Test if the method:
     * >> Long nextLong(Long bound)
     * When bound == Long.MAX_VALUE behaves normally
     * @throws Exception
     */
    @Test
    public void testNextLongInCorrectRange3() throws Exception {
        Long upperBound = Long.MAX_VALUE;
        cycle(NEXT_LONG_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.longs().withBound(upperBound).val())
                    .forEach(num -> assertTrue(num < upperBound)));
    }

    /**
     * Test if the method:
     * >> Long nextLong(Long lowerBound, Long upperBound)
     * Accepts a negative lower bound (it shouldn't)
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNegativeNotBound2() throws Exception {
        long lowerBound = -1;
        long upperBound = 100;
        RAND.longs().inRange(lowerBound, upperBound).val();
    }

    /**
     * Test if the method:
     * >> Long nextLong(Long lowerBound, Long upperBound)
     * Accepts a negative upper bound (it shouldn't)
     * */
    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNegativeNotBound3() throws Exception {
        long lowerBound = 100;
        long upperBound = -5;
        RAND.longs().inRange(lowerBound, upperBound).val();
    }

    /**
     * Tests if the method:
     * >> Long nextLong(Long bound)
     * Doesn't accept null bounds
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNullNotBound2() throws Exception {
        RAND.longs().inRange(null, 10L).val();
    }

    /**
     * Tests if the method:
     * >> Long nextLong(Long bound)
     * Doesn't accept null bounds
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNullNotBound3() throws Exception {
        RAND.longs().inRange(10L, null).val();
    }

    /**
     * Test if the method:
     * * >> Long nextLong(Long lowerBound, Long upperBound)
     * Returns numbers in the correct longerval [5, 8)
     * @throws Exception
     */
    @Test
    public void testNextLongInCorrectRange4() throws Exception {
        long lowerBound = 5;
        long upperBound = 8;
        cycle(NEXT_LONG_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.longs().inRange(lowerBound, upperBound).val())
                    .forEach( num -> assertTrue((num >= lowerBound) && (num < upperBound))));
    }

    /**
     * Test if the method:
     * >> Long nextLong(Long lowerBound, Long upperBound)
     * Doesn't accept equal lower and upper bounds.
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextLongNonEqualBounds() throws Exception {
        long lowerBound = 10, upperBound = 10;
        RAND.longs().inRange(lowerBound, upperBound).val();
    }

    /**
     * Test if the method:
     * >> Long nextLong(Long lowerBound, Long upperBound)
     * Returns always the lowerBound if upperBound = lowerBound + 1
     * @throws Exception
     */
    @Test
    public void testNextLongCorrectValues() throws Exception {
        long lowerBound = 10, upperBound = lowerBound + 1;
        cycle(NEXT_LONG_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.longs().inRange(lowerBound, upperBound).val())
                    .forEach(num -> num.equals(lowerBound)));
    }


    /**
     * Test if the method:
     * >> Long nextLong(Long lowerBound, Long upperBound)
     *
     * @throws Exception
     */
    @Test
    public void testNextLongInCorrectRange5() throws Exception {
        long lowerBound = 0;
        long upperBound = Long.MAX_VALUE;
        cycle(NEXT_LONG_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.longs().inRange(lowerBound, upperBound).val())
                    .forEach(num -> assertTrue(num >= lowerBound && num < upperBound)));
    }

    /**
     * Test if the method
     * >> Long name(long[] rChars)
     * Generates random numbers in the given rChars
     *
     * @throws Exception
     */
    @Test
    public void testNextCorrectValues() throws Exception {
        long[] alphabet = { 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000 };
        Set<Long> helperSet = new HashSet<>(asList(ArrayUtils.toWrapperArray(alphabet)));
        cycle(NEXT_LONG_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.longs().from(alphabet).val())
                    .forEach(num -> assertTrue(helperSet.contains(num))));
    }

    /**
     * Test if the method:
     * >> Long name(long[] rChars)
     * Accepts null array as rChars (it shouldn't)
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextNulLNotAlphabet() throws Exception {
        long[] alphabet = null;
        RAND.longs().from(alphabet).val();
    }

    /**
     * Test if the method:
     * >> Long name(long[] rChars)
     * Accepts an empty array as rChars (it shouldn't)
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextEmptyArrayNotAlphabet() throws Exception {
        long[] alphabet = new long[]{};
        RAND.longs().from(alphabet).val();
    }
}
