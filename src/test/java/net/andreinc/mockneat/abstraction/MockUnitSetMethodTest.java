package net.andreinc.mockneat.abstraction;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.abstraction.models.AbstractSetNoInstance;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MockUnitSetMethodTest {

    @Test(expected = NullPointerException.class)
    public void testSetNullType() throws Exception {
        Class<Set> cls = null;
        M.ints().set(cls, 10).val();
    }

    @Test(expected = NullPointerException.class)
    public void testSetSuppNullType() throws Exception {
        Supplier<Set<Integer>> setSupplier = null;
        M.ints().set(setSupplier, 10).val();
    }

    @Test(expected = NullPointerException.class)
    public void testSetSuppRetNullType() throws Exception {
        M.ints().set(() -> null, 10).val();
    }

    @Test
    public void testSet0Iterations() {
        Set<Integer> st = M.ints().set(HashSet.class, 0).val();
        assertNotNull(st);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeSize() throws Exception {
        M.ints().set(HashSet.class, -1).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSuppNegativeSize() throws Exception {
        M.ints().set(() -> new TreeSet<>(), -1).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotImplementSet() throws Exception {
        M.ints().set(AbstractSetNoInstance.class, 10).val();
    }

    @Test
    public void testSetCorrectSize0() {
        loop(MOCK_CYCLES, MOCKS, m -> assertTrue(m.ints().set(0).set(0).val().isEmpty()));
    }

    @Test
    public void testSetCorrectSize0_1() throws Exception {
        loop(MOCK_CYCLES, MOCKS, m -> m.ints().set(5000).set(0).val().isEmpty());
    }

    @Test
    public void testSetCorrectSize0_2() throws Exception {
        loop(MOCK_CYCLES, () ->
                stream(MOCKS).forEach(r -> {
                    Set<Set<Integer>> result = r.ints().set(10).set(5).val();
                    assertTrue(result.size()==5);
                    result.forEach(sub -> assertTrue(sub.size()==10));
                }));
    }

    @Test
    public void testSetCorrectValues() throws Exception {
        loop(MOCK_CYCLES, MOCKS, m -> {
            Set<Set<Set<Integer>>> result =
                    m.ints().range(100, 200)
                            .set(HashSet.class, 5)
                            .set(HashSet.class, 10)
                            .set(HashSet.class, 5)
                            .val();

            assertTrue(result.size()==5);
            assertTrue(result instanceof HashSet);
            assertTrue(result.iterator().next() instanceof HashSet);
            assertTrue(result.iterator().next().iterator().next() instanceof HashSet);

            // Iterate over all the values
            result.forEach(i -> i.forEach(j -> j.forEach(k -> assertTrue(k >= 100 && k < 200))));
        });
    }

    @Test
    public void testSetSuppCorrectValues() throws Exception {
        loop(MOCK_CYCLES, MOCKS, m -> {
            Set<Set<Set<Integer>>> result = null;
            result = m.ints()
                      .range(100, 200)
                      .set(() -> new TreeSet<>(), 5)
                      .set(() -> new HashSet<>(), 10)
                      .set(() -> new LinkedHashSet<>(), 5)
                      .val();

            assertTrue(result.size()==5);
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
        loop(MOCK_CYCLES, () -> {
            stream(MOCKS).forEach(r -> {
                Set<Integer> set = r.from(integers).set(HashSet.class, 100).val();
                assertTrue(set instanceof HashSet);
                set.forEach(i -> assertTrue(null==i));
            });
        });
    }

    // MockUnitInt sizes

    @Test(expected = NullPointerException.class)
    public void testSetMockUnitIntSizeNull() {
        MockUnitInt sizeUnit = null;
        M.ints().set(sizeUnit).val();
    }

    @Test(expected = NullPointerException.class)
    public void testSetMockUnitIntSizeNull1() {
        MockUnitInt sizeUnit = null;
        M.ints().set(HashSet.class, sizeUnit).val();
    }

    @Test(expected = NullPointerException.class)
    public void testSetSuppMockUnitSizeNull() {
        MockUnitInt sizeUnit = null;
        M.ints().set(() -> new TreeSet<>(), sizeUnit).val();
    }

    @Test
    public void testCorrectMockUnitIntSize() {
        loop(MOCK_CYCLES, MOCKS, m -> {
            Set<Integer> set = m.intSeq()
                                .set(() -> new HashSet<>(), m.ints().range(10, 20))
                                .val();

            assertTrue(set.size()>=10 && set.size()<20);
        });
    }
}
