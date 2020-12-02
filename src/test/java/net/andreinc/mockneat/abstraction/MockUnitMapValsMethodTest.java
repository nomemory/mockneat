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

@SuppressWarnings({"ALL"})
public class MockUnitMapValsMethodTest {

    @Test(expected = NullPointerException.class)
    public void testMapValsSupplierNullClass() {
        Class<Map> cls = null;
        Constants.M.ints().mapVals(cls, 10, () -> "abc").val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsSupplierNegativeSize() {
        Constants.M.ints().mapVals(HashMap.class, -1, () -> "abc").val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsSupplierNullSupplier() {
        Constants.M.ints().mapVals(HashMap.class, 10, null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsSupplierCannotInstantiateMap() {
        Constants.M.ints().mapVals(AbstractMapNoInstance.class, 10, () -> "abc").val();
    }

    @Test
    public void testMapValsSupplierNullValuesHashMap() {
        Map<Integer, Integer> map = Constants.M.from(new Integer[]{ null, null, null})
                                        .mapVals(HashMap.class, 100, Constants.M.ints().supplier())
                                        .val();

        assertTrue(map instanceof HashMap);
    }

    @Test
    public void testMapValsSupplier() {
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
    public void testMapValsSupplierOverloaded() {
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
    public void testMapValsIterableNullClass() {
        Class<Map> cls = null;
        Constants.M.ints().mapVals(cls, Constants.M.ints().list(10).list(10).val());
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsIterableNullIterable() {
        List<Integer> it = null;
        Constants.M.ints().mapVals(HashMap.class, it);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsIterableCannotInstantiateMap() {
        Constants.M.ints().mapVals(AbstractMapNoInstance.class, Constants.M.ints().list(10).val()).val();
    }

    @Test
    public void testMapValsNullValuesHashMap() {
        Map<Integer, Integer> map = Constants.M.from(new Integer[]{ null, null, null})
                .mapVals(HashMap.class, Constants.M.ints().list(10).val())
                .val();

        assertTrue(map instanceof HashMap);
    }

    @Test
    public void testMapValsIterable() {
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
    public void testMapValsIterableOverloaded() {
        Iterable<Integer> vals =
                Constants.M.ints().range(0, 100).list(100).map(ArrayDeque::new).val();
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
    public void testMapValsArrayNullClass() {
        Class<Map> cls = null;
        Constants.M.ints().mapVals(cls, new Integer[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayNullArray() {
        Integer[] arr = null;
        Constants.M.ints().mapVals(HashMap.class, arr).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsArrayCannotInstantiateMap() {
        Constants.M.ints().mapVals(AbstractMapNoInstance.class, new Integer[]{1,2,3}).val();
    }

    @Test
    public void testMapValsArrayNullValuesHashMap() {
        Map<Integer, Integer> map = Constants.M.from(new Integer[]{ null, null, null})
                .mapVals(HashMap.class, Constants.M.ints().array(10).val())
                .val();

        assertTrue(map instanceof HashMap);
    }

    @Test
    public void testMapValsArray() {
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
    public void testMapValsArrayOverloaded() {
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
    public void testMapValsArrayIntNullClass() {
        Class<Map> cls = null;
        Constants.M.ints().mapVals(cls, new int[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayIntNullArray() {
        int[] arr = null;
        Constants.M.ints().mapVals(HashMap.class, arr).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsArrayIntCannotInstantiateMap() {
        Constants.M.ints().mapVals(AbstractMapNoInstance.class, new int[]{1,2,3}).val();
    }


    @Test
    public void testMapValsArrayInt() {
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
    public void testMapValsArrayIntOverloaded() {
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
    public void testMapValsArrayDoubleNullClass() {
        Class<Map> cls = null;
        Constants.M.ints().mapVals(cls, new double[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayDoubleNullArray() {
        int[] arr = null;
        Constants.M.ints().mapVals(HashMap.class, arr).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsArrayDoubleCannotInstantiateMap() {
        Constants.M.ints().mapVals(AbstractMapNoInstance.class, new double[]{1.0,2.0,3.0}).val();
    }


    @Test
    public void testMapValsArrayDouble() {
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
    public void testMapValsArrayDoubleOverloaded() {
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

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayLongNullClass() {
        Class<Map> cls = null;
        Constants.M.ints().mapVals(cls, new long[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapValsArrayLongNullArray() {
        long[] arr = null;
        Constants.M.ints().mapVals(HashMap.class, arr).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapValsArrayLongCannotInstantiateMap() {
        Constants.M.ints().mapVals(AbstractMapNoInstance.class, new long[]{1L, 2L, 3L}).val();
    }


    @Test
    public void testMapValsArrayLong() {
        long[] array = Constants.M.longs().range(0L, 100L).arrayPrimitive(100).val();
        Class<? extends Map>[] maps = new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.longs()
                        .range(100L, 200L)
                        .mapVals(Constants.M.from(maps).val(), array)
                        .val(),
                map -> MapCheckUtils.testMap(
                        map,
                        MapCheckUtils.inRange(100L, 200L),
                        MapCheckUtils.inRange(0L, 100L)
                )
        );
    }

    @Test
    public void testMapValsArrayLongOverloaded() {
        long[] array = Constants.M.longs().range(0L, 100L).arrayPrimitive(100).val();
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.longs()
                        .range(100L, 200L)
                        .mapVals(array)
                        .val(),
                map -> MapCheckUtils.testMap(
                        map,
                        MapCheckUtils.inRange(100L, 200L),
                        MapCheckUtils.inRange(0L, 100L)
                )
        );
    }

}
