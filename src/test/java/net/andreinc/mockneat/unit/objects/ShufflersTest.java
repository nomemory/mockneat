package net.andreinc.mockneat.unit.objects;

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

import net.andreinc.mockneat.unit.objects.model.SimpleBean;
import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Arrays.sort;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
    public void testGenericArrayNullSource() throws Exception {
        M.shufflers().array(null).val();
    }

    @Test
    public void testGenericArrayEmptySource() throws Exception {
        SimpleBean[] simpleBeans = {};
        SimpleBean[] newSimpleBeans = M.shufflers().array(simpleBeans).val();

        assertTrue(newSimpleBeans != simpleBeans);
        assertNotNull(newSimpleBeans);
        assertTrue(newSimpleBeans.length == 0);
    }

    @Test
    public void testGenericArrayOneElementSource() throws Exception {
        SimpleBean[] simpleBeans = { new SimpleBean("A") };
        SimpleBean[] newSimpleBeans = M.shufflers().array(simpleBeans).val();

        assertNotNull(newSimpleBeans);
        assertTrue(simpleBeans!=newSimpleBeans);
        assertTrue(newSimpleBeans.length == 1);
        assertTrue( newSimpleBeans[0].getS().equals("A"));
    }

    @Test
    public void testGenericArray() throws Exception {
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
    public void testLongArrayNullSource() throws Exception {
        M.shufflers().arrayLong(null).val();
    }


    @Test
    public void testLongArrayEmptySource() throws Exception {
        long[] emptyArr = {};
        long[] newEmptyArr = M.shufflers().arrayLong(emptyArr).val();

        assertNotNull(newEmptyArr);
        assertTrue(emptyArr!=newEmptyArr);
        assertTrue(newEmptyArr.length==0);
    }

    @Test
    public void testLongArrayOneElement() throws Exception {
        long[] arr = { 1 };
        long[] newArr = M.shufflers().arrayLong(arr).val();

        assertNotNull(newArr);
        assertTrue(newArr!=arr);
        assertTrue(newArr.length == 1);
        assertTrue(newArr[0] == 1);
    }

    @Test
    public void testLongArray() throws Exception {
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

                    assertTrue(newArr!=arr);
                    assertTrue(arr.length == newArr.length);

                    range(0, newArr.length)
                            .forEach(i -> assertTrue(possibleValues.contains(newArr[i])));
                }
        );
    }

    @Test(expected = NullPointerException.class)
    public void testIntArrayNullSource() throws Exception {
        M.shufflers().arrayInt(null).val();
    }


    @Test
    public void testIntArrayEmptySource() throws Exception {
        int[] emptyArr = {};
        int[] newEmptyArr = M.shufflers().arrayInt(emptyArr).val();

        assertNotNull(newEmptyArr);
        assertTrue(emptyArr!=newEmptyArr);
        assertTrue(newEmptyArr.length==0);
    }

    @Test
    public void testIntArrayOneElement() throws Exception {
        int[] arr = { 1 };
        int[] newArr = M.shufflers().arrayInt(arr).val();

        assertNotNull(newArr);
        assertTrue(newArr!=arr);
        assertTrue(newArr.length == 1);
        assertTrue(newArr[0] == 1);
    }

    @Test
    public void testIntArray() throws Exception {
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

                    assertTrue(newArr!=arr);
                    assertTrue(arr.length == newArr.length);

                    range(0, newArr.length)
                            .forEach(i -> assertTrue(possibleValues.contains(newArr[i])));
                }
        );
    }

    @Test(expected = NullPointerException.class)
    public void testDoubleArrayNullSource() throws Exception {
        M.shufflers().arrayDouble(null).val();
    }


    @Test
    public void testDoubleArrayEmptySource() throws Exception {
        double[] emptyArr = {};
        double[] newEmptyArr = M.shufflers().arrayDouble(emptyArr).val();

        assertNotNull(newEmptyArr);
        assertTrue(emptyArr!=newEmptyArr);
        assertTrue(newEmptyArr.length==0);
    }

    @Test
    public void testDoubleArrayOneElement() throws Exception {
        double[] arr = { 1 };
        double[] newArr = M.shufflers().arrayDouble(arr).val();

        assertNotNull(newArr);
        assertTrue(newArr!=arr);
        assertTrue(newArr.length == 1);
        assertTrue(newArr[0] == 1);
    }

    @Test
    public void testDoubleArray() throws Exception {
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

                    assertTrue(newArr!=arr);
                    assertTrue(arr.length == newArr.length);

                    range(0, newArr.length)
                            .forEach(i -> assertTrue(possibleValues.contains(newArr[i])));
                }
        );
    }

    @Test(expected = NullPointerException.class)
    public void testFloatArrayNullSource() throws Exception {
        M.shufflers().arrayFloat(null).val();
    }


    @Test
    public void testFloatArrayEmptySource() throws Exception {
        float[] emptyArr = {};
        float[] newEmptyArr = M.shufflers().arrayFloat(emptyArr).val();

        assertNotNull(newEmptyArr);
        assertTrue(emptyArr!=newEmptyArr);
        assertTrue(newEmptyArr.length==0);
    }

    @Test
    public void testFloatArrayOneElement() throws Exception {
        float[] arr = { 1 };
        float[] newArr = M.shufflers().arrayFloat(arr).val();

        assertNotNull(newArr);
        assertTrue(newArr!=arr);
        assertTrue(newArr.length == 1);
        assertTrue(newArr[0] == 1);
    }

    @Test
    public void testFloatArray() throws Exception {
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

                    assertTrue(newArr!=arr);
                    assertTrue(arr.length == newArr.length);

                }
        );
    }

    @Test(expected = NullPointerException.class)
    public void testArrayListNullSource() throws Exception {
        M.shufflers().arrayList(null).val();
    }


    @Test
    public void testArrayListEmptySource() throws Exception {
        ArrayList<Double> al = new ArrayList<>();
        ArrayList<Double> newAl = M.shufflers().arrayList(al).val();

        assertNotNull(newAl);
        assertTrue(al.equals(newAl));
        assertTrue(newAl.isEmpty());
    }

    @Test
    public void testArrayListOneElement() throws Exception {
        ArrayList<Double> al = new ArrayList<>();
        al.add(1.0);

        ArrayList<Double> newAl = M.shufflers().arrayList(al).val();

        assertNotNull(newAl);
        assertTrue(newAl.equals(al));
        assertTrue(newAl.size() == 1);
        assertTrue(newAl.get(0).equals(1.0));
    }

    @Test
    public void testArrayListArray() throws Exception {
        loop(
                SHUFFLED_CYCLES,
                MOCKS,
                m -> {
                    ArrayList<Double> al = (ArrayList<Double>) m.doubles()
                            .list(ArrayList.class, 50)
                            .val();

                    Set<Double> possibleValues = new HashSet<>(al);

                    ArrayList<Double> newAl = m.shufflers()
                            .arrayList(al)
                            .val();

                    assertTrue(!newAl.equals(al));
                    assertTrue(al.size() == newAl.size());

                    range(0, newAl.size())
                            .forEach(i -> assertTrue(possibleValues.contains(newAl.get(i))));
                }
        );
    }

    @Test(expected = NullPointerException.class)
    public void testStringNull() throws Exception {
        M.shufflers().string(null).val();
    }

    @Test
    public void testStringEmpty() throws Exception {
        String s1 = "";
        String s2 = M.shufflers().string(s1).val();
        assertTrue(s2 != null);
        assertTrue( s2.length() == 0);
        assertTrue(s1.equals(s2));
    }

    @Test
    public void testString() throws Exception {
        loop(
                SHUFFLED_CYCLES,
                MOCKS,
                m -> {
                    String s = m.strings().size(32).val();
                    String shuffled = m.shufflers().string(s).val();

                    assertTrue(shuffled!=null);
                    assertTrue(s.length() == shuffled.length());
                    assertTrue(areAnagrams(s, shuffled));
                }
        );
    }
}
