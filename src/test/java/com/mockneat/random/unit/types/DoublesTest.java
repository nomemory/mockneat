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

public class DoublesTest {

    @Test
    public void testNextGaussianInCorrectRange() throws Exception {
        FunctUtils.cycle(RandTestConstants.DOUBLES_CYCLES, () ->
            stream(RandTestConstants.RANDS).map(r -> r.doubles()).count());
    }

    @Test
    public void testNextDoubleInCorrectRange() throws Exception {
        FunctUtils.cycle(RandTestConstants.DOUBLES_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.doubles().val())
                    .forEach(n -> assertTrue(n >= 0.0 && n < 1.0)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleZeroNotBound() throws Exception {
        RandTestConstants.RAND.doubles().bound(0.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNegativeNotBound() throws Exception {
        RandTestConstants.RAND.doubles().bound(-5.0).val();
    }

    @Test
    public void testNextDoubleInCorrectRange2() throws Exception {
        final Double bound = 0.01;
        FunctUtils.cycle(RandTestConstants.DOUBLES_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.doubles().bound(bound).val())
                    .forEach(n -> assertTrue(n >= 0.0 && n < bound)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound() throws Exception {
        RandTestConstants.RAND.doubles().bound(Double.NaN).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextDoubleNullNotBound() throws Exception {
        Double bound = null;
        RandTestConstants.RAND.doubles().bound(bound).val();
    }

    @Test
    public void testNextDoubleInCorrectRange3() throws Exception {
        Double lowerBound = 1.1987;
        Double upperBound = Double.MAX_VALUE;

        FunctUtils.cycle(RandTestConstants.DOUBLES_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.doubles().range(lowerBound, upperBound).val())
                    .forEach(n -> assertTrue(n >= lowerBound && n < upperBound)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleBoundsNotEqual() throws Exception {
        Double lowerBound = 0.01;
        Double upperBound = lowerBound;

        RandTestConstants.RAND.doubles().range(lowerBound, upperBound).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound2() throws Exception {
        RandTestConstants.RAND.doubles().range(Double.NaN, 10.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound3() throws Exception {
        RandTestConstants.RAND.doubles().range(10.0, Double.NaN).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleInfinityNotBound2() throws Exception {
        RandTestConstants.RAND.doubles().range(Double.POSITIVE_INFINITY, 10.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleLesserUpperBound() throws Exception {
        RandTestConstants.RAND.doubles().range(10.0, 8.0).val();
    }

    @Test
    public void testNextDoubleCorrectValues() throws Exception {
        double[] doubles = { 1.0, 5.0, 10.0, 15.0, 20.52 };
        Set<Double> values = new HashSet<>(asList(toObject(doubles)));

        FunctUtils.cycle(RandTestConstants.DOUBLES_CYCLES, () ->
            stream(RandTestConstants.RANDS).map(r -> r.doubles().from(doubles).val())
                    .forEach(num -> assertTrue(values.contains(num))));
    }

    @Test
    public void testnNextDoubleCorrectValues2() throws Exception {
        double[] doubles = { 0.0 };
        FunctUtils.cycle(RandTestConstants.DOUBLES_CYCLES, () ->
            stream(RandTestConstants.RANDS)
                    .map(r -> r.doubles().from(doubles).val())
                    .forEach(num -> assertTrue(num.equals(doubles[0]))));
    }

    @Test(expected = NullPointerException.class)
    public void testNextDoubleNullNotAlphabet() throws Exception {
        double[] alphabet = null;
        RandTestConstants.RAND.doubles().from(alphabet).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleEmptyArrayNotAlphabet() throws Exception {
        RandTestConstants.RAND.doubles().from(new double[]{}).val();
    }
}
