package com.mockneat.mock.unit.types;

import com.mockneat.mock.Constants;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.mockneat.mock.Constants.DOUBLES_CYCLES;
import static com.mockneat.mock.Constants.RANDS;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.ArrayUtils.toObject;
import static org.junit.Assert.assertTrue;

public class DoublesTest {

    @Test
    public void testNextGaussianInCorrectRange() throws Exception {
        loop(DOUBLES_CYCLES, () -> stream(RANDS).map(r -> r.doubles()).count());
    }

    @Test
    public void testNextDoubleInCorrectRange() throws Exception {
        loop(DOUBLES_CYCLES,
                RANDS,
                r -> r.doubles().val(),
                n -> assertTrue(n >= 0.0 && n < 1.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleZeroNotBound() throws Exception {
        Constants.RAND.doubles().bound(0.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNegativeNotBound() throws Exception {
        Constants.RAND.doubles().bound(-5.0).val();
    }

    @Test
    public void testNextDoubleInCorrectRange2() throws Exception {
        final Double bound = 0.01;
        loop(DOUBLES_CYCLES,
                RANDS,
                r -> r.doubles().bound(bound).val(),
                n -> assertTrue(n >= 0.0 && n < bound));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound() throws Exception {
        Constants.RAND.doubles().bound(Double.NaN).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextDoubleNullNotBound() throws Exception {
        Double bound = null;
        Constants.RAND.doubles().bound(bound).val();
    }

    @Test
    public void testNextDoubleInCorrectRange3() throws Exception {
        Double lowerBound = 1.1987;
        Double upperBound = Double.MAX_VALUE;

        loop(DOUBLES_CYCLES,
                RANDS,
                r -> r.doubles().range(lowerBound, upperBound).val(),
                n -> assertTrue(n >= lowerBound && n < upperBound));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleBoundsNotEqual() throws Exception {
        Double lowerBound = 0.01;
        Double upperBound = lowerBound;

        Constants.RAND.doubles().range(lowerBound, upperBound).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound2() throws Exception {
        Constants.RAND.doubles().range(Double.NaN, 10.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound3() throws Exception {
        Constants.RAND.doubles().range(10.0, Double.NaN).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleInfinityNotBound2() throws Exception {
        Constants.RAND.doubles().range(Double.POSITIVE_INFINITY, 10.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleLesserUpperBound() throws Exception {
        Constants.RAND.doubles().range(10.0, 8.0).val();
    }

    @Test
    public void testNextDoubleCorrectValues() throws Exception {
        double[] doubles = { 1.0, 5.0, 10.0, 15.0, 20.52 };
        Set<Double> values = new HashSet<>(asList(toObject(doubles)));

        loop(DOUBLES_CYCLES,
                RANDS,
                r -> r.doubles().from(doubles).val(),
                num -> assertTrue(values.contains(num)));
    }

    @Test
    public void testnNextDoubleCorrectValues2() throws Exception {
        double[] doubles = { 0.0 };
        loop(DOUBLES_CYCLES,
                RANDS,
                r -> r.doubles().from(doubles).val(),
                num -> assertTrue(num.equals(doubles[0])));
    }

    @Test(expected = NullPointerException.class)
    public void testNextDoubleNullNotAlphabet() throws Exception {
        double[] alphabet = null;
        Constants.RAND.doubles().from(alphabet).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleEmptyArrayNotAlphabet() throws Exception {
        Constants.RAND.doubles().from(new double[]{}).val();
    }
}
