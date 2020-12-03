package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.unit.objects.model.SimpleBean;
import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Arrays.sort;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.*;

public class ShufflersTest {

    private static boolean areAnagrams(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        char[] s1c = s1.toCharArray();
        char[] s2c = s2.toCharArray();

        sort(s1c);
        sort(s2c);

        return Objects.deepEquals(s1c, s2c);
    }

    @Test(expected = NullPointerException.class)
    public void testGenericArrayNullSource() {
        M.shufflers().array(null).val();
    }

    @Test
    public void testGenericArrayEmptySource() {
        SimpleBean[] simpleBeans = {};
        SimpleBean[] newSimpleBeans = M.shufflers().array(simpleBeans).val();

        assertNotSame(newSimpleBeans, simpleBeans);
        assertNotNull(newSimpleBeans);
        assertEquals(0, newSimpleBeans.length);
    }

    @Test
    public void testGenericArrayOneElementSource() {
        SimpleBean[] simpleBeans = { new SimpleBean("A") };
        SimpleBean[] newSimpleBeans = M.shufflers().array(simpleBeans).val();

        assertNotNull(newSimpleBeans);
        assertNotSame(simpleBeans, newSimpleBeans);
        assertEquals(1, newSimpleBeans.length);
        assertEquals("A", newSimpleBeans[0].getS());
    }

    @Test
    public void testGenericArray() {
        loop(
                SHUFFLED_CYCLES,
                MOCKS,
                m -> {
                    SimpleBean[] arr = m.reflect(SimpleBean.class)
                                        .useDefaults(true)
                                        .array(SimpleBean.class, 50)
                                        .val();

                    Set<SimpleBean> allValues = new HashSet<>(asList(arr));

                    final SimpleBean[] newArr = m.shufflers()
                                                    .array(arr)
                                                    .val();

                    range(0, newArr.length).forEach(i ->
                            assertTrue(allValues.contains(newArr[i])));
                }
        );
    }

    @Test(expected = NullPointerException.class)
    public void testLongArrayNullSource() {
        M.shufflers().arrayLong(null).val();
    }


    @Test
    public void testLongArrayEmptySource() {
        long[] emptyArr = {};
        long[] newEmptyArr = M.shufflers().arrayLong(emptyArr).val();

        assertNotNull(newEmptyArr);
        assertNotSame(emptyArr, newEmptyArr);
        assertEquals(0, newEmptyArr.length);
    }

    @Test
    public void testLongArrayOneElement() {
        long[] arr = { 1 };
        long[] newArr = M.shufflers().arrayLong(arr).val();

        assertNotNull(newArr);
        assertNotSame(newArr, arr);
        assertEquals(1, newArr.length);
        assertEquals(1, newArr[0]);
    }

    @Test
    public void testLongArray() {
        loop(
                SHUFFLED_CYCLES,
                MOCKS,
                m -> {
                    long[] arr = m.longs()
                                 .arrayPrimitive(50)
                                 .val();

                    Set<Long> possibleValues = Arrays.stream(arr)
                                                        .boxed()
                                                        .collect(toSet());

                    long[] newArr = m.shufflers()
                                    .arrayLong(arr)
                                    .val();

                    assertNotSame(newArr, arr);
                    assertEquals(arr.length, newArr.length);

                    range(0, newArr.length)
                            .forEach(i -> assertTrue(possibleValues.contains(newArr[i])));
                }
        );
    }

    @Test(expected = NullPointerException.class)
    public void testIntArrayNullSource() {
        M.shufflers().arrayInt(null).val();
    }


    @Test
    public void testIntArrayEmptySource() {
        int[] emptyArr = {};
        int[] newEmptyArr = M.shufflers().arrayInt(emptyArr).val();

        assertNotNull(newEmptyArr);
        assertNotSame(emptyArr, newEmptyArr);
        assertEquals(0, newEmptyArr.length);
    }

    @Test
    public void testIntArrayOneElement() {
        int[] arr = { 1 };
        int[] newArr = M.shufflers().arrayInt(arr).val();

        assertNotNull(newArr);
        assertNotSame(newArr, arr);
        assertEquals(1, newArr.length);
        assertEquals(1, newArr[0]);
    }

    @Test
    public void testIntArray() {
        loop(
                SHUFFLED_CYCLES,
                MOCKS,
                m -> {
                    int[] arr = m.ints()
                            .arrayPrimitive(50)
                            .val();

                    Set<Integer> possibleValues = Arrays.stream(arr)
                            .boxed()
                            .collect(toSet());

                    int[] newArr = m.shufflers()
                            .arrayInt(arr)
                            .val();

                    assertNotSame(newArr, arr);
                    assertEquals(arr.length, newArr.length);

                    range(0, newArr.length)
                            .forEach(i -> assertTrue(possibleValues.contains(newArr[i])));
                }
        );
    }

