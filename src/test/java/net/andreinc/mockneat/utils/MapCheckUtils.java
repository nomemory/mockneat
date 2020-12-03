package net.andreinc.mockneat.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public final class MapCheckUtils {
    public static <K,V> void testMap(Map<K,V> map,
                                      Consumer<K> validateKeys,
                                      Consumer<V> validateVals,
                                      Consumer<Map<K,V>> validateMap) {
        assertNotNull(map);
        map.forEach((k,v) -> {
            validateKeys.accept(k);
            validateVals.accept(v);
            validateMap.accept(map);
        });
    }
    public static <K,V> void testMap(Map<K,V> map,
                                      Consumer<K> validateKeys,
                                      Consumer<V> validateVals) {
        testMap(map, validateKeys, validateVals, (m) -> {
            if (!(map instanceof TreeMap || map instanceof HashMap))
                fail();
        });
    }
    public static <T1 extends Comparable<T1>> Consumer<T1> inRange(T1 low, T1 high) {
        return (T1 t) ->  { if (!(t.compareTo(low) >= 0 && t.compareTo(high) < 0)) fail(); };
    }

}
