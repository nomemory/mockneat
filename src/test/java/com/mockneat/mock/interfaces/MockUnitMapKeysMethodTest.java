package com.mockneat.mock.interfaces;

import com.mockneat.mock.interfaces.models.AbstractMapNoInstance;
import junit.framework.Assert;
import org.junit.Test;

import java.util.*;

import static com.mockneat.mock.Constants.*;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by andreinicolinciobanu on 16/02/2017.
 */
public class MockUnitMapKeysMethodTest {

    /*----------------------------------
     * test map method with supplier
     ----------------------------------*/

    @Test(expected = NullPointerException.class)
    public void testMapKeysSupplierNullClass() throws Exception {
        M.ints().mapKeys(null, 10, () -> "abc").val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysSupplierNegativeSize() throws Exception {
        M.ints().mapKeys(HashMap.class, -1, () -> "abc").val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysSupplierNullSupplier() {
        M.ints().mapKeys(HashMap.class, 10, null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysSupplierCannotInstantiateMap() throws Exception {
        M.ints().mapKeys(AbstractMapNoInstance.class, 10, M.ints().supplier()).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysSupplierTreeMapNoComparable() throws Exception {
        M.ints().mapKeys(TreeMap.class, 100, () -> new ArrayList()).val();
    }

    @Test
    public void testMapKeysSupplierNullKeyHashMap() throws Exception {
        Integer[] ints = { null };
        Map<Integer, Integer> m = M.ints().range(0,5).mapKeys(HashMap.class, 10, M.from(ints).supplier()).val();

        Integer x = m.get(null);
        Assert.assertTrue(0 <= x && x < 5);
    }

    @Test
    public void testMapKeysSupplier() throws Exception {
        MockUnitInt ints = M.ints().range(0, 10);
        Class<? extends Map>[] maps =
                new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.ints()
                        .range(10, 20)
                        .mapKeys(M.from(maps).val(), 10, ints.supplier())
                        .val(),
                map -> {
                    map.forEach((k,v) -> {
                        if (!(0<=k && k<10))
                            fail();
                        if (!(10<=v && v<20))
                            fail();
                        if (!((map instanceof TreeMap) ||
                              (map instanceof HashMap) ||
                              (map instanceof LinkedHashMap)))
                            fail();
                    });
                }
        );
    }

    @Test
    public void testMapKeysSupplierOverloaded() throws Exception {
        MockUnitString strings = M.strings().size(5);
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.ints()
                      .range(0, 100)
                      .mapKeys(10, strings.supplier())
                      .val(),
                map -> {
                    map.forEach((k,v) -> {
                        if (!(k.length()==5))
                            fail();
                        if (!(0<=v && v<100))
                            fail();
                        if (!(map instanceof HashMap))
                            fail();
                    });
                }
        );
    }

    /*----------------------------------
     * test map method with iterable
     * default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, Iterable<R> keys)
     ----------------------------------*/

    @Test(expected = NullPointerException.class)
    public void testMapKeysIterableNullClass() throws Exception {
        M.ints().mapKeys(null, M.ints().list(10).val());
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysIterableNullIterable() throws Exception {
        List<String> nullList = null;
        M.ints().mapKeys(HashMap.class, nullList).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysIterableCannotInstantiateMap() throws Exception {
        M.ints().mapKeys(AbstractMapNoInstance.class, M.ints().list(10).val()).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysIterableTreeMapNoComparable() throws Exception {
        List<ArrayList> list = ((MockUnit<ArrayList>)() -> () -> new ArrayList()).list(10).val();
        M.ints().mapKeys(TreeMap.class, list).val();
    }

    @Test
    public void testMapKeysIterableNullKeyHashMap() throws Exception {
        Integer[] ints = { null };
        Map<Integer, Integer> m = M.ints().range(0,5).mapKeys(HashMap.class, M.from(ints).list(10).val()).val();

        Integer x = m.get(null);
        Assert.assertTrue(0 <= x && x < 5);
    }

    @Test
    public void testMapKeysIterable() throws Exception {
        MockUnitInt ints = M.ints().range(50, 100);
        Class<? extends Map>[] maps =
                new Class[]{ TreeMap.class, HashMap.class, LinkedHashMap.class };
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.ints()
                        .range(1, 5)
                        .mapKeys(M.from(maps).val(), ints.list(10).val())
                        .val(),
                map -> {
                    map.forEach((k,v) -> {
                        if (!(50<=k && k<100))
                            fail();
                        if (!(1<=v && v<5))
                            fail();
                        if (!((map instanceof TreeMap) ||
                                (map instanceof HashMap) ||
                                (map instanceof LinkedHashMap)))
                            fail();
                    });
                }
        );
    }

    @Test
    public void testMapKeysIterableOverloade() throws Exception {
        MockUnitInt ints = M.ints().range(1, 5);
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.ints()
                        .range(100, 200)
                        .mapKeys(ints.list(20).val())
                        .val(),
                map -> {
                    map.forEach((k,v) -> {
                        if (!(1<=k && k<5))
                            fail();
                        if (!(100<=v && v<200))
                            fail();
                        if (!(map instanceof HashMap))
                            fail();
                    });
                }
        );
    }

    /*----------------------------------
     * test map method with array
     * default <R> MockUnit<Map<R, T>> mapKeys(Class<? extends Map> mapClass, R[] keys)
     ----------------------------------*/

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayNullClass() throws Exception {
        Integer[] keys = {1,2,3};
        M.ints().mapKeys(null, keys).val();
    }

    @Test(expected = NullPointerException.class)
    public void testMapKeysArrayNullArray() throws Exception {
        Integer[] keys = null;
        M.ints().mapKeys(HashMap.class, keys);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysArrayCannotInstantiateMap() throws Exception {
        Integer[] keys = {1,2,3};
        M.ints().mapKeys(AbstractMapNoInstance.class, keys).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapKeysArrayTreeMapNoComparable() throws Exception {
        ArrayList[] lists = { new ArrayList(), new ArrayList(), new ArrayList() };
        M.ints().mapKeys(TreeMap.class, lists).val();
    }

    @Test
    public void testMapKeysArrayNullKeyHashMap() throws Exception {
        Integer[] ints = { null };
        Map<Integer, Integer> map = M.ints().mapKeys(HashMap.class, ints).val();

        assertTrue(map.get(null) instanceof Integer);
    }

    @Test
    public void testMapKeysArray() throws Exception {
        Integer[] ints = M.ints().range(0, 10).array(100).val();
        Class<? extends Map>[] maps = new Class[]{LinkedHashMap.class, HashMap.class, TreeMap.class};
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.ints().range(10, 20).mapKeys(M.from(maps).val(), ints).val(),
                map -> {
                    map.forEach((k, v) -> {
                        if (!((map instanceof TreeMap) ||
                                (map instanceof HashMap) ||
                                (map instanceof LinkedHashMap)))
                            fail();
                        if (!(0<= k && k < 10))
                            fail();
                        if (!(10<= v && v < 20))
                            fail();
                    });
                }
        );
    }

    @Test
    public void testMapKeysArrayOverloaded() throws Exception {
        Integer[] ints = M.ints().range(0, 10).array(100).val();
        loop(
                MOCK_CYCLES,
                MOCKS,
                m -> m.ints().range(10, 20).mapKeys(ints).val(),
                map -> {
                    map.forEach((k, v) -> {
                        if (!(map instanceof HashMap))
                            fail();
                        if (!(0<= k && k < 10))
                            fail();
                        if (!(10<= v && v < 20))
                            fail();
                    });
                }
        );
    }
}
