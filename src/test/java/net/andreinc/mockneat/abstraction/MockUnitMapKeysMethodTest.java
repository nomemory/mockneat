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
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;

import static net.andreinc.mockneat.Constants.M;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.Constants.MOCK_CYCLES;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static net.andreinc.mockneat.utils.MapCheckUtils.testMap;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MockUnitMapKeysMethodTest {

    /*----------------------------------
     * test map method with supplier
     ----------------------------------*/

    @Test(expected = NullPointerException.class)
    public void testMapKeysSupplierNullClass() {
        Class<Map<?, ?>> cls = null;
        M.ints().mapKeys(cls, 10, () -> "abc").val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysSuppSupplierNullClass() {
        Supplier<Map<String, Integer>> mapSupplier = null;
        M.ints().mapKeys(mapSupplier, 10, () -> "abc").val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysSupplierNegativeSize() {
        M.ints().mapKeys(HashMap.class, -1, () -> "abc").val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysSuppSupplierNegativeSize() {
        M.ints().mapKeys(HashMap::new, -1, () -> "abc").val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysSupplierNullSupplier() {
        Supplier<?> supp = null;
        M.ints().mapKeys(HashMap.class, 10, supp).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysSuppSupplierNullSupplier() {
        Supplier<?> supp = null;
        M.ints().mapKeys(LinkedHashMap::new, 10, supp).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysSupplierCannotInstantiateMap() {
        M.ints().mapKeys(AbstractMapNoInstance.class, 10, M.ints().supplier()).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysSupplierTreeMapNoComparable() {
        M.ints().mapKeys(TreeMap.class, 100, (Supplier<ArrayList>) ArrayList::new).val();
    }

    @Test(expected = ClassCastException.class)
    public void testMapKeysSuppSupplierTreeMapNoComparable() {
        M.ints().mapKeys(TreeMap::new, 100, (Supplier<ArrayList>) ArrayList::new).val();
    }

    @Test
    public void testMapKeysSupplierNullKeyHashMap() {
        Integer[] ints = { null };
        Map<Integer, Integer> m = M.ints().range(0,5).mapKeys(HashMap.class, 10, M.from(ints).supplier()).val();

        Integer x = m.get(null);
        assertTrue(0 <= x && x < 5);
    }

    @Test
    public void testMapKeysSuppSupplierNullKeyHashMap() {
        Integer[] ints = { null };
        Map<Integer, Integer> m = M.ints()
                                   .range(0,5)
                                   .mapKeys(HashMap::new, 10, M.from(ints).supplier())
                                   .val();
        Integer x = m.get(null);
        assertTrue(0 <= x && x < 5);
    }

    @Test
    public void testMapKeysSupplier() {
        MockUnitInt ints = M.ints().range(0, 10);
        Class<? extends Map>[] maps =
                new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };
        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(10, 20)
                        .mapKeys(M.from(maps).val(), 10, ints.supplier())
                        .val(),
                map -> testMap(map, MapCheckUtils.inRange(0, 10), MapCheckUtils.inRange(10, 20))
        );
    }

    @Test
    public void testMapKeysSuppSupplier() {
        MockUnitInt ints = M.ints().range(0, 10);
        Supplier<Map<Integer, Integer>>[] maps =
                new Supplier[]{
                        TreeMap::new,
                        HashMap::new,
                        LinkedHashMap::new
                };

        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(10, 20)
                        .mapKeys(M.from(maps).val(), 10, ints.supplier())
                        .val(),
                map -> testMap(map, MapCheckUtils.inRange(0, 10), MapCheckUtils.inRange(10, 20))
        );
    }


    @Test
    public void testMapKeysSupplierOverloaded() {
        MockUnitString strings = M.strings().size(5);
        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                      .range(0, 100)
                      .mapKeys(10, strings.supplier())
                      .val(),
                map -> testMap(
                        map,
                        (k) -> {
                            if ((k.length()!=5))
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

    @Test
    public void testMapKeysSuppSupplierOverloaded() {
        MockUnitString strings = M.strings().size(5);
        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapKeys(HashMap::new, 10, strings.supplier())
                        .val(),
                map -> testMap(
                        map,
                        (k) -> {
                            if ((k.length()!=5))
                                fail();
                        },
                        MapCheckUtils.inRange(100, 200),
                        m -> {
                            if (!(m instanceof HashMap))
                                fail();
                        }
                )
        );
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysSupplierMockUnitIntSizeNull() {
        MockUnitInt sizeUnit = null;
        M.ints().range(0, 10).mapKeys(sizeUnit, M.ints().range(0, 10)::val);
    }

    @Test
    public void testMapKeysSupplierMockUnitIntSize() {
        final MockUnitInt size = M.ints().range(0, 10);
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.ints()
                      .range(100, 200)
                      .mapKeys(size, m.intSeq().supplier())
                      .val(),
                rMap -> {
                   testMap(rMap, MapCheckUtils.inRange(0, 10), MapCheckUtils.inRange(100, 200));
                   Assert.assertTrue(rMap.size() < 10);
                }
        );
    }


    /*----------------------------------
    * test map method with supplier, unit, supplier
    * <R> MockUnit<Map<R, T>> mapKeys(Supplier<Map<R, T>> mapSupplier, MockUnitInt sizeUnit, Supplier<R> keySupplier)
    ----------------------------------*/


    /*----------------------------------
     * test map method with iterable
     * default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, Iterable<R> keys)
     ----------------------------------*/

    @Test(expected = NullPointerException.class)
    public void testMapKeysIterableNullClass() {
        Class<Map> cls = null;
        M.ints().mapKeys(cls, M.ints().list(10).val());
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysSuppIterableNullClass() {
        Supplier<Map<Integer, Integer>> mapSupplier = null;
        M.ints().mapKeys(mapSupplier, M.ints().list(10).val());
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysIterableNullIterable() {
        List<String> nullList = null;
        M.ints().mapKeys(HashMap.class, nullList).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysSuppIterableNullIterable() {
        List<String> nullList = null;
        M.ints().mapKeys(HashMap::new, nullList).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysIterableCannotInstantiateMap() {
        M.ints().mapKeys(AbstractMapNoInstance.class, M.ints().list(10).val()).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysIterableTreeMapNoComparable() {
        List<ArrayList> list = ((MockUnit<ArrayList>)() -> ArrayList::new).list(10).val();
        M.ints().mapKeys(TreeMap.class, list).val();
    }

    @Test
    public void testMapKeysIterableNullKeyHashMap() {
        Integer[] ints = { null };
        Map<Integer, Integer> m = M.ints().range(0,5).mapKeys(HashMap.class, M.from(ints).list(10).val()).val();

        Integer x = m.get(null);
        assertTrue(0 <= x && x < 5);
    }

    @Test
    public void testMapKeysSuppIterableNullKeyHashMap() {
        Integer[] ints = { null };
        Map<Integer, Integer> m = M.ints().range(0,5).mapKeys(HashMap::new, M.from(ints).list(10).val()).val();

        Integer x = m.get(null);
        assertTrue(0 <= x && x < 5);
    }

    @Test
    public void testMapKeysIterable() {
        MockUnitInt ints = M.ints().range(50, 100);
        Class<? extends Map>[] maps = new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };
        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(1, 5)
                        .mapKeys(M.from(maps).val(), ints.list(10).val())
                        .val(),
                map -> testMap(
                        map,
                        MapCheckUtils.inRange(50, 100),
                        MapCheckUtils.inRange(1, 5)
                )
        );
    }

    @Test
    public void testMapKeysSuppIterable() {
        MockUnitInt ints = M.ints().range(50, 100);
        Supplier<Map<Integer, Integer>>[] maps = new Supplier[]{
                TreeMap::new,
                HashMap::new,
                LinkedHashMap::new
        };
        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(1, 5)
                        .mapKeys(M.from(maps).val(), ints.list(10).val())
                        .val(),
                map -> testMap(
                        map,
                        MapCheckUtils.inRange(50, 100),
                        MapCheckUtils.inRange(1, 5)
                )
        );
    }

    @Test
    public void testMapKeysIterableOverload() {
        MockUnitInt ints = M.ints().range(1, 5);
        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapKeys(ints.list(20).val())
                        .val(),
                map -> testMap(
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

    @Test
    public void testMapKeysSuppIterableOverloaded() {
        Supplier<Map<Integer, Integer>>[] maps = new Supplier[]{
                TreeMap::new,
                HashMap::new,
                LinkedHashMap::new
        };
        MockUnitInt ints = M.ints().range(1, 5);
        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapKeys(m.from(maps).val(), ints.list(20).val())
                        .val(),
                map -> testMap(
                        map,
                        MapCheckUtils.inRange(1, 5),
                        MapCheckUtils.inRange(100, 200),
                        m -> {
                            if (m == null)
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
    public void testMapKeysArrayNullClass() {
        Integer[] keys = {1,2,3};
        Class<Map> cls = null;
        M.ints().mapKeys(cls, keys).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayNullArray() {
        Integer[] keys = null;
        M.ints().mapKeys(HashMap.class, keys);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysArrayCannotInstantiateMap() {
        Integer[] keys = {1,2,3};
        M.ints().mapKeys(AbstractMapNoInstance.class, keys).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysArrayTreeMapNoComparable() {
        ArrayList[] lists = { new ArrayList(), new ArrayList(), new ArrayList() };
        M.ints().mapKeys(TreeMap.class, lists).val();
    }

    @Test
    public void testMapKeysArrayNullKeyHashMap() {
        Integer[] ints = { null };
        Map<Integer, Integer> map = M.ints().mapKeys(HashMap.class, ints).val();

        assertTrue(map.get(null) instanceof Integer);
    }

    @Test
    public void testMapKeysArray() {
        Integer[] ints = M.ints().range(0, 10).array(100).val();
        Class<? extends Map>[] maps = new Class[]{LinkedHashMap.class, HashMap.class, TreeMap.class};
        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints().range(10, 20).mapKeys(M.from(maps).val(), ints).val(),
                map -> testMap(map, MapCheckUtils.inRange(0, 10), MapCheckUtils.inRange(10, 20))
        );
    }

    @Test
    public void testMapKeysArrayOverloaded() {
        Integer[] ints = M.ints().range(0, 10).array(100).val();
        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints().range(10, 20).mapKeys(ints).val(),
                map -> testMap(map, MapCheckUtils.inRange(0, 10), MapCheckUtils.inRange(10, 20), m -> {
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
    public void testMapKeysArrayIntNullClass() {
        int[] keys = {1,2,3};
        Class<Map> cls = null;
        M.ints().mapKeys(cls, keys).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayIntNullArray() {
        int[] keys = null;
        M.ints().mapKeys(HashMap.class, keys);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysArrayIntCannotInstantiateMap() {
        int[] keys = {1,2,3};
        M.ints().mapKeys(AbstractMapNoInstance.class, keys).val();
    }


    @Test
    public void testMapKeysArrayInt() {
        int[] ints = M.ints().range(0, 10).arrayPrimitive(100).val();
        Class<? extends Map>[] maps = new Class[]{LinkedHashMap.class, HashMap.class, TreeMap.class};
        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints().range(10, 20).mapKeys(M.from(maps).val(), ints).val(),
                map -> testMap(map, MapCheckUtils.inRange(0, 10), MapCheckUtils.inRange(10, 20))
        );
    }

    @Test
    public void testMapKeysArrayIntOverloaded() {
        int[] ints = M.ints().range(0, 10).arrayPrimitive(100).val();
        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.ints().range(10, 20).mapKeys(ints).val(),
                map -> testMap(map, MapCheckUtils.inRange(0, 10), MapCheckUtils.inRange(10, 20), m -> {
                    if (!(m instanceof HashMap)) fail();
                })
        );
    }

    /*----------------------------------
     * test map method with array long[]
     default MockUnit<Map<Integer, T>> mapKeys(Class<? extends Map> mapClass, long[] keys)
     ----------------------------------*/

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayLongNullClass() {
        Class<Map> cls = null;
        long[] keys = {1,2,3};
        M.ints().mapKeys(cls, keys).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayLongNullArray() {
        long[] keys = null;
        M.ints().mapKeys(HashMap.class, keys);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysArrayLongCannotInstantiateMap() {
        long[] keys = {1,2,3};
        M.ints().mapKeys(AbstractMapNoInstance.class, keys).val();
    }


    @Test
    public void testMapKeysArrayLong() {
        long[] longs = M.longs().range(0L, 10L).arrayPrimitive(100).val();
        Class<? extends Map>[] maps = new Class[]{LinkedHashMap.class, HashMap.class, TreeMap.class};
        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.longs().range(10, 20).mapKeys(M.from(maps).val(), longs).val(),
                map -> testMap(map, MapCheckUtils.inRange(0L, 10L), MapCheckUtils.inRange(10L, 20L))
        );
    }

    @Test
    public void testMapKeysArrayLongOverloaded() {
        long[] longs = M.longs().range(0L, 10L).arrayPrimitive(100).val();
        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.longs().range(10L, 20L).mapKeys(longs).val(),
                map -> testMap(map, MapCheckUtils.inRange(0L, 10L), MapCheckUtils.inRange(10L, 20L), m -> {
                    if (!(m instanceof HashMap)) fail();
                })
        );
    }

    /*----------------------------------
     * test map method with array double[]
     default MockUnit<Map<Integer, T>> mapKeys(Class<? extends Map> mapClass, double[] keys)
     ----------------------------------*/

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayDoubleNullClass() {
        Class<Map> cls = null;
        double[] keys = {1.0, 2.0, 3.0};
        M.ints().mapKeys(cls, keys).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayDoubleNullArray() {
        double[] keys = null;
        M.ints().mapKeys(HashMap.class, keys);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysArrayDoubleCannotInstantiateMap() {
        double[] keys = {1.0, 2.0, 3.0};
        M.ints().mapKeys(AbstractMapNoInstance.class, keys).val();
    }


    @Test
    public void testMapKeysArrayDouble() {
        double[] doubles = M.doubles().range(0.0, 10.0).arrayPrimitive(100).val();
        Class<? extends Map>[] maps = new Class[]{LinkedHashMap.class, HashMap.class, TreeMap.class};
        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.doubles().range(10.0, 20.0).mapKeys(M.from(maps).val(), doubles).val(),
                map -> testMap(map, MapCheckUtils.inRange(0.0, 10.0), MapCheckUtils.inRange(10.0, 20.0))
        );
    }

    @Test
    public void testMapKeysArrayDoubleOverloaded() {
        double[] doubles = M.doubles().range(0.0, 10.0).arrayPrimitive(100).val();
        loop(
                MOCK_CYCLES,
                Constants.MOCKS,
                m -> m.doubles().range(10.0, 20.0).mapKeys(doubles).val(),
                map -> testMap(map, MapCheckUtils.inRange(0.0, 10.0), MapCheckUtils.inRange(10.0, 20.0), m -> {
                    if (!(m instanceof HashMap)) fail();
                })
        );
    }


}
