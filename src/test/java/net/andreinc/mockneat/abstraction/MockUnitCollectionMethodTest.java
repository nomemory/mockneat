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

import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.abstraction.models.AbstractListNoInstance;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;

import static java.util.Arrays.stream;
import static net.andreinc.mockneat.Constants.M;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.Constants.MOCK_CYCLES;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class MockUnitCollectionMethodTest {

    @Test(expected = NullPointerException.class)
    public void testCollectionNullType() throws Exception {
        Class<Collection<?>> cls = null;
        M.ints().collection(cls, 10).val();
    }

    @Test(expected = NullPointerException.class)
    public void testCollectionSuppNullType() throws Exception {
        Supplier<Collection<Integer>> supp = null;
        M.ints().collection(supp, 10).val();
    }

    @Test(expected = NullPointerException.class)
    public void testCollectionSuppNullSupp() throws Exception {
        M.ints().collection(() -> null, 10).val();
    }

    @Test
    public void testCollection0Size() throws Exception {
       assertTrue(M.ints().collection(PriorityQueue.class, 0).val() instanceof Collection);
       assertTrue(M.ints().collection(LinkedList.class, 0).val().isEmpty());
    }

    @Test
    public void testCollectionSupp0Size() {
        assertTrue(M.ints().collection(() -> new PriorityQueue<>(), 0).val() instanceof Collection);
        assertTrue(M.ints().collection(() -> new LinkedList<>(), 0).val().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCollectionNegativeSize() throws Exception {
        M.ints().collection(LinkedList.class, -1).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCollectionSuppNegativeSize() throws Exception {
        M.ints().collection(() -> new ArrayList<>(), -1).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotImplementList() throws Exception {
        M.ints().collection(AbstractListNoInstance.class, 10).val();
    }

    @Test
    public void testCollectionCorrectSize0() throws Exception {
        loop(MOCK_CYCLES, MOCKS,
                m -> assertTrue(m.ints().list(0).collection(0).val().isEmpty()));
    }

    @Test
    public void testLCollectionCorrectSize0_1() throws Exception {
        loop(MOCK_CYCLES, MOCKS,
                m -> assertTrue(m.ints().collection(5000).collection(0).val().isEmpty()));
    }

    @Test
    public void testCollectionCorrectSize0_2() throws Exception {
        loop(MOCK_CYCLES, MOCKS,
                m -> {
                    Collection<Collection<Integer>> result = m.ints().collection(10).collection(5).val();
                    assertTrue(result.size()==5);
                    result.forEach(sub -> assertTrue(sub.size()==10));
        });
    }

    @Test
    public void testCollectionCorrectValues() throws Exception {
        loop(MOCK_CYCLES, MOCKS, m -> {
            Collection<Collection<Collection<Integer>>> result =
                    m.ints().range(100, 200)
                            .collection(LinkedList.class, 5)
                            .collection(Stack.class, 10)
                            .collection(ArrayList.class, 5)
                            .val();

            // Iterate over all the values
            assertTrue(result instanceof Collection);
            result.forEach(stack -> {
                assertTrue(stack instanceof Collection);
                stack.forEach(linked -> {
                    assertTrue(linked instanceof Collection);
                    linked.forEach(i -> assertTrue(i >= 100 && i < 200));
                });
            });
        });
    }

    @Test
    public void testCollectionSuppCorrectValues() throws Exception {
        loop(MOCK_CYCLES, MOCKS, m -> {
            Collection<Collection<Collection<Integer>>> result =
                    m.ints().range(100, 200)
                            .collection(() -> new LinkedList<>(), 5)
                            .collection(() -> new Stack<>(), 10)
                            .collection(() -> new ArrayList<>(), 5)
                            .val();

            // Iterate over all the values
            assertTrue(result instanceof Collection);
            result.forEach(stack -> {
                assertTrue(stack instanceof Collection);
                stack.forEach(linked -> {
                    assertTrue(linked instanceof Collection);
                    linked.forEach(i -> assertTrue(i >= 100 && i < 200));
                });
            });

        });
    }

    protected MockUnit getRecursiveRandUnitCollection(MockUnit ru, int stop) {
        Class[] collection = new Class[] {
                ArrayList.class,
                LinkedList.class,
                Stack.class,
                HashSet.class
        };
        while(stop-->0)
            ru = ru.collection(M.from(collection).val(), 1);
        return ru;
    }

    protected MockUnit getRecursiveRandUnitCollectionSupp(MockUnit ru, int stop) {
        Supplier<Collection>[] supps = new Supplier[] {
                () -> new ArrayList<>(),
                () -> new LinkedList<>(),
                () -> new Stack<>(),
                () -> new HashSet<>()
        };
        while(stop-->0) {
            ru = ru.collection(M.from(supps).val(), 1);
        }
        return ru;
    }

    protected Collection getRecursiveCollection() {
        MockUnit l = M.ints().collection(1);
        return (Collection) getRecursiveRandUnitCollection(l, 1000).val();
    }

    protected Collection getRecursiveCollectionSupp() {
        MockUnit l = M.ints().collection(() -> new ArrayList<>(), 1);
        return (Collection) getRecursiveRandUnitCollectionSupp(l, 1000).val();
    }

    @Test
    public void testCollectionDeep() {
        Collection l = getRecursiveCollection();
        Optional opt = l.stream().findFirst();
        while(opt.get() instanceof Collection) {
            opt = ((Collection)opt.get()).stream().findFirst();
        }
        assertTrue(opt.get() instanceof Integer);
    }

    @Test
    public void testCollectionSuppDeep() {
        Collection l = getRecursiveCollectionSupp();
        Optional opt = l.stream().findFirst();
        while(opt.get() instanceof  Collection) {
            opt = ((Collection)opt.get()).stream().findFirst();
        }
        assertTrue(opt.get() instanceof Integer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCollectionTreeSetWithNulls() {
        List<Integer> list = Arrays.asList(null, null);
        loop(MOCK_CYCLES, () -> {
            stream(MOCKS).forEach(r -> {
                Collection c = r.from(list).collection(TreeSet.class, 100).val();
                c.forEach(e -> assertTrue(null==e));
            });
        });
    }

    @Test
    public void testCollectionHashSetWithNulls() {
        List<Integer> list = Arrays.asList(null, null);
        loop(MOCK_CYCLES, () -> {
            stream(MOCKS).forEach(r -> {
                Collection c = r.from(list).collection(HashSet.class, 100).val();
                c.forEach(e -> assertTrue(null==e));
            });
        });
    }

    // MockUnitInt sizes

    @Test(expected = NullPointerException.class)
    public void testCollectionMockUnitIntSizeNull() {
        MockUnitInt sizeUnit = null;
        M.ints().collection(sizeUnit).val();
    }

    @Test(expected = NullPointerException.class)
    public void testCollectionMockUnitIntSizeNull1() {
        MockUnitInt sizeUnit = null;
        M.ints().collection(HashSet.class, sizeUnit).val();
    }

    @Test(expected = NullPointerException.class)
    public void testCollectionSuppMockUnitSizeNull() {
        MockUnitInt sizeUnit = null;
        M.ints().collection(() -> new TreeSet<>(), sizeUnit).val();
    }

    @Test
    public void testCollectionSuppCorrectMockUnitIntSize() {
        loop(MOCK_CYCLES, MOCKS, m -> {
            Collection<Integer> set = m.intSeq()
                    .collection(() -> new HashSet<>(), m.ints().range(10, 20))
                    .val();

            assertTrue(set.size()>=10 && set.size()<20);
        });
    }

    @Test
    public void testCollectionCorrectMockUnitIntSize() {
        loop(MOCK_CYCLES, MOCKS, m -> {
            Collection<Integer> set = m.intSeq()
                    .collection(HashSet.class, m.ints().range(10, 20))
                    .val();
            assertTrue(set.size() >= 10 && set.size() < 20);
        });
    }

    @Test
    public void testCollectionCorrectMockUnitIntSize1() {
        loop(MOCK_CYCLES, MOCKS, m -> {
            Collection<Integer> set = m.intSeq()
                                        .collection(m.ints().range(10, 20))
                                        .val();
            assertTrue(set.size() >= 10 && set.size() < 20);
        });
    }
}
