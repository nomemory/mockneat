package com.mockneat.sources.random.rand;

import com.mockneat.utils.ArrayUtils;
import com.mockneat.utils.FunctUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.mockneat.sources.random.rand.RandTestConstants.*;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static org.junit.Assert.assertTrue;

public class DoublesTest {

    /**
     * Tests if the method:
     * > Double nextGaussian()
     * works...
     * @throws Exception
     */
    @Test
    public void testNextGaussianInCorrectRange() throws Exception {
        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
            stream(RANDS).map(r -> r.doubles()).count());
    }

    /**
     * Tests if the method:
     * > DoubleStream nextGaussian()
     * works...
     * @throws Exception
     */
    @Test
    public void testStreamGaussianWorks() throws Exception {
        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
                stream(RANDS)
                        .map(r -> r.doubles().gaussians().stream().limit(DOUBLE_STREAMS_LIMIT))
                        .count());
    }

    /**
     * Tests if the method:
     * > Double val()
     * Returns stream between [0.0, 1.0)
     * @throws Exception
     */
    @Test
    public void testNextDoubleInCorrectRange() throws Exception {
        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.doubles().val())
                    .forEach(n -> assertTrue(n >= 0.0 && n < 1.0)));
    }

    /**
     * Tests if the method:
     * > public DoubleStream streamDouble()
     * Returns a names of doubles in the range [0.0, 1.0)
     * @throws Exception
     */
    @Test
    public void testStreamDoubleInCorrectRange() throws Exception {
        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
                stream(RANDS)
                    .map(r -> r.doubles().stream().limit(DOUBLE_STREAMS_LIMIT))
                    .forEach(s ->
                            s.forEach(n -> assertTrue(n >= 0 && n <1.0))));
    }

    /**
     * Test if the method:
     * > Double val(Double bound)
     * Doesn't accept a 0.0 bound
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleZeroNotBound() throws Exception {
        RAND.doubles().withBound(0.0).val();
    }

    /**
     * Test if the method:
     * > Double val(Double bound)
     * Doesn't accept a negative bound
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNegativeNotBound() throws Exception {
        RAND.doubles().withBound(-5.0).val();
    }

    /**
     * Test if the method:
     * > Double val(Double bound)
     * Generates double stream in the correct interval.
     * @throws Exception
     */
    @Test
    public void testNextDoubleInCorrectRange2() throws Exception {
        final Double bound = 0.01;
        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.doubles().withBound(bound).val())
                    .forEach(n -> assertTrue(n >= 0.0 && n < bound)));
    }


    /**
     * Test if method:
     * > DoubleStream streamDouble(Double bound)
     * Generates a names of double stream in the correct range
     * @throws Exception
     */
    @Test
    public void testStreamDoubleInCorrectRange2() throws Exception {
        final Double bound = 0.01;
        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
                stream(RANDS)
                    .map(r -> r.doubles()
                                .withBound(bound)
                                .stream()
                                .limit(DOUBLE_STREAMS_LIMIT))
                    .forEach( s -> s.forEach(d -> assertTrue(d >= 0.0 && d < bound))));
    }

    /**
     * Test if the method:
     * > Double val(Double bound)
     * Doesn't accept Double.POSITIVE_INFIINITY as a bound.
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleInfinityNotBound() throws Exception {
        RAND.doubles().withBound(Double.POSITIVE_INFINITY).val();
    }

    /**
     * Test if the method:
     * > Double val(Double bound)
     * Accepts Double.NaN as a bound (it shouldn't)
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound() throws Exception {
        RAND.doubles().withBound(Double.NaN).val();
    }

    /**
     * Test if the method:
     * > Double val(Double bound)
     * Accepts null as a bound (it shouldn't)
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNullNotBound() throws Exception {
        Double bound = null;
        RAND.doubles().withBound(bound).val();
    }

    /**
     * Test if the method:
     * > Double val(Double lowerBound, Double upperBound)
     * Generates the numbers in the correct interval.
     * @throws Exception
     */
    @Test
    public void testNextDoubleInCorrectRange3() throws Exception {
        Double lowerBound = 1.1987;
        Double upperBound = Double.MAX_VALUE;

        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.doubles().inRange(lowerBound, upperBound).val())
                    .forEach(n -> assertTrue(n >= lowerBound && n < upperBound)));
    }

    /**
     * Test if the method:
     * > DoubleStream streamDouble(Double lowerBound, Double upperBound)
     * Generates the numbers in the correct interval
     */
    @Test
    public void testStreamDoubleInCorrectRange3() throws Exception {
        Double low = 1.21;
        Double upper = Double.MAX_VALUE;

        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
                stream(RANDS)
                .map(r -> r.doubles()
                            .inRange(low, upper)
                            .stream()
                            .limit(DOUBLE_STREAMS_LIMIT))
                .forEach(s -> s.forEach(
                        d -> assertTrue(d >= 1.21 && d < upper))));
    }

    /**
     * Test if the method:
     * > Double val(Double lowerBound, Double upperBound)
     * Generates the numbers in the correct interval.
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleBoundsNotEqual() throws Exception {
        Double lowerBound = 0.01;
        Double upperBound = lowerBound;

        RAND.doubles().inRange(lowerBound, upperBound).val();
    }

    /**
     * Test if the method:
     * > Double val(Double lowerBound, Double upperBound)
     * Doesn't accept NaN as the lowerBound
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound2() throws Exception {
        RAND.doubles().inRange(Double.NaN, 10.0).val();
    }

    /**
     * Test if the method:
     * > Double val(Double lowerBound, Double upperBound)
     * Doesn't accept NaN as the upperBound
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound3() throws Exception {
        RAND.doubles().inRange(10.0, Double.NaN).val();
    }

    /**
     * Test if the method:
     * > Double val(Double lowerBound, Double upperBound)
     * Doesn't accept null as the upperBound
     * @throws Exception
     */
    @Test(expected =  IllegalArgumentException.class)
    public void testNextDoubleNullNotBound2() throws Exception {
        RAND.doubles().inRange(10.0, null).val();
    }

    /**
     * Test if the method:
     * > Double val(Double lowerBound, Double upperBound)
     * Doesn't accept null as the lowerBound
     * @throws Exception
     */
    @Test(expected =  IllegalArgumentException.class)
    public void testNextDoubleNullNotBound3() throws Exception {
        RAND.doubles().inRange(null, 10.0).val();
    }

    /**
     * Test if the method:
     * > Double val(Double lowerBound, Double upperBound)
     * Doesn't accept null as the lowerBound
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleInfinityNotBound2() throws Exception {
        RAND.doubles().inRange(Double.POSITIVE_INFINITY, 10.0).val();
    }

    /**
     * Test if the method:
     * > Double val(Double lowerBound, Double upperBound)
     * Doesn't accept Infinity as the upperBound
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleInfinityNotBound3() throws Exception {
        RAND.doubles().inRange(10.0, Double.POSITIVE_INFINITY).val();
    }

    /**
     * Test if the method:
     * > Double val(Double lowerBound, Double upperBound)
     * Doesn't accept an upperBound lesser than the lowerBound
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleLesserUpperBound() throws Exception {
        RAND.doubles().inRange(10.0, 8.0).val();
    }

    /**
     * Test if the method:
     * > Double val(double[] rChars)
     * Generates only double stream from the rChars
     * @throws Exception
     */
    @Test
    public void testNextDoubleCorrectValues() throws Exception {
        double[] doubles = { 1.0, 5.0, 10.0, 15.0, 20.52 };
        Set<Double> values = new HashSet<>(asList(ArrayUtils.toWrapperArray(doubles)));

        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
            stream(RANDS).map(r -> r.doubles().from(doubles).val())
                    .forEach(num -> assertTrue(values.contains(num))));
    }

    /**
     * Test if the method:
     * > Double val(double[] rChars)
     * Generates only double stream from the rChars
     * @throws Exception
     */
    @Test
    public void testnNextDoubleCorrectValues2() throws Exception {
        double[] doubles = { 0.0 };
        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.doubles().from(doubles).val())
                    .forEach(num -> assertTrue(num.equals(doubles[0]))));
    }

    /**
     * Test if the method:
     * > Double val(double[] rChars)
     * Doesn't accept null as the rChars
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNullNotAlphabet() throws Exception {
        double[] alphabet = null;
        RAND.doubles().from(alphabet).val();
    }

    /**
     * Test if the method:
     * > Double val(double[] rChars)
     * Doesn't accept an empty array as rChars
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleEmptyArrayNotAlphabet() throws Exception {
        RAND.doubles().from(new double[]{}).val();
    }
}
