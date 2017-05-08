package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.unit.objects.model.SimpleBean;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ShufflersTest {

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

//                    Arrays.stream(arr).forEach(simpleBean -> System.out.printf("%s ", simpleBean));
//                    System.out.println();

                    Set<SimpleBean> allValues = new HashSet<>(asList(arr));

                    final SimpleBean[] newArr = m.shufflers()
                                                    .array(arr)
                                                    .val();

//                    Arrays.stream(newArr).forEach(simpleBean -> System.out.printf("%s ", simpleBean));
//                    System.out.println();

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
        assertTrue(al!=newAl);
        assertTrue(newAl.size()==0);
    }

    @Test
    public void testArrayListOneElement() throws Exception {
        ArrayList<Double> al = new ArrayList<>();
        al.add(1.0);

        ArrayList<Double> newAl = M.shufflers().arrayList(al).val();

        assertNotNull(newAl);
        assertTrue(newAl!=al);
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

                    assertTrue(newAl!=al);
                    assertTrue(al.size() == newAl.size());

                    range(0, newAl.size())
                            .forEach(i -> assertTrue(possibleValues.contains(newAl.get(i))));
                }
        );
    }
}
