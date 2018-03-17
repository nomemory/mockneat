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
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static java.util.Arrays.stream;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class MockUnitCollectionMethodTest {
    @Test(expected = NullPointerException.class)
    public void testCollectionNulLType() throws Exception {
        Class<Collection<?>> cls = null;
        Constants.M.ints().collection(cls, 10).val();
    }

    @Test
    public void testCollection0Size() throws Exception {
        Assert.assertTrue(
                Constants.M.ints().collection(PriorityQueue.class, 0).val() instanceof Collection);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCollectionNegativeSize() throws Exception {
        Constants.M.ints().collection(LinkedList.class, -1).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotImplementList() throws Exception {
        Constants.M.ints().collection(AbstractListNoInstance.class, 10).val();
    }

    @Test
    public void testCollectionCorrectSize0() throws Exception {
        loop(Constants.MOCK_CYCLES, () ->
                stream(Constants.MOCKS).forEach(r ->
                        assertTrue(r.ints().list(0).collection(0).val().isEmpty())));
    }

    @Test
    public void testLCollectionCorrectSize0_1() throws Exception {
        loop(Constants.MOCK_CYCLES, () ->
                stream(Constants.MOCKS).forEach(r ->
                        assertTrue(r.ints().collection(5000).collection(0).val().isEmpty())));
    }

    @Test
    public void testCollectionCorrectSize0_2() throws Exception {
        loop(Constants.MOCK_CYCLES, () ->
                stream(Constants.MOCKS).forEach(r -> {
                    Collection<Collection<Integer>> result = r.ints().collection(10).collection(5).val();
                    assertTrue(result.size()==5);
                    result.forEach(sub -> assertTrue(sub.size()==10));
                }));
    }

    @Test
    public void testCollectionCorrectValues() throws Exception {
        loop(Constants.MOCK_CYCLES, () -> stream(Constants.MOCKS).forEach(r -> {
            Collection<Collection<Collection<Integer>>> result =
                    r.ints().range(100, 200)
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
        }));
    }

    protected MockUnit getRecursiveRandUnitCollection(MockUnit ru, int stop) {
        Class[] collection = new Class[] {
                ArrayList.class,
                LinkedList.class,
                Stack.class,
                HashSet.class
        };
        while(stop-->0)
            ru = ru.collection(Constants.M.from(collection).val(), 1);
        return ru;
    }

    protected Collection getRecursiveCollection() {
        MockUnit l = Constants.M.ints().collection(1);
        return (Collection) getRecursiveRandUnitCollection(l, 1000).val();
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

    @Test(expected = IllegalArgumentException.class)
    public void testCollectionTreeSetWithNulls() {
        List<Integer> list = Arrays.asList(null, null);
        loop(Constants.MOCK_CYCLES, () -> {
            stream(Constants.MOCKS).forEach(r -> {
                Collection c = r.from(list).collection(TreeSet.class, 100).val();
                c.forEach(e -> assertTrue(null==e));
            });
        });
    }

    @Test
    public void testCollectionHashSetWithNulls() {
        List<Integer> list = Arrays.asList(null, null);
        loop(Constants.MOCK_CYCLES, () -> {
            stream(Constants.MOCKS).forEach(r -> {
                Collection c = r.from(list).collection(HashSet.class, 100).val();
                c.forEach(e -> assertTrue(null==e));
            });
        });
    }
}
