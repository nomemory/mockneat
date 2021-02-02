package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.utils.LoopsUtils;
import org.junit.Assert;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.M;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.Constants.PROBABILITIES_CYCLES;

public class ProbabilitiesTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNegative() {
        M.probabilites(Integer.class)
                .add(-0.5, M.ints().bound(10))
                .add(1.5, M.ints().range(10, 20))
                .val();
    }

    @Test(expected = NullPointerException.class)
    public void testNull() {
        M.probabilites(Integer.class)
                .add(null, M.ints().bound(10))
                .val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumNot0() {
        M.probabilites(Integer.class)
                .add(0.2, M.fromInts(new int[]{1,2,3}))
                .add(0.5, M.fromInts(new int[]{4,5,6}))
                .val();
    }

    @Test
    public void testValues() {
        LoopsUtils.loop(
                PROBABILITIES_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.probabilites(Integer.class)
                                    .add(0.1, 1)
                                    .add(0.9, 2)
                                    .val(),
                val -> Assert.assertTrue(val.equals(2) || val.equals(1))
        );
    }

    @Test
    public void testValuesWIthMockUnits() {
        LoopsUtils.loop(
                PROBABILITIES_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.probabilites(String.class)
                                    .add(0.5, mockNeat.strings().size(10))
                                    .add(0.5, mockNeat.strings().size(15))
                                    .val(),
                val -> Assert.assertTrue(val.length()==10 ||
                                                    val.length() == 15)
        );
    }

    @Test
    public void testValuesWithMockUnits2() {
        LoopsUtils.loop(
                PROBABILITIES_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.probabilites(Integer.class)
                                    .add(0.1, mockNeat.ints().range(0, 1))
                                    .add(0.1, mockNeat.ints().range(1, 2))
                                    .add(0.2, mockNeat.ints().range(2, 3))
                                    .add(0.2, mockNeat.ints().range(3, 4))
                                    .add(0.3, mockNeat.ints().range(4, 5))
                                    .add(0.1, mockNeat.ints().range(5, 6))
                                    .val(),
                val -> {
                    Assert.assertTrue(val >= 0 && val <= 5);
                }
        );
    }
}