    @Test(expected = NullPointerException.class)
    public void testDoubleArrayNullSource() {
        M.shufflers().arrayDouble(null).val();
    }


    @Test
    public void testDoubleArrayEmptySource() {
        double[] emptyArr = {};
        double[] newEmptyArr = M.shufflers().arrayDouble(emptyArr).val();

        assertNotNull(newEmptyArr);
        assertNotSame(emptyArr, newEmptyArr);
        assertEquals(0, newEmptyArr.length);
    }

    @Test
    public void testDoubleArrayOneElement() {
        double[] arr = { 1 };
        double[] newArr = M.shufflers().arrayDouble(arr).val();

        assertNotNull(newArr);
        assertNotSame(newArr, arr);
        assertEquals(1, newArr.length);
        assertEquals(1, newArr[0], 0.0);
    }

    @Test
    public void testDoubleArray() {
        loop(
                SHUFFLED_CYCLES,
                MOCKS,
                m -> {
                    double[] arr = m.doubles()
                            .arrayPrimitive(50)
                            .val();

                    Set<Double> possibleValues = Arrays.stream(arr)
                            .boxed()
                            .collect(toSet());

                    double[] newArr = m.shufflers()
                            .arrayDouble(arr)
                            .val();

                    assertNotSame(newArr, arr);
                    assertEquals(arr.length, newArr.length);

                    range(0, newArr.length)
                            .forEach(i -> assertTrue(possibleValues.contains(newArr[i])));
                }
        );
    }

    @Test(expected = NullPointerException.class)
    public void testFloatArrayNullSource() {
        M.shufflers().arrayFloat(null).val();
    }


    @Test
    public void testFloatArrayEmptySource() {
        float[] emptyArr = {};
        float[] newEmptyArr = M.shufflers().arrayFloat(emptyArr).val();

        assertNotNull(newEmptyArr);
        assertNotSame(emptyArr, newEmptyArr);
        assertEquals(0, newEmptyArr.length);
    }

    @Test
    public void testFloatArrayOneElement() {
        float[] arr = { 1 };
        float[] newArr = M.shufflers().arrayFloat(arr).val();

        assertNotNull(newArr);
        assertNotSame(newArr, arr);
        assertEquals(1, newArr.length);
        assertEquals(1, newArr[0], 0.0);
    }

    @Test
    public void testFloatArray() {
        loop(
                SHUFFLED_CYCLES,
                MOCKS,
                m -> {
                    float[] arr = m.floats()
                            .arrayPrimitive(50)
                            .val();

                    float[] newArr = m.shufflers()
                            .arrayFloat(arr)
                            .val();

                    assertNotSame(newArr, arr);
                    assertEquals(arr.length, newArr.length);

                }
        );
    }

    @Test(expected = NullPointerException.class)
    public void testArrayListNullSource() {
        M.shufflers().arrayList(null).val();
    }


    @Test
    public void testArrayListEmptySource() {
        ArrayList<Double> al = new ArrayList<>();
        ArrayList<Double> newAl = M.shufflers().arrayList(al).val();

        assertNotNull(newAl);
        assertEquals(al, newAl);
        assertTrue(newAl.isEmpty());
    }

    @Test
    public void testArrayListOneElement() {
        ArrayList<Double> al = new ArrayList<>();
        al.add(1.0);

        ArrayList<Double> newAl = M.shufflers().arrayList(al).val();

        assertNotNull(newAl);
        assertEquals(newAl, al);
        assertEquals(1, newAl.size());
        assertEquals(1.0, newAl.get(0), 0.0);
    }

    @Test
    public void testArrayListArray() {
        loop(
                SHUFFLED_CYCLES,
                MOCKS,
                m -> {
                    ArrayList<Double> al = (ArrayList<Double>) m.doubles().list(ArrayList::new, 50)
                            .val();

                    Set<Double> possibleValues = new HashSet<>(al);

                    ArrayList<Double> newAl = m.shufflers()
                            .arrayList(al)
                            .val();

                    assertNotEquals(newAl, al);
                    assertEquals(al.size(), newAl.size());

                    range(0, newAl.size())
                            .forEach(i -> assertTrue(possibleValues.contains(newAl.get(i))));
                }
        );
    }

    @Test(expected = NullPointerException.class)
    public void testStringNull() {
        M.shufflers().string(null).val();
    }

    @Test
    public void testStringEmpty() {
        String s1 = "";
        String s2 = M.shufflers().string(s1).val();
        assertNotNull(s2);
        assertEquals(0, s2.length());
        assertEquals(s1, s2);
    }

    @Test
    public void testString() {
        loop(
                SHUFFLED_CYCLES,
                MOCKS,
                m -> {
                    String s = m.strings().size(32).val();
                    String shuffled = m.shufflers().string(s).val();

                    assertNotNull(shuffled);
                    assertEquals(s.length(), shuffled.length());
                    assertTrue(areAnagrams(s, shuffled));
                }
        );
    }
}
