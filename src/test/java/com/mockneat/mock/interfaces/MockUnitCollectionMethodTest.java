package com.mockneat.mock.interfaces;

import com.mockneat.mock.interfaces.models.AbstractListNoInstance;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static com.mockneat.mock.Constants.M;
import static com.mockneat.mock.Constants.MOCKS;
import static com.mockneat.mock.Constants.MOCK_CYCLES;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static java.util.Arrays.stream;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 10/02/2017.
 */
public class MockUnitCollectionMethodTest {
    @Test(expected = NullPointerException.class)
    public void testCollectionNulLType() throws Exception {
        M.ints().collection(null, 10).val();
    }

    @Test
    public void testCollection0Size() throws Exception {
        Assert.assertTrue(
                M.ints().collection(PriorityQueue.class, 0).val() instanceof Collection);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCollectionNegativeSize() throws Exception {
        M.ints().collection(LinkedList.class, -1).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotImplementList() throws Exception {
        M.ints().collection(AbstractListNoInstance.class, 10).val();
    }

    @Test
    public void testCollectionCorrectSize0() throws Exception {
        loop(MOCK_CYCLES, () ->
                stream(MOCKS).forEach(r ->
                        assertTrue(r.ints().list(0).collection(0).val().isEmpty())));
    }

    @Test
    public void testLCollectionCorrectSize0_1() throws Exception {
        loop(MOCK_CYCLES, () ->
                stream(MOCKS).forEach(r ->
                        assertTrue(r.ints().collection(5000).collection(0).val().isEmpty())));
    }

    @Test
    public void testCollectionCorrectSize0_2() throws Exception {
        loop(MOCK_CYCLES, () ->
                stream(MOCKS).forEach(r -> {
                    Collection<Collection<Integer>> result = r.ints().collection(10).collection(5).val();
                    assertTrue(result.size()==5);
                    result.forEach(sub -> assertTrue(sub.size()==10));
                }));
    }

    @Test
    public void testCollectionCorrectValues() throws Exception {
        loop(MOCK_CYCLES, () -> stream(MOCKS).forEach(r -> {
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
            ru = ru.collection(M.from(collection).val(), 1);
        return ru;
    }

    protected Collection getRecursiveCollection() {
        MockUnit l = M.ints().collection(1);
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
}
