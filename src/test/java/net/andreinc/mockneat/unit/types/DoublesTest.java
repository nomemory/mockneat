package net.andreinc.mockneat.unit.types;

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static net.andreinc.mockneat.Constants.DOUBLES_CYCLES;
import static net.andreinc.mockneat.Constants.MOCKS;
import static java.util.Arrays.asList;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.ArrayUtils.toObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DoublesTest {

    @Test
    public void testNextDoubleInCorrectRange() {
        loop(DOUBLES_CYCLES,
                MOCKS,
                r -> r.doubles().val(),
                n -> assertTrue(n >= 0.0 && n < 1.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleZeroNotBound() {
        Constants.M.doubles().bound(0.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNegativeNotBound() {
        Constants.M.doubles().bound(-5.0).val();
    }

    @Test
    public void testNextDoubleInCorrectRange2() {
        final double bound = 0.01;
        loop(DOUBLES_CYCLES,
                MOCKS,
                r -> r.doubles().bound(bound).val(),
                n -> assertTrue(n >= 0.0 && n < bound));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound() {
        Constants.M.doubles().bound(Double.NaN).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNextDoubleNullNotBound() {
        Constants.M.doubles().bound((Double) null).val();
    }

    @Test
    public void testNextDoubleInCorrectRange3() {
        Double lowerBound = 1.1987;
        double upperBound = Double.MAX_VALUE;

        loop(DOUBLES_CYCLES,
                MOCKS,
                r -> r.doubles().range(lowerBound, upperBound).val(),
                n -> assertTrue(n >= lowerBound && n < upperBound));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleBoundsNotEqual() {
        Constants.M.doubles().range(0.01, 0.01).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound2() {
        Constants.M.doubles().range(Double.NaN, 10.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleNaNNotBound3() {
        Constants.M.doubles().range(10.0, Double.NaN).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleInfinityNotBound2() {
        Constants.M.doubles().range(Double.POSITIVE_INFINITY, 10.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleLesserUpperBound() {
        Constants.M.doubles().range(10.0, 8.0).val();
    }

    @Test
    public void testNextDoubleCorrectValues() {
        double[] doubles = { 1.0, 5.0, 10.0, 15.0, 20.52 };
        Set<Double> values = new HashSet<>(asList(toObject(doubles)));

        loop(DOUBLES_CYCLES,
                MOCKS,
                r -> r.doubles().from(doubles).val(),
                num -> assertTrue(values.contains(num)));
    }

    @Test
    public void testnNextDoubleCorrectValues2() {
        double[] doubles = { 0.0 };
        loop(DOUBLES_CYCLES,
                MOCKS,
                r -> r.doubles().from(doubles).val(),
                num -> assertEquals(num, doubles[0], 0.0));
    }

    @Test(expected = NullPointerException.class)
    public void testNextDoubleNullNotAlphabet() {
        Constants.M.doubles().from(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextDoubleEmptyArrayNotAlphabet() {
        Constants.M.doubles().from(new double[]{}).val();
    }
}
