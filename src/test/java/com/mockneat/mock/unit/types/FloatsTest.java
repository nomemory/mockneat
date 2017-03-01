package com.mockneat.mock.unit.types;

import com.mockneat.mock.Constants;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.mockneat.mock.Constants.FLOATS_CYCLES;
import static com.mockneat.mock.Constants.MOCKS;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.ArrayUtils.toObject;
import static org.junit.Assert.assertTrue;

public class FloatsTest {

    @Test
    public void testNextFloatInCorrectRange() throws Exception {
        loop(FLOATS_CYCLES,
                MOCKS,
                r -> r.floats().val(),
                n -> assertTrue(n >= 0.0 && n < 1.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatZeroNotBound() throws Exception {
        Constants.M.floats().bound(0.0f).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void nextFloat_NegativeNotBound() throws Exception {
        Constants.M.floats().bound(-5.0f).val();
    }

    @Test
    public void testNextFloatInCorrectRange2() throws Exception {
        Float bound = 0.01f;
        loop(FLOATS_CYCLES,
                MOCKS,
                r -> r.floats().bound(bound).val(),
                n -> assertTrue(n >= 0.0 && n < bound));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatNaNNotBound() throws Exception {
        Constants.M.floats().bound(Float.NaN).val();
    }

    @Test(expected = NullPointerException.class)
    public void nextFloat_NullNotBound() throws Exception {
        Float bound = null;
        stream(MOCKS).map(r -> r.floats().bound(bound).val()).count();
    }

    @Test
    public void testNextFloatInCorrectRange3() throws Exception {
        Float lowerBound = 1.1987f;
        Float upperBound = Float.MAX_VALUE;

        loop(FLOATS_CYCLES,
                MOCKS,
                r -> r.floats().range(lowerBound, upperBound).val(),
                n -> assertTrue(n >= lowerBound && n < upperBound));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatBoundsNotEqual() throws Exception {
        Float lowerBound = 0.01f;
        Float upperBound = lowerBound;

        Constants.M.floats().range(lowerBound, upperBound).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatNaNNotBound2() throws Exception {
        Constants.M.floats().range(Float.NaN, 10.0f).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatNaNNotBound3() throws Exception {
        Constants.M.floats().range(10.0f, Float.NaN).val();
    }

    @Test(expected =  NullPointerException.class)
    public void testNextFloatNullNotBound2() throws Exception {
        Float f = null;
        Constants.M.floats().range(10.0f, f).val();
    }

    @Test(expected =  NullPointerException.class)
    public void testNextFloatNullNotBound3() throws Exception {
        Float f = null;
        Constants.M.floats().range(f, 10.0f).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextFloatLesserUpperBound() throws Exception {
        Constants.M.floats().range(10.0f, 8.0f).val();
    }

    @Test
    public void testNextCorrectValues() throws Exception {
        float[] floats = { 1.0f, 5.0f, 10.0f, 15.0f, 20.52f };
        Set<Float> values = new HashSet<>(asList(toObject(floats)));
        loop(FLOATS_CYCLES,
                MOCKS,
                r -> r.floats().from(floats).val(),
                num -> assertTrue(values.contains(num)));
    }

    @Test
    public void testNextCorrectValues2() throws Exception {
        float[] floats = { 0.0f };
        loop(FLOATS_CYCLES,
                MOCKS,
                r -> r.floats().from(floats).val(),
                num -> assertTrue(num.equals(floats[0])));
    }

    @Test(expected = NullPointerException.class)
    public void testNextNullNotAlphabet() throws Exception {
        float[] alphabet = null;
        Constants.M.floats().from(alphabet).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextEmptyArrayNotAlphabet() throws Exception {
        Constants.M.floats().from(new float[]{}).val();
    }
}
