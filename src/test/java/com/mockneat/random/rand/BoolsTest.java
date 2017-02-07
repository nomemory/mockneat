package com.mockneat.random.rand;

import org.junit.Assert;
import org.junit.Test;

import static com.mockneat.random.rand.RandTestConstants.*;
import static com.mockneat.utils.FunctUtils.cycle;
import static java.util.Arrays.stream;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoolsTest {

    @Test
    public void testNextBooleanAlwaysTrueIf100() throws Exception {
        cycle(NEXT_BOOLEAN_CYCLES, () ->
                stream(RANDS)
                    .map(rand -> rand.bools().probability(100.0).val())
                    .forEach(bol -> assertTrue(bol)));
    }

    @Test
    public void testStreamBooleanAlwaysTrueIf100() throws Exception {
        cycle(NEXT_BOOLEAN_CYCLES, () ->
                stream(RANDS)
                    .map(r -> r.bools().probability(100.0).stream().val().limit(BOOLEAN_STREAMS_LIMIT))
                    .forEach(s -> s.forEach(Assert::assertTrue)));
    }

    @Test
    public void testNextBooleanAlwaysTrueIf1002() throws Exception {
        cycle(NEXT_BOOLEAN_CYCLES, () ->
            stream(RANDS)
                    .map(rand -> rand.bools().probability(100.0).val())
                    .forEach(bol -> assertTrue(bol)));
    }

    @Test
    public void testStreamBooleanAlwaysTrueIf100_2() throws Exception {
        cycle(NEXT_BOOLEAN_CYCLES, () ->
                stream(RANDS)
                        .map(r -> r.bools().probability(100.0).stream().val().limit(BOOLEAN_STREAMS_LIMIT))
                        .forEach(s -> s.forEach(Assert::assertTrue)));
    }

    @Test
    public void testNextBooleanAlwaysFalseIf() throws Exception {
        cycle(NEXT_BOOLEAN_CYCLES, () ->
            stream(RANDS)
                    .map(rand -> rand.bools().probability(0.0).val())
                    .forEach(bol -> assertFalse(bol)));
    }

    @Test
    public void testStreamBooleanAlwaysFalseIf0() throws Exception {
        cycle(NEXT_BOOLEAN_CYCLES, () ->
                stream(RANDS)
                        .map(r -> r.bools()
                                    .probability(0.0)
                                    .stream()
                                    .val()
                                    .limit(BOOLEAN_STREAMS_LIMIT))
                        .forEach(s -> s.forEach(Assert::assertFalse)));
    }

    @Test
    public void testNextBooleanAlwaysFalseIf02() throws Exception {
        cycle(NEXT_BOOLEAN_CYCLES, () ->
            stream(RANDS)
                    .map(rand -> rand.bools().probability(0.0).val())
                    .forEach(bol -> assertFalse(bol)));
    }

    @Test
    public void testStreamBooleanAlwaysFalseIf_2() throws Exception {
        cycle(NEXT_BOOLEAN_CYCLES, () ->
                stream(RANDS)
                        .map(r -> r.bools().probability(0.0).stream().val().limit(BOOLEAN_STREAMS_LIMIT))
                        .forEach(s -> s.forEach(Assert::assertFalse)));
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
}
