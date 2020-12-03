package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.abstraction.models.AbstractSetNoInstance;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.*;

public class MockUnitSetMethodTest {

    @Test(expected = NullPointerException.class)
    public void testSetNullType() {
        M.ints().set((Class<Set<Integer>>) null, 10).val();
    }

    @Test(expected = NullPointerException.class)
    public void testSetSuppNullType() {
        M.ints().set((Supplier<Set<Integer>>) null, 10).val();
    }

    @Test(expected = NullPointerException.class)
    public void testSetSuppRetNullType() {
        M.ints().set(() -> null, 10).val();
    }

    @Test
    public void testSet0Iterations() {
        Set<Integer> st = M.ints().set(HashSet.class, 0).val();
        assertNotNull(st);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeSize() {
        M.ints().set(HashSet.class, -1).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSuppNegativeSize() {
        M.ints().set(TreeSet::new, -1).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotImplementSet() {
        M.ints().set(AbstractSetNoInstance.class, 10).val();
    }

    @Test
    public void testSetCorrectSize0() {
        loop(MOCK_CYCLES,
                MOCKS,
                m -> assertTrue(m.ints().set(0).set(0).val().isEmpty()));
    }

    @Test
    public void testSetCorrectSize0_1() {
        loop(MOCK_CYCLES,
                MOCKS,
                m -> assertTrue(m.ints().set(5000).set(0).val().isEmpty()));
    }

    @Test
    public void testSetCorrectSize0_2() {
        loop(MOCK_CYCLES, () ->
                stream(MOCKS).forEach(r -> {
                    Set<Set<Integer>> result = r.ints().set(10).set(5).val();
                    assertEquals(5, result.size());
                    result.forEach(sub -> assertEquals(10, sub.size()));
                }));
    }

    @Test
    public void testSetCorrectValues() {
        loop(MOCK_CYCLES, MOCKS, m -> {
            Set<Set<Set<Integer>>> result =
                    m.ints().range(100, 200)
                            .set(HashSet.class, 5)
                            .set(HashSet.class, 10)
                            .set(HashSet.class, 5)
                            .val();

            assertEquals(5, result.size());
            assertTrue(result instanceof HashSet);
            assertTrue(result.iterator().next() instanceof HashSet);
            assertTrue(result.iterator().next().iterator().next() instanceof HashSet);

            // Iterate over all the values
            result.forEach(i -> i.forEach(j -> j.forEach(k -> assertTrue(k >= 100 && k < 200))));
        });
    }

    @Test
    public void testSetSuppCorrectValues() {
        loop(MOCK_CYCLES, MOCKS, m -> {
            Set<Set<Set<Integer>>> result;
            result = m.ints()
                      .range(100, 200)
                      .set(TreeSet::new, 5)
                      .set(HashSet::new, 10)
                      .set(LinkedHashSet::new, 5)
                      .val();

            assertEquals(5, result.size());
            assertTrue(result instanceof LinkedHashSet);
            assertTrue(result.iterator().next() instanceof HashSet);
            assertTrue(result.iterator().next().iterator().next() instanceof TreeSet);

            // Iterate over all the values
            result.forEach(i -> i.forEach(j -> j.forEach(k -> assertTrue(k >= 100 && k < 200))));
        });
    }

    @Test
    public void testSetOfNulls() {
        List<Integer> integers = asList(null, null, null, null);
        loop(MOCK_CYCLES, () -> stream(MOCKS).forEach(r -> {
            Set<Integer> set = r.from(integers).set(HashSet.class, 100).val();
            assertTrue(set instanceof HashSet);
            set.forEach(Assert::assertNull);
        }));
    }

    // MockUnitInt sizes

    @Test(expected = NullPointerException.class)
    public void testSetMockUnitIntSizeNull() {
        M.ints().set(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testSetMockUnitIntSizeNull1() {
        M.ints().set(HashSet.class, null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testSetSuppMockUnitSizeNull() {
        M.ints().set(TreeSet::new, null).val();
    }

    @Test
    public void testSetSuppCorrectMockUnitIntSize() {
        loop(MOCK_CYCLES, MOCKS, m -> {
            Set<Integer> set = m.intSeq()
                                .set(HashSet::new, m.ints().range(10, 20))
                                .val();

            assertTrue(set.size()>=10 && set.size()<20);
        });
    }

    @Test
    public void testSetCorrectMockUnitIntSize() {
        loop(MOCK_CYCLES, MOCKS, m -> {
            Set<Integer> set = m.intSeq()
                                .set(HashSet.class, m.ints().range(10, 20))
                                .val();
            assertTrue(set.size() >= 10 && set.size() < 20);
        });
    }

    @Test
    public void testSetCorrectMockUnitIntSize1() {
        loop(MOCK_CYCLES, MOCKS, m -> {
            Set<Integer> set = m.intSeq()
                                .set(m.ints().range(10, 20))
                                .val();
            assertTrue(set.size() >= 10 && set.size() < 20);
        });
    }
}
