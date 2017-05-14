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

import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.abstraction.models.AbstractMapNoInstance;
import net.andreinc.mockneat.utils.MapCheckUtils;
import org.junit.Test;

import java.util.*;

import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class MockUnitMapValsMethodTest {
    /*
    Test:
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, int size, Supplier<R> valuesSupplier)
     */

    @Test(expected = NullPointerException.class)
    public void testMapValsSupplierNullClass() throws Exception {
        Constants.M.ints().mapVals(null, 10, () -> "abc").val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsSupplierNegativeSize() throws Exception {
        Constants.M.ints().mapVals(HashMap.class, -1, () -> "abc").val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsSupplierNullSupplier() throws Exception {
        Constants.M.ints().mapVals(HashMap.class, 10, null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsSupplierCannotInstantiateMap() throws Exception {
        Constants.M.ints().mapVals(AbstractMapNoInstance.class, 10, () -> "abc").val();
    }

    @Test
    public void testMapValsSupplierNullValuesHashMap() throws Exception {
        Map<Integer, Integer> map = Constants.M.from(new Integer[]{ null, null, null})
                                        .mapVals(HashMap.class, 100, Constants.M.ints().supplier())
                                        .val();

        assertTrue(map instanceof HashMap);
        assertTrue(map.get(null) instanceof Integer);
    }

    @Test
    public void testMapValsSupplier() throws Exception {
        MockUnitInt vals = Constants.M.ints().range(0, 10);
        Class<? extends Map>[] maps = new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };

        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                      .range(100, 200)
                      .mapVals(Constants.M.from(maps).val(), 10, vals.supplier())
                      .val(),
                map -> MapCheckUtils.testMap(
                        map,
                        MapCheckUtils.inRange(100, 200),
                        MapCheckUtils.inRange(0, 10)
                )
        );
    }

    @Test
    public void testMapValsSupplierOverloaded() throws Exception {
        Iterable<Integer> iterable = Constants.M.ints().range(10, 100).set(100).val();
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapVals(iterable)
                        .val(),
                map -> MapCheckUtils.testMap(
                    map,
                    MapCheckUtils.inRange(100, 200),
                    MapCheckUtils.inRange(10, 100)
                )
        );
    }


    /*
    Test:
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, Iterable<R> values) {
    */

    @Test(expected = NullPointerException.class)
    public void testMapValsIterableNullClass() throws Exception {
        Constants.M.ints().mapVals(null, Constants.M.ints().list(10).list(10).val());
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsIterableNullIterable() throws Exception {
        List<Integer> it = null;
        Constants.M.ints().mapVals(HashMap.class, it);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsIterableCannotInstantiateMap() throws Exception {
        Constants.M.ints().mapVals(AbstractMapNoInstance.class, Constants.M.ints().list(10).val()).val();
    }

    @Test
    public void testMapValsNullValuesHashMap() throws Exception {
        Map<Integer, Integer> map = Constants.M.from(new Integer[]{ null, null, null})
                .mapVals(HashMap.class, Constants.M.ints().list(10).val())
                .val();

        assertTrue(map instanceof HashMap);
        assertTrue(map.get(null) instanceof Integer);
    }

    @Test
    public void testMapValsIterable() throws Exception {
        Iterable<Integer> vals = Constants.M.ints().range(0, 10).list(10).val();
        Class<? extends Map>[] maps = new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };

        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapVals(Constants.M.from(maps).val(), vals)
                        .val(),
                map -> MapCheckUtils.testMap(
                        map,
                        MapCheckUtils.inRange(100, 200),
                        MapCheckUtils.inRange(0, 10)
                )
        );
    }

    @Test
    public void testMapValsIterableOverloaded() throws Exception {
        Iterable<Integer> vals =
                Constants.M.ints().range(0, 100).list(100).map(l -> new ArrayDeque<>(l)).val();
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapVals(vals)
                        .val(),
                map -> MapCheckUtils.testMap(
                        map,
                        MapCheckUtils.inRange(100, 200),
                        MapCheckUtils.inRange(0, 100)
                )
        );
    }

    /*
    Test:
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, R[] values)
     */

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayNullClass() throws Exception {
        Constants.M.ints().mapVals(null, new Integer[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayNullArray() throws Exception {
        Integer[] arr = null;
        Constants.M.ints().mapVals(HashMap.class, arr).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsArrayCannotInstantiateMap() throws Exception {
        Constants.M.ints().mapVals(AbstractMapNoInstance.class, new Integer[]{1,2,3}).val();
    }

    @Test
    public void testMapValsArrayNullValuesHashMap() throws Exception {
        Map<Integer, Integer> map = Constants.M.from(new Integer[]{ null, null, null})
                .mapVals(HashMap.class, Constants.M.ints().array(10).val())
                .val();

        assertTrue(map instanceof HashMap);
        assertTrue(map.get(null) instanceof Integer);
    }

    @Test
    public void testMapValsArray() throws Exception {
        Integer[] array = Constants.M.ints().range(0, 100).array(100).val();
        Class<? extends Map>[] maps = new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapVals(Constants.M.from(maps).val(), array)
                        .val(),
                map -> MapCheckUtils.testMap(
                        map,
                        MapCheckUtils.inRange(100, 200),
                        MapCheckUtils.inRange(0, 100)
                )
        );
    }

    @Test
    public void testMapValsArrayOverloaded() throws Exception {
        Integer[] array = Constants.M.ints().range(0, 100).array(100).val();
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapVals(array)
                        .val(),
                map -> MapCheckUtils.testMap(
                        map,
                        MapCheckUtils.inRange(100, 200),
                        MapCheckUtils.inRange(0, 100)
                )
        );
    }

    /*
    Test:
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, int[] values)
     */

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayIntNullClass() throws Exception {
        Constants.M.ints().mapVals(null, new int[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayIntNullArray() throws Exception {
        int[] arr = null;
        Constants.M.ints().mapVals(HashMap.class, arr).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsArrayIntCannotInstantiateMap() throws Exception {
        Constants.M.ints().mapVals(AbstractMapNoInstance.class, new int[]{1,2,3}).val();
    }


    @Test
    public void testMapValsArrayInt() throws Exception {
        int[] array = Constants.M.ints().range(0, 100).arrayPrimitive(100).val();
        Class<? extends Map>[] maps = new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapVals(Constants.M.from(maps).val(), array)
                        .val(),
                map -> MapCheckUtils.testMap(
                        map,
                        MapCheckUtils.inRange(100, 200),
                        MapCheckUtils.inRange(0, 100)
                )
        );
    }

    @Test
    public void testMapValsArrayIntOverloaded() throws Exception {
        int[] array = Constants.M.ints().range(0, 100).arrayPrimitive(100).val();
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapVals(array)
                        .val(),
                map -> MapCheckUtils.testMap(
                        map,
                        MapCheckUtils.inRange(100, 200),
                        MapCheckUtils.inRange(0, 100)
                )
        );
    }

    /*
    Test:
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, double[] values)
     */


    @Test(expected = NullPointerException.class)
    public void testMapValsArrayDoubleNullClass() throws Exception {
        Constants.M.ints().mapVals(null, new double[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayDoubleNullArray() throws Exception {
        int[] arr = null;
        Constants.M.ints().mapVals(HashMap.class, arr).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsArrayDoubleCannotInstantiateMap() throws Exception {
        Constants.M.ints().mapVals(AbstractMapNoInstance.class, new double[]{1.0,2.0,3.0}).val();
    }


    @Test
    public void testMapValsArrayDouble() throws Exception {
        double[] array = Constants.M.doubles().range(0.0, 100.0).arrayPrimitive(100).val();
        Class<? extends Map>[] maps = new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.doubles()
                        .range(100.0, 200.0)
                        .mapVals(Constants.M.from(maps).val(), array)
                        .val(),
                map -> MapCheckUtils.testMap(
                        map,
                        MapCheckUtils.inRange(100.0, 200.0),
                        MapCheckUtils.inRange(0.0, 100.0)
                )
        );
    }

    @Test
    public void testMapValsArrayDoubleOverloaded() throws Exception {
        double[] array = Constants.M.doubles().range(0.0, 100.0).arrayPrimitive(100).val();
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.doubles()
                        .range(100.0, 200.0)
                        .mapVals(array)
                        .val(),
                map -> MapCheckUtils.testMap(
                        map,
                        MapCheckUtils.inRange(100.0, 200.0),
                        MapCheckUtils.inRange(0.0, 100.0)
                )
        );
    }

    /*
    Test:
    default <R> MockUnit<Map<T, R>> mapVals(Class<? extends Map> mapClass, long[] values)
     */

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayLongNullClass() throws Exception {
        Constants.M.ints().mapVals(null, new long[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayLongNullArray() throws Exception {
        long[] arr = null;
        Constants.M.ints().mapVals(HashMap.class, arr).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsArrayLongCannotInstantiateMap() throws Exception {
        Constants.M.ints().mapVals(AbstractMapNoInstance.class, new long[]{1l,2l,3l}).val();
    }


    @Test
    public void testMapValsArrayLong() throws Exception {
        long[] array = Constants.M.longs().range(0l, 100l).arrayPrimitive(100).val();
        Class<? extends Map>[] maps = new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.longs()
                        .range(100l, 200l)
                        .mapVals(Constants.M.from(maps).val(), array)
                        .val(),
                map -> MapCheckUtils.testMap(
                        map,
                        MapCheckUtils.inRange(100l, 200l),
                        MapCheckUtils.inRange(0l, 100l)
                )
        );
    }

    @Test
    public void testMapValsArrayLongOverloaded() throws Exception {
        long[] array = Constants.M.longs().range(0l, 100l).arrayPrimitive(100).val();
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.longs()
                        .range(100l, 200l)
                        .mapVals(array)
                        .val(),
                map -> MapCheckUtils.testMap(
                        map,
                        MapCheckUtils.inRange(100l, 200l),
                        MapCheckUtils.inRange(0l, 100l)
                )
        );
    }

}
