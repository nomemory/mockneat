package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.abstraction.models.AbstractListNoInstance;
import org.junit.Assert;
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
import static org.junit.Assert.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MockUnitListMethodTest {

    @Test(expected = NullPointerException.class)
    public void testListNullType() {
        M.ints().list((Class<List>) null, 10).val();
    }

    @Test(expected = NullPointerException.class)
    public void testListSuppNullType() {
        M.ints().list(( Supplier<List<Integer>>) null, 10).val();
    }

    @Test(expected = NullPointerException.class)
    public void testListSuppNullValueReturned() {
        M.ints().list(() -> null, 10).val();
    }

    @Test
    public void testList0Size() {
        assertEquals(0, M.ints().list(LinkedList.class, 0).val().size());
    }

    @Test
    public void testListSupp0Size() {
        List<Integer> list = M.ints().list(() -> new ArrayList<>(10), 0).val();
        assertTrue(list.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListNegativeSize() {
        M.ints().list(LinkedList.class, -1).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListSuppNegativeSize() {
        M.ints().list(ArrayList::new, -1).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotImplementList() {
        M.ints().list(AbstractListNoInstance.class, 10).val();
    }

    @Test
    public void testListCorrectSize0() {
        loop(MOCK_CYCLES, () ->
                stream(MOCKS).forEach(r ->
                        assertTrue(r.ints().list(0).list(0).val().isEmpty())));
    }


    @Test
    public void testListCorrectSize0_1() {
        loop(MOCK_CYCLES, () ->
                stream(MOCKS).forEach(r ->
                        assertTrue(r.ints().list(5000).list(0).val().isEmpty())));
    }

    @Test
    public void testListCorrectSize0_2() {
        loop(MOCK_CYCLES, () ->
                stream(MOCKS).forEach(r -> {
                    List<List<Integer>> result = r.ints().list(10).list(5).val();
                    assertEquals(5, result.size());
                    result.forEach(sub -> assertEquals(10, sub.size()));
                }));
    }

    @Test
    public void testListCorrectValues() {
        loop(MOCK_CYCLES, () -> stream(MOCKS).forEach(r -> {
            List<List<List<Integer>>> result =
                r.ints().range(100, 200)
                        .list(LinkedList.class, 5)
                        .list(Stack.class, 10)
                        .list(ArrayList.class, 5)
                        .val();

            assertEquals(5, result.size());
            assertTrue(result instanceof ArrayList);
            assertEquals(10, result.get(0).size());
            assertTrue(result.get(0) instanceof Stack);
            assertEquals(5, result.get(0).get(0).size());
            assertTrue(result.get(0).get(0) instanceof LinkedList);

            // Iterate over all the values
            result.forEach(stack ->
                    stack.forEach(linkedList ->
                            linkedList.forEach(i ->
                                    assertTrue(i >= 100 && i < 200))));
        }));
    }

    @Test
    public void testListSuppCorrectValues() {
        loop(
                MOCK_CYCLES,
                MOCKS,
                mockNeat -> {
                    List<List<List<Integer>>> result =
                            mockNeat.ints().range(100, 200)
                                    .list(LinkedList::new, 5)
                                    .list(Stack::new, 10)
                                    .list(ArrayList::new, 5)
                                    .val();


                    assertEquals(5, result.size());
                    assertTrue(result instanceof ArrayList);
                    assertEquals(10, result.get(0).size());
                    assertTrue(result.get(0) instanceof Stack);
                    assertEquals(5, result.get(0).get(0).size());
                    assertTrue(result.get(0).get(0) instanceof LinkedList);

                    // Iterate over all the values
                    result.forEach(stack ->
                            stack.forEach(linkedList ->
                                    linkedList.forEach(i ->
                                            assertTrue(i >= 100 && i < 200))));
                }
        );
    }

    @SuppressWarnings("SameParameterValue")
    protected MockUnit getRecursiveRandUnitList(MockUnit ru, int stop) {
        Class[] listsImpls = new Class[] { ArrayList.class, LinkedList.class, Stack.class };
        while(stop-->0) {
            ru = ru.list(M.from(listsImpls).val(), 1);
        }
        return ru;
    }

    @SuppressWarnings("SameParameterValue")
    protected MockUnit getRecursiveRandUnitListSupp(MockUnit ru, int stop) {
        Supplier<List<Integer>>[] supps = new Supplier[]{ArrayList::new, LinkedList::new, Stack::new};
        while(stop-->0) {
            ru = ru.list(M.from(supps).val(), 1);
        }
        return ru;
    }

    protected List getRecursiveList() {
        MockUnit l = M.ints().list(1);
        return (List) getRecursiveRandUnitList(l, 100).val();
    }

    protected List getRecursiveListSupp() {
        MockUnit l = M.ints().list(ArrayList::new, 1);
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
        loop(MOCK_CYCLES, () -> stream(MOCKS).forEach(r -> {
            List<Integer> list = r.from(integers).list(LinkedList.class, 100).val();
            assertTrue(list instanceof LinkedList);
            list.forEach(Assert::assertNull);
        }));
    }

    @Test
    public void testListSuppOfNulls() {
        List<Integer> integers = asList(null, null, null, null);
        loop(MOCK_CYCLES, MOCKS, m -> {
            List<Integer> list = m.from(integers).list(ArrayList::new, 10).val();
            assertTrue(list instanceof ArrayList);
            list.forEach(Assert::assertNull);
        });
    }

    // MockUnitInt size tests

    @Test(expected = NullPointerException.class)
    public void testListMockUnitNull() {
        M.ints().list(ArrayList.class, null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testListSuppMockUnitNull() {
        M.ints().list(ArrayList::new, null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testListSuppMockUnitNull_1() {
        M.ints().list(null).val();
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
