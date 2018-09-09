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

import net.andreinc.mockneat.abstraction.models.AbstractListNoInstance;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class MockUnitListMethodTest {

    @Test(expected = NullPointerException.class)
    public void testListNullType() throws Exception {
        Class<List> cls = null;
        M.ints().list(cls, 10).val();
    }

    @Test(expected = NullPointerException.class)
    public void testListSuppNullType() throws Exception {
        Supplier<List<Integer>> supp = null;
        M.ints().list(supp, 10).val();
    }

    @Test(expected = NullPointerException.class)
    public void testListSuppNullValueReturned() throws Exception {
        M.ints().list(() -> null, 10).val();
    }

    @Test
    public void testList0Size() throws Exception {
        assertTrue(0 == M.ints().list(LinkedList.class, 0).val().size());
    }

    @Test
    public void testListSupp0Size() throws Exception {
        List<Integer> list = M.ints().list(() -> new ArrayList<>(10), 0).val();
        assertTrue(list.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListNegativeSize() throws Exception {
        M.ints().list(LinkedList.class, -1).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListSuppNegativeSize() throws Exception {
        M.ints().list(() -> new ArrayList<>(), -1).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotImplementList() throws Exception {
        M.ints().list(AbstractListNoInstance.class, 10).val();
    }

    @Test
    public void testListCorrectSize0() throws Exception {
        loop(MOCK_CYCLES, () ->
                stream(MOCKS).forEach(r ->
                        assertTrue(r.ints().list(0).list(0).val().isEmpty())));
    }


    @Test
    public void testListCorrectSize0_1() throws Exception {
        loop(MOCK_CYCLES, () ->
                stream(MOCKS).forEach(r ->
                        assertTrue(r.ints().list(5000).list(0).val().isEmpty())));
    }

    @Test
    public void testListCorrectSize0_2() throws Exception {
        loop(MOCK_CYCLES, () ->
                stream(MOCKS).forEach(r -> {
                    List<List<Integer>> result = r.ints().list(10).list(5).val();
                    assertTrue(result.size()==5);
                    result.forEach(sub -> assertTrue(sub.size()==10));
                }));
    }

    @Test
    public void testListCorrectValues() throws Exception {
        loop(MOCK_CYCLES, () -> stream(MOCKS).forEach(r -> {
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

    @Test
    public void testListSuppCorrectValues() throws Exception {
        loop(
                MOCK_CYCLES,
                MOCKS,
                mockNeat -> {
                    List<List<List<Integer>>> result =
                            mockNeat.ints().range(100, 200)
                                    .list(() -> new LinkedList<>(), 5)
                                    .list(() -> new Stack<>(), 10)
                                    .list(() -> new ArrayList<>(), 5)
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
                }
        );
    }

    protected MockUnit getRecursiveRandUnitList(MockUnit ru, int stop) {
        Class[] listsImpls = new Class[] { ArrayList.class, LinkedList.class, Stack.class };
        while(stop-->0) {
            ru = ru.list(M.from(listsImpls).val(), 1);
        }
        return ru;
    }

    protected MockUnit getRecursiveRandUnitListSupp(MockUnit ru, int stop) {
        Supplier<List<Integer>>[] supps = new Supplier[]{ () -> new ArrayList<>(), () -> new LinkedList<>(), () -> new Stack<>() };
        while(stop-->0) {
            ru = ru.list(M.from(supps).val(), 1);
        }
        return ru;
    }

    protected List getRecursiveList() {
        MockUnit l = M.ints().list(1);
        return (List) getRecursiveRandUnitList(l, 1000).val();
    }

    protected List getRecursiveListSupp() {
        MockUnit l = M.ints().list(() -> new ArrayList<Integer>(), 1);
        return (List) getRecursiveRandUnitListSupp(l, 1000).val();
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
    public void testListSuppDeep() {
        List l = getRecursiveListSupp();
        while(l.get(0) instanceof List) {
            l = (List) l.get(0);
        }
        assertTrue(l.get(0) instanceof Integer);
    }

    @Test
    public void testListOfNulls() {
        List<Integer> integers = asList(null, null, null, null);
        loop(MOCK_CYCLES, () -> {
            stream(MOCKS).forEach(r -> {
                List<Integer> list = r.from(integers).list(LinkedList.class, 100).val();
                assertTrue(list instanceof LinkedList);
                list.forEach(i -> assertTrue(null==i));
            });
        });
    }

    @Test
    public void testListSuppOfNulls() {
        List<Integer> integers = asList(null, null, null, null);
        loop(MOCK_CYCLES, MOCKS, m -> {
            List<Integer> list = m.from(integers).list(() -> new ArrayList<>(), 10).val();
            assertTrue(list instanceof ArrayList);
            list.forEach(i -> assertTrue(null == i));
        });
    }

    // MockUnitInt size tests

    @Test(expected = NullPointerException.class)
    public void testListMockUnitNull() throws Exception {
        MockUnitInt intSize = null;
        M.ints().list(ArrayList.class, intSize).val();
    }

    @Test(expected = NullPointerException.class)
    public void testListSuppMockUnitNull() throws Exception {
        MockUnitInt intSize = null;
        M.ints().list(() -> new ArrayList<>(), intSize).val();
    }

    @Test(expected = NullPointerException.class)
    public void testListSuppMockUnitNull_1() throws Exception {
        MockUnitInt intSize = null;
        M.ints().list(intSize).val();
    }

    @Test
    public void testListSuppCorrectValuesMockUnitIntSize() {
        loop(MOCK_CYCLES, MOCKS, m -> {
            MockUnitInt listSize = m.ints().range(10, 20);
            List<Integer> list = m.ints().list(listSize).val();
            assertTrue(list.size() >= 10 && list.size() < 20);
        });
    }
}
