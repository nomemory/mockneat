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

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class MockUnitSetMethodTest {
    @Test(expected = NullPointerException.class)
    public void testSetNullType() throws Exception {
        M.ints().set(null, 10).val();
    }

    @Test
    public void testSet0Iterations() throws Exception {
        M.ints().set(HashSet.class, 0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativeSize() throws Exception {
        M.ints().set(HashSet.class, -1).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotImplementSet() throws Exception {
        M.ints().set(AbstractSetNoInstance.class, 10).val();
    }

    @Test
    public void testSetCorrectSize0() throws Exception {
        loop(MOCK_CYCLES, () ->
                stream(MOCKS).forEach(r ->
                        assertTrue(r.ints().set(0).set(0).val().isEmpty())));
    }

    @Test
    public void testSetCorrectSize0_1() throws Exception {
        loop(MOCK_CYCLES, () ->
                stream(MOCKS).forEach(r ->
                        assertTrue(r.ints().set(5000).set(0).val().isEmpty())));
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
        loop(MOCK_CYCLES, () -> stream(MOCKS).forEach(r -> {
            Set<Set<Set<Integer>>> result =
                    r.ints().range(100, 200)
                            .set(HashSet.class, 5)
                            .set(HashSet.class, 10)
                            .set(HashSet.class, 5)
                            .val();

            assertTrue(result.size()==5);
            assertTrue(result instanceof HashSet);
            assertTrue(result.iterator().next() instanceof HashSet);
            assertTrue(result.iterator().next().iterator().next() instanceof HashSet);

            // Iterate over all the values
            result.forEach(stack ->
                    stack.forEach(linkedSet ->
                            linkedSet.forEach(i ->
                                    assertTrue(i >= 100 && i < 200))));
        }));
    }

    protected MockUnit getRecursiveRandUnitSet(MockUnit ru, int stop) {
        Class[] setsImpls = new Class[] { TreeSet.class, HashSet.class, LinkedHashSet.class };
        while(stop-->0) {
            ru = ru.set(M.from(setsImpls).val(), 1);
        }
        return ru;
    }

    protected Set getRecursiveSet() {
        MockUnit l = M.ints().set(1);
        return (Set) getRecursiveRandUnitSet(l, 1000).val();
    }

    @Test
    public void testSetDeep() {

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


}
