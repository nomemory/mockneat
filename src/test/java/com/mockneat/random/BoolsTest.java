package com.mockneat.random;

import org.junit.Assert;
import org.junit.Test;

import static com.mockneat.random.RandTestConstants.*;
import static com.mockneat.utils.FunctUtils.cycle;
import static java.util.Arrays.stream;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoolsTest {

    @Test
    public void test100ProbabilityVal() throws Exception {
        cycle(BOOLS_CYCLES, () ->
                stream(RANDS)
                    .map(rand -> rand.bools().probability(100.0).val())
                    .forEach(bol -> assertTrue(bol)));
    }


    @Test
    public void testNextBooleanAlwaysTrueIf1002() throws Exception {
        cycle(BOOLS_CYCLES, () ->
            stream(RANDS)
                    .map(rand -> rand.bools().probability(100.0).val())
                    .forEach(bol -> assertTrue(bol)));
    }


    @Test
    public void testNextBooleanAlwaysFalseIf() throws Exception {
        cycle(BOOLS_CYCLES, () ->
            stream(RANDS)
                    .map(rand -> rand.bools().probability(0.0).val())
                    .forEach(bol -> assertFalse(bol)));
    }

    @Test
    public void testNextBooleanAlwaysFalseIf02() throws Exception {
        cycle(BOOLS_CYCLES, () ->
            stream(RANDS)
                    .map(rand -> rand.bools().probability(0.0).val())
                    .forEach(bol -> assertFalse(bol)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanNegativeNotProbability() throws Exception {
        RAND.bools().probability(-5.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanNegativeNotProbability_2() throws Exception {
        RAND.bools().probability(-5.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanGreaterThan100NotProbability() throws Exception {
        RAND.bools().probability(105.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanGreaterThan100NotProbability2() throws Exception {
        RAND.bools().probability(105.0).val();
    }

    @Test
    public void testBools() throws Exception {
        cycle(BOOLS_CYCLES, () ->
                stream(RANDS).forEach(r -> {
                    Boolean b = r.bools().val();
                    assertTrue(b!=null && (b||!b));
                }));
    }
}
