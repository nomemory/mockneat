package com.mockneat.mock.unit.types;

import org.junit.Test;

import static com.mockneat.mock.Constants.*;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static java.util.Arrays.stream;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoolsTest {

    @Test
    public void test100ProbabilityVal() throws Exception {
        loop(BOOLS_CYCLES,
                RANDS,
                r -> r.bools().probability(100.0).val(),
                b -> assertTrue(b));
    }

    @Test
    public void testNextBooleanAlwaysFalseIf() throws Exception {
        loop(BOOLS_CYCLES,
                RANDS,
                r -> r.bools().probability(0.0).val(),
                bol -> assertFalse(bol));
    }

    @Test
    public void testNextBooleanAlwaysFalseIf02() throws Exception {
        loop(BOOLS_CYCLES,
                RANDS,
                rand -> rand.bools().probability(0.0).val(),
                bol -> assertFalse(bol));
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
        loop(BOOLS_CYCLES,
                RANDS,
                r -> r.bools().val(),
                b -> assertTrue(b!=null && (b||!b)));
    }
}
