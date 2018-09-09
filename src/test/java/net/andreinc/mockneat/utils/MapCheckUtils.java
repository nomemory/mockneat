package net.andreinc.mockneat.utils;

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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public final class MapCheckUtils {
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
