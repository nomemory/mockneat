package com.mockneat.mock.interfaces.mockunitgeneric;

import com.mockneat.mock.interfaces.MockUnitInt;
import com.mockneat.mock.interfaces.models.AbstractMapNoInstance;
import org.junit.Test;

import java.util.*;

import static com.mockneat.mock.Constants.*;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static com.mockneat.mock.utils.MapCheckUtils.inRange;
import static com.mockneat.mock.utils.MapCheckUtils.testMap;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 06/03/2017.
 */
public class MockUnitMapValsMethodTest {
    /*
    Test:
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, int size, Supplier<R> valuesSupplier)
     */

    @Test(expected = NullPointerException.class)
    public void testMapValsSupplierNullClass() throws Exception {
        M.ints().mapVals(null, 10, () -> "abc").val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsSupplierNegativeSize() throws Exception {
        M.ints().mapVals(HashMap.class, -1, () -> "abc").val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsSupplierNullSupplier() throws Exception {
        M.ints().mapVals(HashMap.class, 10, null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsSupplierCannotInstantiateMap() throws Exception {
        M.ints().mapVals(AbstractMapNoInstance.class, 10, () -> "abc").val();
    }

    @Test
    public void testMapValsSupplierNullValuesHashMap() throws Exception {
        Map<Integer, Integer> map = M.from(new Integer[]{ null, null, null})
                                        .mapVals(HashMap.class, 100, M.ints().supplier())
                                        .val();

        assertTrue(map instanceof HashMap);
        assertTrue(map.get(null) instanceof Integer);
    }

    @Test
    public void testMapValsSupplier() throws Exception {
        MockUnitInt vals = M.ints().range(0, 10);
        Class<? extends Map>[] maps = new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };

        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.ints()
                      .range(100, 200)
                      .mapVals(M.from(maps).val(), 10, vals.supplier())
                      .val(),
                map -> testMap(
                        map,
                        inRange(100, 200),
                        inRange(0, 10)
                )
        );
    }

    @Test
    public void testMapValsSupplierOverloaded() throws Exception {
        Iterable<Integer> iterable = M.ints().range(10, 100).set(100).val();
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapVals(iterable)
                        .val(),
                map -> testMap(
                    map,
                    inRange(100, 200),
                    inRange(10, 100)
                )
        );
    }


    /*
    Test:
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, Iterable<R> values) {
    */

    @Test(expected = NullPointerException.class)
    public void testMapValsIterableNullClass() throws Exception {
        M.ints().mapVals(null, M.ints().list(10).list(10).val());
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsIterableNullIterable() throws Exception {
        List<Integer> it = null;
        M.ints().mapVals(HashMap.class, it);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsIterableCannotInstantiateMap() throws Exception {
        M.ints().mapVals(AbstractMapNoInstance.class, M.ints().list(10).val()).val();
    }

    @Test
    public void testMapValsNullValuesHashMap() throws Exception {
        Map<Integer, Integer> map = M.from(new Integer[]{ null, null, null})
                .mapVals(HashMap.class, M.ints().list(10).val())
                .val();

        assertTrue(map instanceof HashMap);
        assertTrue(map.get(null) instanceof Integer);
    }

    @Test
    public void testMapValsIterable() throws Exception {
        Iterable<Integer> vals = M.ints().range(0, 10).list(10).val();
        Class<? extends Map>[] maps = new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };

        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapVals(M.from(maps).val(), vals)
                        .val(),
                map -> testMap(
                        map,
                        inRange(100, 200),
                        inRange(0, 10)
                )
        );
    }

    @Test
    public void testMapValsIterableOverloaded() throws Exception {
        Iterable<Integer> vals =
                M.ints().range(0, 100).list(100).map(l -> new ArrayDeque<>(l)).val();
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapVals(vals)
                        .val(),
                map -> testMap(
                        map,
                        inRange(100, 200),
                        inRange(0, 100)
                )
        );
    }

