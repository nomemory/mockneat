package com.mockneat.random;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static com.mockneat.utils.FunctUtils.cycle;
import static org.apache.commons.lang3.ArrayUtils.toObject;
import static org.junit.Assert.assertTrue;

public class IntsTest {

    @Test
    public void testNextIntegerInCorrectRange() throws Exception {
        int upperBound = 100;
        cycle(RandTestConstants.INTS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.ints().bound(upperBound).val())
                    .forEach(num -> assertTrue(num < 100 && num >= 0)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextIntegerNegativeNotBound() throws Exception {
        int upperBound = -100;
        RandTestConstants.RAND.ints().bound(upperBound).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextIntegerNullNotBound() throws Exception {
        Integer bound = null;
        RandTestConstants.RAND.ints().bound(bound).val();
    }

    @Test
    public void testNextIntegerInCorrectRange2() throws Exception {
        int upperBound = 1;
        cycle(RandTestConstants.INTS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.ints().bound(upperBound).val())
                    .forEach(num -> assertTrue(num.equals(0))));
    }

    @Test
    public void testNextIntegerInCorrectRange3() throws Exception {
        Integer upperBound = Integer.MAX_VALUE;
        cycle(RandTestConstants.INTS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.ints().bound(upperBound).val())
                    .forEach(num -> assertTrue(num < upperBound)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextIntegerNegativeNotBound2() throws Exception {
        int lowerBound = -1;
        int upperBound = 100;
        RandTestConstants.RAND.ints().range(lowerBound, upperBound).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextIntegerNegativeNotBound3() throws Exception {
        int lowerBound = 100;
        int upperBound = -5;
        RandTestConstants.RAND.ints().range(lowerBound, upperBound).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextIntegerNullNotBound2() throws Exception {
        Integer x = null;
        RandTestConstants.RAND.ints().range(x, 10).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextIntegerNullNotBound3() throws Exception {
        Integer x = null;
        RandTestConstants.RAND.ints().range(10, x).val();
    }

    @Test
    public void testNextIntegerInCorrectRange4() throws Exception {
        int lowerBound = 5;
        int upperBound = 8;
        cycle(RandTestConstants.INTS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.ints().range(lowerBound, upperBound).val())
                    .forEach( num -> assertTrue((num >= lowerBound) && (num < upperBound))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextIntegerNonEqualBounds() throws Exception {
        int lowerBound = 10, upperBound = 10;
        RandTestConstants.RAND.ints().range(lowerBound, upperBound).val();
    }

    @Test
    public void testNextIntegerCorrectValues() throws Exception {
        int lowerBound = 10, upperBound = lowerBound + 1;
        cycle(RandTestConstants.INTS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.ints().range(lowerBound, upperBound).val())
                    .forEach(num -> num.equals(lowerBound)));
    }

    @Test
    public void testNextIntegerInCorrectRange5() throws Exception {
        int lowerBound = 0;
        int upperBound = Integer.MAX_VALUE;
        cycle(RandTestConstants.INTS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.ints().range(lowerBound, upperBound).val())
                    .forEach(num -> assertTrue(num >= lowerBound && num < upperBound)));
    }

    @Test
    public void testNextCorrectValues() throws Exception {
        int[] alphabet = { 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000 };
        Set<Integer> helperSet = new HashSet<>(asList(toObject(alphabet)));
        cycle(RandTestConstants.INTS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.ints().from(alphabet).val())
                    .forEach(num -> assertTrue(helperSet.contains(num))));
    }

    @Test(expected = NullPointerException.class)
    public void testNextNulLNotAlphabet() throws Exception {
        int[] alphabet = null;
        RandTestConstants.RAND.ints().from(alphabet).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextEmptyArrayNotAlphabet() throws Exception {
        int[] alphabet = new int[]{};
        RandTestConstants.RAND.ints().from(alphabet).val();
    }
}
