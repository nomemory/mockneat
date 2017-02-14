package com.mockneat.random.unit.types;

import com.mockneat.random.RandTestConstants;
import com.mockneat.random.utils.FunctUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.ArrayUtils.toObject;
import static org.junit.Assert.assertTrue;

public class FloatsTest {

    @Test
    public void testNextFloatInCorrectRange() throws Exception {
        FunctUtils.cycle(RandTestConstants.FLOATS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.floats().val())
                    .forEach(n -> assertTrue(n >= 0.0 && n < 1.0)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatZeroNotBound() throws Exception {
        RandTestConstants.RAND.floats().bound(0.0f).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextFloat_NegativeNotBound() throws Exception {
        RandTestConstants.RAND.floats().bound(-5.0f).val();
    }

    @Test
    public void testNextFloatInCorrectRange2() throws Exception {
        Float bound = 0.01f;
        FunctUtils.cycle(RandTestConstants.FLOATS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.floats().bound(bound).val())
                    .forEach(n -> assertTrue(n >= 0.0 && n < bound)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatNaNNotBound() throws Exception {
        RandTestConstants.RAND.floats().bound(Float.NaN).val();
    }

    @Test(expected = NullPointerException.class)
    public void nextFloat_NullNotBound() throws Exception {
        Float bound = null;
        stream(RandTestConstants.RANDS)
                .map(r -> r.floats().bound(bound).val())
                .count();
    }

    @Test
    public void testNextFloatInCorrectRange3() throws Exception {
        Float lowerBound = 1.1987f;
        Float upperBound = Float.MAX_VALUE;

        FunctUtils.cycle(RandTestConstants.FLOATS_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.floats().range(lowerBound, upperBound).val())
                    .forEach(n -> assertTrue(n >= lowerBound && n < upperBound)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatBoundsNotEqual() throws Exception {
        Float lowerBound = 0.01f;
        Float upperBound = lowerBound;

        RandTestConstants.RAND.floats().range(lowerBound, upperBound).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatNaNNotBound2() throws Exception {
        RandTestConstants.RAND.floats().range(Float.NaN, 10.0f).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatNaNNotBound3() throws Exception {
        RandTestConstants.RAND.floats().range(10.0f, Float.NaN).val();
    }

    @Test(expected =  NullPointerException.class)
    public void testNextFloatNullNotBound2() throws Exception {
        Float f = null;
        RandTestConstants.RAND.floats().range(10.0f, f).val();
    }

    @Test(expected =  NullPointerException.class)
    public void testNextFloatNullNotBound3() throws Exception {
        Float f = null;
        RandTestConstants.RAND.floats().range(f, 10.0f).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatLesserUpperBound() throws Exception {
        RandTestConstants.RAND.floats().range(10.0f, 8.0f).val();
    }

    @Test
    public void testNextCorrectValues() throws Exception {
        float[] floats = { 1.0f, 5.0f, 10.0f, 15.0f, 20.52f };
        Set<Float> values = new HashSet<>(asList(toObject(floats)));

        FunctUtils.cycle(RandTestConstants.FLOATS_CYCLES, () ->
            stream(RandTestConstants.RANDS).map(r -> r.floats().from(floats).val())
                    .forEach(num -> assertTrue(values.contains(num))));
    }

    @Test
    public void testNextCorrectValues2() throws Exception {
        float[] floats = { 0.0f };
        FunctUtils.cycle(RandTestConstants.FLOATS_CYCLES, () ->
            stream(RandTestConstants.RANDS).map(r -> r.floats().from(floats).val())
                    .forEach(num -> assertTrue(num.equals(floats[0]))));
    }

    @Test(expected = NullPointerException.class)
    public void testNextNullNotAlphabet() throws Exception {
        float[] alphabet = null;
        RandTestConstants.RAND.floats().from(alphabet).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextEmptyArrayNotAlphabet() throws Exception {
        RandTestConstants.RAND.floats().from(new float[]{}).val();
    }
}
