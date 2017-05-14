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

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class MockUnitListMethodTest {



    @Test(expected = NullPointerException.class)
    public void testListNullType() throws Exception {
        M.ints().list(null, 10).val();
    }

    @Test
    public void testList0Size() throws Exception {
        M.ints().list(LinkedList.class, 0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListNegativeSize() throws Exception {
        M.ints().list(LinkedList.class, -1).val();
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

    protected MockUnit getRecursiveRandUnitList(MockUnit ru, int stop) {
        Class[] listsImpls = new Class[] { ArrayList.class, LinkedList.class, Stack.class };
        while(stop-->0) {
            ru = ru.list(M.from(listsImpls).val(), 1);
        }
        return ru;
    }

    protected List getRecursiveList() {
        MockUnit l = M.ints().list(1);
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
        List<Integer> integers = asList(null, null, null, null);
        loop(MOCK_CYCLES, () -> {
            stream(MOCKS).forEach(r -> {
                List<Integer> list = r.from(integers).list(LinkedList.class, 100).val();
                assertTrue(list instanceof LinkedList);
                list.forEach(i -> assertTrue(null==i));
            });
        });
    }

}
