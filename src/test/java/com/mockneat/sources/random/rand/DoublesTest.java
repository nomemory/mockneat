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

    @Test
    public void testNextGaussianInCorrectRange() throws Exception {
        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
            stream(RANDS).map(r -> r.doubles()).count());
    }

    @Test
    public void testStreamGaussianWorks() throws Exception {
        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
                stream(RANDS)
                        .map(r -> r.doubles().gaussians().stream().limit(DOUBLE_STREAMS_LIMIT))
                        .count());
    }

    @Test
    public void testNextDoubleInCorrectRange() throws Exception {
        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.doubles().val())
                    .forEach(n -> assertTrue(n >= 0.0 && n < 1.0)));
    }

    @Test
    public void testStreamDoubleInCorrectRange() throws Exception {
        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
                stream(RANDS)
                    .map(r -> r.doubles().stream().limit(DOUBLE_STREAMS_LIMIT))
                    .forEach(s ->
                            s.forEach(n -> assertTrue(n >= 0 && n <1.0))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleZeroNotBound() throws Exception {
        RAND.doubles().withBound(0.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNegativeNotBound() throws Exception {
        RAND.doubles().withBound(-5.0).val();
    }

    @Test
    public void testNextDoubleInCorrectRange2() throws Exception {
        final Double bound = 0.01;
        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.doubles().withBound(bound).val())
                    .forEach(n -> assertTrue(n >= 0.0 && n < bound)));
    }

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

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleInfinityNotBound() throws Exception {
        RAND.doubles().withBound(Double.POSITIVE_INFINITY).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound() throws Exception {
        RAND.doubles().withBound(Double.NaN).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNullNotBound() throws Exception {
        Double bound = null;
        RAND.doubles().withBound(bound).val();
    }

    @Test
    public void testNextDoubleInCorrectRange3() throws Exception {
        Double lowerBound = 1.1987;
        Double upperBound = Double.MAX_VALUE;

        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.doubles().inRange(lowerBound, upperBound).val())
                    .forEach(n -> assertTrue(n >= lowerBound && n < upperBound)));
    }

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

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleBoundsNotEqual() throws Exception {
        Double lowerBound = 0.01;
        Double upperBound = lowerBound;

        RAND.doubles().inRange(lowerBound, upperBound).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound2() throws Exception {
        RAND.doubles().inRange(Double.NaN, 10.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound3() throws Exception {
        RAND.doubles().inRange(10.0, Double.NaN).val();
    }

    @Test(expected =  IllegalArgumentException.class)
    public void testNextDoubleNullNotBound2() throws Exception {
        RAND.doubles().inRange(10.0, null).val();
    }

    @Test(expected =  IllegalArgumentException.class)
    public void testNextDoubleNullNotBound3() throws Exception {
        RAND.doubles().inRange(null, 10.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleInfinityNotBound2() throws Exception {
        RAND.doubles().inRange(Double.POSITIVE_INFINITY, 10.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleInfinityNotBound3() throws Exception {
        RAND.doubles().inRange(10.0, Double.POSITIVE_INFINITY).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleLesserUpperBound() throws Exception {
        RAND.doubles().inRange(10.0, 8.0).val();
    }

    @Test
    public void testNextDoubleCorrectValues() throws Exception {
        double[] doubles = { 1.0, 5.0, 10.0, 15.0, 20.52 };
        Set<Double> values = new HashSet<>(asList(ArrayUtils.toWrapperArray(doubles)));

        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
            stream(RANDS).map(r -> r.doubles().from(doubles).val())
                    .forEach(num -> assertTrue(values.contains(num))));
    }

    @Test
    public void testnNextDoubleCorrectValues2() throws Exception {
        double[] doubles = { 0.0 };
        FunctUtils.cycle(NEXT_DOUBLE_CYCLES, () ->
            stream(RANDS)
                    .map(r -> r.doubles().from(doubles).val())
                    .forEach(num -> assertTrue(num.equals(doubles[0]))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNullNotAlphabet() throws Exception {
        double[] alphabet = null;
        RAND.doubles().from(alphabet).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleEmptyArrayNotAlphabet() throws Exception {
        RAND.doubles().from(new double[]{}).val();
    }
}