    /*
    Test:
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, R[] values)
     */

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayNullClass() throws Exception {
        M.ints().mapVals(null, new Integer[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayNullArray() throws Exception {
        Integer[] arr = null;
        M.ints().mapVals(HashMap.class, arr).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsArrayCannotInstantiateMap() throws Exception {
        M.ints().mapVals(AbstractMapNoInstance.class, new Integer[]{1,2,3}).val();
    }

    @Test
    public void testMapValsArrayNullValuesHashMap() throws Exception {
        Map<Integer, Integer> map = M.from(new Integer[]{ null, null, null})
                .mapVals(HashMap.class, M.ints().array(10).val())
                .val();

        assertTrue(map instanceof HashMap);
        assertTrue(map.get(null) instanceof Integer);
    }

    @Test
    public void testMapValsArray() throws Exception {
        Integer[] array = M.ints().range(0, 100).array(100).val();
        Class<? extends Map>[] maps = new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapVals(M.from(maps).val(), array)
                        .val(),
                map -> testMap(
                        map,
                        inRange(100, 200),
                        inRange(0, 100)
                )
        );
    }

    @Test
    public void testMapValsArrayOverloaded() throws Exception {
        Integer[] array = M.ints().range(0, 100).array(100).val();
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapVals(array)
                        .val(),
                map -> testMap(
                        map,
                        inRange(100, 200),
                        inRange(0, 100)
                )
        );
    }

    /*
    Test:
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, int[] values)
     */

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayIntNullClass() throws Exception {
        M.ints().mapVals(null, new int[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayIntNullArray() throws Exception {
        int[] arr = null;
        M.ints().mapVals(HashMap.class, arr).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsArrayIntCannotInstantiateMap() throws Exception {
        M.ints().mapVals(AbstractMapNoInstance.class, new int[]{1,2,3}).val();
    }


    @Test
    public void testMapValsArrayInt() throws Exception {
        int[] array = M.ints().range(0, 100).arrayPrimitive(100).val();
        Class<? extends Map>[] maps = new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapVals(M.from(maps).val(), array)
                        .val(),
                map -> testMap(
                        map,
                        inRange(100, 200),
                        inRange(0, 100)
                )
        );
    }

    @Test
    public void testMapValsArrayIntOverloaded() throws Exception {
        int[] array = M.ints().range(0, 100).arrayPrimitive(100).val();
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapVals(array)
                        .val(),
                map -> testMap(
                        map,
                        inRange(100, 200),
                        inRange(0, 100)
                )
        );
    }

    /*
    Test:
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, double[] values)
     */


    @Test(expected = NullPointerException.class)
    public void testMapValsArrayDoubleNullClass() throws Exception {
        M.ints().mapVals(null, new double[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayDoubleNullArray() throws Exception {
        int[] arr = null;
        M.ints().mapVals(HashMap.class, arr).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsArrayDoubleCannotInstantiateMap() throws Exception {
        M.ints().mapVals(AbstractMapNoInstance.class, new double[]{1.0,2.0,3.0}).val();
    }


    @Test
    public void testMapValsArrayDouble() throws Exception {
        double[] array = M.doubles().range(0.0, 100.0).arrayPrimitive(100).val();
        Class<? extends Map>[] maps = new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.doubles()
                        .range(100.0, 200.0)
                        .mapVals(M.from(maps).val(), array)
                        .val(),
                map -> testMap(
                        map,
                        inRange(100.0, 200.0),
                        inRange(0.0, 100.0)
                )
        );
    }

    @Test
    public void testMapValsArrayDoubleOverloaded() throws Exception {
        double[] array = M.doubles().range(0.0, 100.0).arrayPrimitive(100).val();
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.doubles()
                        .range(100.0, 200.0)
                        .mapVals(array)
                        .val(),
                map -> testMap(
                        map,
                        inRange(100.0, 200.0),
                        inRange(0.0, 100.0)
                )
        );
    }

    /*
    Test:
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, long[] values)
     */

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayLongNullClass() throws Exception {
        M.ints().mapVals(null, new long[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayLongNullArray() throws Exception {
        long[] arr = null;
        M.ints().mapVals(HashMap.class, arr).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsArrayLongCannotInstantiateMap() throws Exception {
        M.ints().mapVals(AbstractMapNoInstance.class, new long[]{1l,2l,3l}).val();
    }


    @Test
    public void testMapValsArrayLong() throws Exception {
        long[] array = M.longs().range(0l, 100l).arrayPrimitive(100).val();
        Class<? extends Map>[] maps = new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.longs()
                        .range(100l, 200l)
                        .mapVals(M.from(maps).val(), array)
                        .val(),
                map -> testMap(
                        map,
                        inRange(100l, 200l),
                        inRange(0l, 100l)
                )
        );
    }

    @Test
    public void testMapValsArrayLongOverloaded() throws Exception {
        long[] array = M.longs().range(0l, 100l).arrayPrimitive(100).val();
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.longs()
                        .range(100l, 200l)
                        .mapVals(array)
                        .val(),
                map -> testMap(
                        map,
                        inRange(100l, 200l),
                        inRange(0l, 100l)
                )
        );
    }

}
