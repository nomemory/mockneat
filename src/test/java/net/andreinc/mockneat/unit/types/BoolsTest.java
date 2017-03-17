package net.andreinc.mockneat.unit.types;

import org.junit.Test;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoolsTest {

    @Test
    public void test100ProbabilityVal() throws Exception {
        loop(BOOLS_CYCLES,
                MOCKS,
                r -> r.bools().probability(100.0).val(),
                b -> assertTrue(b));
    }

    @Test
    public void testNextBooleanAlwaysFalseIf() throws Exception {
        loop(BOOLS_CYCLES,
                MOCKS,
                r -> r.bools().probability(0.0).val(),
                bol -> assertFalse(bol));
    }

    @Test
    public void testNextBooleanAlwaysFalseIf02() throws Exception {
        loop(BOOLS_CYCLES,
                MOCKS,
                rand -> rand.bools().probability(0.0).val(),
                bol -> assertFalse(bol));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanNegativeNotProbability() throws Exception {
        M.bools().probability(-5.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanNegativeNotProbability_2() throws Exception {
        M.bools().probability(-5.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanGreaterThan100NotProbability() throws Exception {
        M.bools().probability(105.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanGreaterThan100NotProbability2() throws Exception {
        M.bools().probability(105.0).val();
    }

    @Test
    public void testBools() throws Exception {
        loop(BOOLS_CYCLES,
                MOCKS,
                r -> r.bools().val(),
                b -> assertTrue(b!=null && (b||!b)));
    }
}
