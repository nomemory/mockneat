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

import junit.framework.Assert;
import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.abstraction.models.AbstractMapNoInstance;
import net.andreinc.mockneat.utils.MapCheckUtils;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MockUnitMapKeysMethodTest {

    /*----------------------------------
     * test map method with supplier
     ----------------------------------*/

    @Test(expected = NullPointerException.class)
    public void testMapKeysSupplierNullClass() throws Exception {
        Class<Map<?, ?>> cls = null;
        Constants.M.ints().mapKeys(cls, 10, () -> "abc").val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysSupplierNegativeSize() throws Exception {
        Constants.M.ints().mapKeys(HashMap.class, -1, () -> "abc").val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysSupplierNullSupplier() {
        Supplier<?> supp = null;
        Constants.M.ints().mapKeys(HashMap.class, 10, supp).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysSupplierCannotInstantiateMap() throws Exception {
        Constants.M.ints().mapKeys(AbstractMapNoInstance.class, 10, Constants.M.ints().supplier()).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysSupplierTreeMapNoComparable() throws Exception {
        Constants.M.ints().mapKeys(TreeMap.class, 100, () -> new ArrayList()).val();
    }

    @Test
    public void testMapKeysSupplierNullKeyHashMap() throws Exception {
        Integer[] ints = { null };
        Map<Integer, Integer> m = Constants.M.ints().range(0,5).mapKeys(HashMap.class, 10, Constants.M.from(ints).supplier()).val();

        Integer x = m.get(null);
        Assert.assertTrue(0 <= x && x < 5);
    }

    @Test
    public void testMapKeysSupplier() throws Exception {
        MockUnitInt ints = Constants.M.ints().range(0, 10);
        Class<? extends Map>[] maps =
                new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(10, 20)
                        .mapKeys(Constants.M.from(maps).val(), 10, ints.supplier())
                        .val(),
                map -> MapCheckUtils.testMap(map, MapCheckUtils.inRange(0, 10), MapCheckUtils.inRange(10, 20))
        );
    }

    @Test
    public void testMapKeysSupplierOverloaded() throws Exception {
        MockUnitString strings = Constants.M.strings().size(5);
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                      .range(0, 100)
                      .mapKeys(10, strings.supplier())
                      .val(),
                map -> MapCheckUtils.testMap(
                        map,
                        (k) -> {
                            if (!(k.length()==5))
                                fail();
                        },
                        MapCheckUtils.inRange(0, 100),
                        m -> {
                            if (!(m instanceof HashMap))
                                fail();
                        }
                )
        );
    }

    /*----------------------------------
     * test map method with iterable
     * default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, Iterable<R> keys)
     ----------------------------------*/

    @Test(expected = NullPointerException.class)
    public void testMapKeysIterableNullClass() throws Exception {
        Class<Map> cls = null;
        Constants.M.ints().mapKeys(cls, Constants.M.ints().list(10).val());
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysIterableNullIterable() throws Exception {
        List<String> nullList = null;
        Constants.M.ints().mapKeys(HashMap.class, nullList).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysIterableCannotInstantiateMap() throws Exception {
        Constants.M.ints().mapKeys(AbstractMapNoInstance.class, Constants.M.ints().list(10).val()).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysIterableTreeMapNoComparable() throws Exception {
        List<ArrayList> list = ((MockUnit<ArrayList>)() -> () -> new ArrayList()).list(10).val();
        Constants.M.ints().mapKeys(TreeMap.class, list).val();
    }

    @Test
    public void testMapKeysIterableNullKeyHashMap() throws Exception {
        Integer[] ints = { null };
        Map<Integer, Integer> m = Constants.M.ints().range(0,5).mapKeys(HashMap.class, Constants.M.from(ints).list(10).val()).val();

        Integer x = m.get(null);
        Assert.assertTrue(0 <= x && x < 5);
    }

    @Test
    public void testMapKeysIterable() throws Exception {
        MockUnitInt ints = Constants.M.ints().range(50, 100);
        Class<? extends Map>[] maps = new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(1, 5)
                        .mapKeys(Constants.M.from(maps).val(), ints.list(10).val())
                        .val(),
                map -> MapCheckUtils.testMap(
                        map,
                        MapCheckUtils.inRange(50, 100),
                        MapCheckUtils.inRange(1, 5)
                )
        );
    }

    @Test
    public void testMapKeysIterableOverloade() throws Exception {
        MockUnitInt ints = Constants.M.ints().range(1, 5);
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapKeys(ints.list(20).val())
                        .val(),
                map -> MapCheckUtils.testMap(
                            map,
                            MapCheckUtils.inRange(1, 5),
                            MapCheckUtils.inRange(100, 200),
                            m -> {
                                if (!(m  instanceof HashMap))
                                    fail();
                            }
                        )
        );
    }

    /*----------------------------------
     * test map method with array
     * default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, R[] keys)
     ----------------------------------*/

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayNullClass() throws Exception {
        Integer[] keys = {1,2,3};
        Class<Map> cls = null;
        Constants.M.ints().mapKeys(cls, keys).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayNullArray() throws Exception {
        Integer[] keys = null;
        Constants.M.ints().mapKeys(HashMap.class, keys);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysArrayCannotInstantiateMap() throws Exception {
        Integer[] keys = {1,2,3};
        Constants.M.ints().mapKeys(AbstractMapNoInstance.class, keys).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysArrayTreeMapNoComparable() throws Exception {
        ArrayList[] lists = { new ArrayList(), new ArrayList(), new ArrayList() };
        Constants.M.ints().mapKeys(TreeMap.class, lists).val();
    }

    @Test
    public void testMapKeysArrayNullKeyHashMap() throws Exception {
        Integer[] ints = { null };
        Map<Integer, Integer> map = Constants.M.ints().mapKeys(HashMap.class, ints).val();

        assertTrue(map.get(null) instanceof Integer);
    }

    @Test
    public void testMapKeysArray() throws Exception {
        Integer[] ints = Constants.M.ints().range(0, 10).array(100).val();
        Class<? extends Map>[] maps = new Class[]{LinkedHashMap.class, HashMap.class, TreeMap.class};
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints().range(10, 20).mapKeys(Constants.M.from(maps).val(), ints).val(),
                map -> MapCheckUtils.testMap(map, MapCheckUtils.inRange(0, 10), MapCheckUtils.inRange(10, 20))
        );
    }

    @Test
    public void testMapKeysArrayOverloaded() throws Exception {
        Integer[] ints = Constants.M.ints().range(0, 10).array(100).val();
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints().range(10, 20).mapKeys(ints).val(),
                map -> MapCheckUtils.testMap(map, MapCheckUtils.inRange(0, 10), MapCheckUtils.inRange(10, 20), m -> {
                    if (!(map instanceof HashMap))
                        fail();
                })
        );
    }

     /*----------------------------------
     * test map method with array int[]
     default MockUnit<Map<Integer, T>> mapKeys(Class<? extends Map> mapClass, int[] keys)
     ----------------------------------*/

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayIntNullClass() throws Exception {
        int[] keys = {1,2,3};
        Class<Map> cls = null;
        Constants.M.ints().mapKeys(cls, keys).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayIntNullArray() throws Exception {
        int[] keys = null;
        Constants.M.ints().mapKeys(HashMap.class, keys);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysArrayIntCannotInstantiateMap() throws Exception {
        int[] keys = {1,2,3};
        Constants.M.ints().mapKeys(AbstractMapNoInstance.class, keys).val();
    }


    @Test
    public void testMapKeysArrayInt() throws Exception {
        int[] ints = Constants.M.ints().range(0, 10).arrayPrimitive(100).val();
        Class<? extends Map>[] maps = new Class[]{LinkedHashMap.class, HashMap.class, TreeMap.class};
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints().range(10, 20).mapKeys(Constants.M.from(maps).val(), ints).val(),
                map -> MapCheckUtils.testMap(map, MapCheckUtils.inRange(0, 10), MapCheckUtils.inRange(10, 20))
        );
    }

    @Test
    public void testMapKeysArrayIntOverloaded() throws Exception {
        int[] ints = Constants.M.ints().range(0, 10).arrayPrimitive(100).val();
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints().range(10, 20).mapKeys(ints).val(),
                map -> MapCheckUtils.testMap(map, MapCheckUtils.inRange(0, 10), MapCheckUtils.inRange(10, 20), m -> {
                    if (!(m instanceof HashMap)) fail();
                })
        );
    }

    /*----------------------------------
     * test map method with array long[]
     default MockUnit<Map<Integer, T>> mapKeys(Class<? extends Map> mapClass, long[] keys)
     ----------------------------------*/

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayLongNullClass() throws Exception {
        Class<Map> cls = null;
        long[] keys = {1,2,3};
        Constants.M.ints().mapKeys(cls, keys).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayLongNullArray() throws Exception {
        long[] keys = null;
        Constants.M.ints().mapKeys(HashMap.class, keys);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysArrayLongCannotInstantiateMap() throws Exception {
        long[] keys = {1,2,3};
        Constants.M.ints().mapKeys(AbstractMapNoInstance.class, keys).val();
    }


    @Test
    public void testMapKeysArrayLong() throws Exception {
        long[] longs = Constants.M.longs().range(0l, 10l).arrayPrimitive(100).val();
        Class<? extends Map>[] maps = new Class[]{LinkedHashMap.class, HashMap.class, TreeMap.class};
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.longs().range(10, 20).mapKeys(Constants.M.from(maps).val(), longs).val(),
                map -> MapCheckUtils.testMap(map, MapCheckUtils.inRange(0l, 10l), MapCheckUtils.inRange(10l, 20l))
        );
    }

    @Test
    public void testMapKeysArrayLongOverloaded() throws Exception {
        long[] longs = Constants.M.longs().range(0l, 10l).arrayPrimitive(100).val();
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.longs().range(10l, 20l).mapKeys(longs).val(),
                map -> MapCheckUtils.testMap(map, MapCheckUtils.inRange(0l, 10l), MapCheckUtils.inRange(10l, 20l), m -> {
                    if (!(m instanceof HashMap)) fail();
                })
        );
    }

    /*----------------------------------
     * test map method with array double[]
     default MockUnit<Map<Integer, T>> mapKeys(Class<? extends Map> mapClass, double[] keys)
     ----------------------------------*/

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayDoubleNullClass() throws Exception {
        Class<Map> cls = null;
        double[] keys = {1.0, 2.0, 3.0};
        Constants.M.ints().mapKeys(cls, keys).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayDoubleNullArray() throws Exception {
        double[] keys = null;
        Constants.M.ints().mapKeys(HashMap.class, keys);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysArrayDoubleCannotInstantiateMap() throws Exception {
        double[] keys = {1.0, 2.0, 3.0};
        Constants.M.ints().mapKeys(AbstractMapNoInstance.class, keys).val();
    }


    @Test
    public void testMapKeysArrayDouble() throws Exception {
        double[] doubles = Constants.M.doubles().range(0.0, 10.0).arrayPrimitive(100).val();
        Class<? extends Map>[] maps = new Class[]{LinkedHashMap.class, HashMap.class, TreeMap.class};
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.doubles().range(10.0, 20.0).mapKeys(Constants.M.from(maps).val(), doubles).val(),
                map -> MapCheckUtils.testMap(map, MapCheckUtils.inRange(0.0, 10.0), MapCheckUtils.inRange(10.0, 20.0))
        );
    }

    @Test
    public void testMapKeysArrayDoubleOverloaded() throws Exception {
        double[] doubles = Constants.M.doubles().range(0.0, 10.0).arrayPrimitive(100).val();
        loop(
                Constants.MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.doubles().range(10.0, 20.0).mapKeys(doubles).val(),
                map -> MapCheckUtils.testMap(map, MapCheckUtils.inRange(0.0, 10.0), MapCheckUtils.inRange(10.0, 20.0), m -> {
                    if (!(m instanceof HashMap)) fail();
                })
        );
    }
}
