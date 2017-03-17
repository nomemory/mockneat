package net.andreinc.mockneat.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by andreinicolinciobanu on 06/03/2017.
 */
public class MapCheckUtils {
    public static <K,V> void testMap(Map<K,V> map,
                                      Consumer<K> validateKeys,
                                      Consumer<V> validateVals,
                                      Consumer<Map<K,V>> validateMap) {
        assertTrue(map!=null);
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
            if (  !((map instanceof TreeMap) ||
                    (map instanceof HashMap) ||
                    (map instanceof LinkedHashMap)))
                fail();
        });
    }
    public static <T1 extends Comparable> Consumer<T1> inRange(T1 low, T1 high) {
        Consumer<T1> consumer =
                (T1 t) ->  { if (!(t.compareTo(low) >= 0 && t.compareTo(high) < 0)) fail(); };
        return consumer;
    }

}
