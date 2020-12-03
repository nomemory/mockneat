package net.andreinc.mockneat.unit.types;

import org.junit.Assert;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;

public class BoolsTest {

    @Test
    public void test100ProbabilityVal() {
        loop(BOOLS_CYCLES,
                MOCKS,
                r -> r.bools().probability(100.0).val(),
                Assert::assertTrue);
    }

    @Test
    public void testNextBooleanAlwaysFalseIf() {
        loop(BOOLS_CYCLES,
                MOCKS,
                r -> r.bools().probability(0.0).val(),
                Assert::assertFalse);
    }

    @Test
    public void testNextBooleanAlwaysFalseIf02() {
        loop(BOOLS_CYCLES,
                MOCKS,
                rand -> rand.bools().probability(0.0).val(),
                Assert::assertFalse);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanNegativeNotProbability() {
        M.bools().probability(-5.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanNegativeNotProbability_2() {
        M.bools().probability(-5.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanGreaterThan100NotProbability() {
        M.bools().probability(105.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanGreaterThan100NotProbability2() {
        M.bools().probability(105.0).val();
    }

    @Test
    public void testBools() {
        loop(BOOLS_CYCLES,
                MOCKS,
                r -> r.bools().val(),
                Assert::assertNotNull);
    }
}
