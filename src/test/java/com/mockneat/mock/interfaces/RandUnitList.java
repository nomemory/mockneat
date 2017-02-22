package com.mockneat.mock.interfaces;

import org.junit.Test;

import java.util.*;

import static com.mockneat.mock.RandTestConstants.RAND;
import static com.mockneat.mock.RandTestConstants.RANDS;
import static com.mockneat.mock.RandTestConstants.RU_CYCLES;
import static com.mockneat.mock.utils.FunctUtils.loop;
import static java.util.Arrays.stream;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 10/02/2017.
 */
public class RandUnitList {

    @Test(expected = NullPointerException.class)
    public void testListNullType() throws Exception {
        RAND.ints().list(null, 10).val();
    }

    @Test
    public void testList0Size() throws Exception {
        RAND.ints().list(LinkedList.class, 0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListNegativeSize() throws Exception {
        RAND.ints().list(LinkedList.class, -1).val();
    }

    @Test
    public void testListCorrectSize0() throws Exception {
        loop(RU_CYCLES, () ->
                stream(RANDS).forEach(r ->
                        assertTrue(r.ints().list(0).list(0).val().isEmpty())));
    }

    @Test
    public void testListCorrectSize0_1() throws Exception {
        loop(RU_CYCLES, () ->
                stream(RANDS).forEach(r ->
                        assertTrue(r.ints().list(5000).list(0).val().isEmpty())));
    }

    @Test
    public void testListCorrectSize0_2() throws Exception {
        loop(RU_CYCLES, () ->
                stream(RANDS).forEach(r -> {
                    List<List<Integer>> result = r.ints().list(10).list(5).val();
                    assertTrue(result.size()==5);
                    result.forEach(sub -> assertTrue(sub.size()==10));
                }));
    }

    @Test
    public void testListCorrectValues() throws Exception {
        loop(RU_CYCLES, () -> stream(RANDS).forEach(r -> {
            List<List<List<Integer>>> result =
                r.ints().range(100, 200)
                        .list(LinkedList.class, 5)
                        .list(Stack.class, 10)
                        .list(ArrayList.class, 5)
                        .val();

            assertTrue(result.size()==5);
            assertTrue(result instanceof ArrayList);
            assertTrue(result.get(0).size()==10);
            assertTrue(result.get(0) instanceof Stack);
            assertTrue(result.get(0).get(0).size()==5);
            assertTrue(result.get(0).get(0) instanceof LinkedList);

            // Iterate over all the values
            result.forEach(stack ->
                    stack.forEach(linkedList ->
                            linkedList.forEach(i ->
                                    assertTrue(i >= 100 && i < 200))));
        }));
    }

    protected MockUnit getRecursiveRandUnitList(MockUnit ru, int stop) {
        Class[] listsImpls = new Class[] { ArrayList.class, LinkedList.class, Stack.class };
        while(stop-->0) {
            ru = ru.list(RAND.from(listsImpls).val(), 1);
        }
        return ru;
    }

    protected List getRecursiveList() {
        MockUnit l = RAND.ints().list(1);
        return (List) getRecursiveRandUnitList(l, 1000).val();
    }

    @Test
    public void testListDeep() {
        List l = getRecursiveList();
        while(l.get(0) instanceof List) {
            l = (List) l.get(0);
        }
        assertTrue(l.get(0) instanceof Integer);
    }

    @Test
    public void testListOfNulls() {
        List<Integer> integers = Arrays.asList(null, null, null, null);
        loop(RU_CYCLES, () -> {
            stream(RANDS).forEach(r -> {
                List<Integer> list = r.from(integers).list(LinkedList.class, 100).val();
                assertTrue(list instanceof LinkedList);
                list.forEach(i -> assertTrue(null==i));
            });
        });
    }
}
