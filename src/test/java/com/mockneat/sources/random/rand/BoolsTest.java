package com.mockneat.sources.random.rand;

import com.mockneat.utils.FunctUtils;
import org.junit.Assert;
import org.junit.Test;

import static com.mockneat.sources.random.rand.RandTestConstants.*;
import static java.util.Arrays.stream;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoolsTest {

    /**
     * Tests if the method:
     * >> Boolean val(Integer probability)
     * Returns always true for a 100% probability
     * @throws Exception
     */
    @Test
    public void testNextBooleanAlwaysTrueIf100() throws Exception {
        FunctUtils.cycle(NEXT_BOOLEAN_CYCLES, () ->
                stream(RANDS)
                    .map(rand -> rand.bools().probability(100.0).val())
                    .forEach(bol -> assertTrue(bol)));
    }


    /**
     * Test if the method:
     * > Stream<Boolean> streamBoolean(Integer probability)
     * Returns streams that contain only TRUE stream for 100% probability
     * @throws Exception
     */
    @Test
    public void testStreamBooleanAlwaysTrueIf100() throws Exception {
        FunctUtils.cycle(NEXT_BOOLEAN_CYCLES, () ->
                stream(RANDS)
                    .map(r -> r.bools().probability(100.0).stream().limit(BOOLEAN_STREAMS_LIMIT))
                    .forEach(s -> s.forEach(Assert::assertTrue)));
    }

    /**
     * Tests if the method:
     * >> Boolean val(Double probability)
     * Returns always true for a 100.0% probability
     * @throws Exception
     */
    @Test
    public void testNextBooleanAlwaysTrueIf1002() throws Exception {
        FunctUtils.cycle(NEXT_BOOLEAN_CYCLES, () ->
            stream(RANDS)
                    .map(rand -> rand.bools().probability(100.0).val())
                    .forEach(bol -> assertTrue(bol)));
    }

    /**
     * Test if the method:
     * > Stream<Boolean> streamBoolean(Double probability)
     * Returns streams that contain only TRUE stream for 100% probability
     * @throws Exception
     */
    @Test
    public void testStreamBooleanAlwaysTrueIf100_2() throws Exception {
        FunctUtils.cycle(NEXT_BOOLEAN_CYCLES, () ->
                stream(RANDS)
                        .map(r -> r.bools().probability(100.0).stream().limit(BOOLEAN_STREAMS_LIMIT))
                        .forEach(s -> s.forEach(Assert::assertTrue)));
    }

    /**
     * Tests if the method:
     * >> Boolean val(Integer probability)
     * Returns always false for a 0% probability
     * @throws Exception
     */
    @Test
    public void testNextBooleanAlwaysFalseIf() throws Exception {
        FunctUtils.cycle(NEXT_BOOLEAN_CYCLES, () ->
            stream(RANDS)
                    .map(rand -> rand.bools().probability(0.0).val())
                    .forEach(bol -> assertFalse(bol)));
    }

    /**
     * Test if the method:
     * > Stream<Boolean> streamBoolean(Integer probability)
     * Returns streams that contain only FALSE stream for 0 probability
     * @throws Exception
     */
    @Test
    public void testStreamBooleanAlwaysFalseIf0() throws Exception {
        FunctUtils.cycle(NEXT_BOOLEAN_CYCLES, () ->
                stream(RANDS)
                        .map(r -> r.bools()
                                    .probability(0.0)
                                    .stream()
                                    .limit(BOOLEAN_STREAMS_LIMIT))
                        .forEach(s -> s.forEach(Assert::assertFalse)));
    }


    /**
     * Tests if the method:
     * >> Boolean val(Double probability)
     * Returns always false for a 0% probability
     * @throws Exception
     */
    @Test
    public void testNextBooleanAlwaysFalseIf02() throws Exception {
        FunctUtils.cycle(NEXT_BOOLEAN_CYCLES, () ->
            stream(RANDS)
                    .map(rand -> rand.bools().probability(0.0).val())
                    .forEach(bol -> assertFalse(bol)));
    }


    /**
     * Test if the method:
     * > Stream<Boolean> streamBoolean(Double probability)
     * Returns streams that contain only FALSE stream for 0 probability
     * @throws Exception
     */
    @Test
    public void testStreamBooleanAlwaysFalseIf_2() throws Exception {
        FunctUtils.cycle(NEXT_BOOLEAN_CYCLES, () ->
                stream(RANDS)
                        .map(r -> r.bools().probability(0.0).stream().limit(BOOLEAN_STREAMS_LIMIT))
                        .forEach(s -> s.forEach(Assert::assertFalse)));
    }

    /**
     * Tests if the method:
     * >> Boolean val(Integer probability)
     * Throws an excpetion for a negative probability
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanNegativeNotProbability() throws Exception {
        RAND.bools().probability(-5.0).val();
    }

    /**
     * Tests if the method:
     * >> Boolean val(Double probability)
     * Throws an excpetion for a negative probability
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanNegativeNotProbability_2() throws Exception {
        RAND.bools().probability(-5.0).val();
    }


    /**
     * Tests if the method:
     * >> Boolean val(Integer probability)
     * Throws an exception for a probability bigger than 100%
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanGreaterThan100NotProbability() throws Exception {
        RAND.bools().probability(105.0).val();
    }

    /**
     * Tests if the method:
     * >> Boolean val(Double probability)
     * Throws an exception for a probability bigger than 100%
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNextBooleanGreaterThan100NotProbability2() throws Exception {
        RAND.bools().probability(105.0).val();
    }
}
