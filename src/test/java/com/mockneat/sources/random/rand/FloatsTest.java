package com.mockneat.sources.random.rand;

import com.mockneat.utils.ArrayUtils;
import com.mockneat.utils.FunctUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.mockneat.sources.random.rand.RandTestConstants.NEXT_FLOAT_CYCLES;
import static com.mockneat.sources.random.rand.RandTestConstants.RAND;
import static com.mockneat.sources.random.rand.RandTestConstants.RANDS;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static com.mockneat.utils.ArrayUtils.toWrapperArray;
import static org.junit.Assert.assertTrue;

public class FloatsTest {
    /**
     * Tests if the method:
     * > Float nextFloat()
     * Returns stream between [0.0, 1.0)
     * @throws Exception
     */
    @Test
    public void testNextFloatInCorrectRange() throws Exception {
        FunctUtils.cycle(NEXT_FLOAT_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.floats().val())
                    .forEach(n -> assertTrue(n >= 0.0 && n < 1.0)));
    }

    /**
     * Test if the method:
     * > Float nextFloat(Float bound)
     * Doesn't accept a 0.0 bound
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatZeroNotBound() throws Exception {
        RAND.floats().withBound(0.0f).val();
    }

    /**
     * Test if the method:
     * > Float nextFloat(Float bound)
     * Doesn't accept a negative bound
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void nextFloat_NegativeNotBound() throws Exception {
        RAND.floats().withBound(-5.0f).val();
    }

    /**
     * Test if the method:
     * > Float nextFloat(Float bound)
     * Generates float stream in the correct interval.
     * @throws Exception
     */
    @Test
    public void testNextFloatInCorrectRange2() throws Exception {
        Float bound = 0.01f;
        FunctUtils.cycle(NEXT_FLOAT_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.floats().withBound(bound).val())
                    .forEach(n -> assertTrue(n >= 0.0 && n < bound)));
    }

    /**
     * Test if the method:
     * > Float nextFloat(Float bound)
     * Doesn't accept Float.POSITIVE_INFIINITY as a bound.
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatInfinityNotBound() throws Exception {
        RAND.floats().withBound(Float.POSITIVE_INFINITY).val();
    }

    /**
     * Test if the method:
     * > Float nextFloat(Float bound)
     * Accepts Float.NaN as a bound (it shouldn't)
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatNaNNotBound() throws Exception {
        RAND.floats().withBound(Float.NaN).val();
    }

    /**
     * Test if the method:
     * > Float nextFloat(Float bound)
     * Accepts null as a bound (it shouldn't)
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void nextFloat_NullNotBound() throws Exception {
        Float bound = null;
        stream(RANDS)
                .map(r -> r.floats().withBound(bound).val())
                .count();
    }

    /**
     * Test if the method:
     * > Float nextFloat(Float lowerBound, Float upperBound)
     * Generates the numbers in the correct interval.
     * @throws Exception
     */
    @Test
    public void testNextFloatInCorrectRange3() throws Exception {
        Float lowerBound = 1.1987f;
        Float upperBound = Float.MAX_VALUE;

        FunctUtils.cycle(NEXT_FLOAT_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.floats().inRange(lowerBound, upperBound).val())
                    .forEach(n -> assertTrue(n >= lowerBound && n < upperBound)));
    }

    /**
     * Test if the method:
     * > Float nextFloat(Float lowerBound, Float upperBound)
     * Generates the numbers in the correct interval.
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatBoundsNotEqual() throws Exception {
        Float lowerBound = 0.01f;
        Float upperBound = lowerBound;

        RAND.floats().inRange(lowerBound, upperBound).val();
    }

    /**
     * Test if the method:
     * > Float nextFloat(Float lowerBound, Float upperBound)
     * Doesn't accept NaN as the lowerBound
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatNaNNotBound2() throws Exception {
        RAND.floats().inRange(Float.NaN, 10.0f).val();
    }

    /**
     * Test if the method:
     * > Float nextFloat(Float lowerBound, Float upperBound)
     * Doesn't accept NaN as the upperBound
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatNaNNotBound3() throws Exception {
        RAND.floats().inRange(10.0f, Float.NaN).val();
    }

    /**
     * Test if the method:
     * > Float nextFloat(Float lowerBound, Float upperBound)
     * Doesn't accept null as the upperBound
     * @throws Exception
     */
    @Test(expected =  IllegalArgumentException.class)
    public void testNextFloatNullNotBound2() throws Exception {
        RAND.floats().inRange(10.0f, null).val();
    }

    /**
     * Test if the method:
     * > Float nextFloat(Float lowerBound, Float upperBound)
     * Doesn't accept null as the lowerBound
     * @throws Exception
     */
    @Test(expected =  IllegalArgumentException.class)
    public void testNextFloatNullNotBound3() throws Exception {
        RAND.floats().inRange(null, 10.0f).val();
    }

    /**
     * Test if the method:
     * > Float nextFloat(Float lowerBound, Float upperBound)
     * Doesn't accept null as the lowerBound
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatInfinityNotBound2() throws Exception {
        RAND.floats().inRange(Float.POSITIVE_INFINITY, 10.0f).val();
    }

    /**
     * Test if the method:
     * > Float nextFloat(Float lowerBound, Float upperBound)
     * Doesn't accept Infinity as the upperBound
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatInfinityNotBound3() throws Exception {
        RAND.floats().inRange(10.0f, Float.POSITIVE_INFINITY).val();
    }

    /**
     * Test if the method:
     * > Float nextFloat(Float lowerBound, Float upperBound)
     * Doesn't accept an upperBound lesser than the lowerBound
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatLesserUpperBound() throws Exception {
        RAND.floats().inRange(10.0f, 8.0f).val();
    }

    /**
     * Test if the method:
     * > Float name(float[] rChars)
     * Generates only float stream from the rChars
     * @throws Exception
     */
    @Test
    public void testNextCorrectValues() throws Exception {
        float[] floats = { 1.0f, 5.0f, 10.0f, 15.0f, 20.52f };
        Set<Float> values = new HashSet<>(asList(ArrayUtils.toWrapperArray(floats)));

        FunctUtils.cycle(NEXT_FLOAT_CYCLES, () ->
            stream(RANDS).map(r -> r.floats().from(floats).val())
                    .forEach(num -> assertTrue(values.contains(num))));
    }

    /**
     * Test if the method:
     * > Float name(float[] rChars)
     * Generates only float stream from the rChars
     * @throws Exception
     */
    @Test
    public void testNextCorrectValues2() throws Exception {
        float[] floats = { 0.0f };
        FunctUtils.cycle(NEXT_FLOAT_CYCLES, () ->
            stream(RANDS).map(r -> r.floats().from(floats).val())
                    .forEach(num -> assertTrue(num.equals(floats[0]))));
    }

    /**
     * Test if the method:
     * > Float name(float[] rChars)
     * Doesn't accept null as the rChars
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextNullNotAlphabet() throws Exception {
        float[] alphabet = null;
        RAND.floats().from(alphabet).val();
    }

    /**
     * Test if the method:
     * > Float name(float[] rChars)
     * Doesn't accept an empty array as rChars
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextEmptyArrayNotAlphabet() throws Exception {
        RAND.floats().from(new float[]{}).val();
    }
}
