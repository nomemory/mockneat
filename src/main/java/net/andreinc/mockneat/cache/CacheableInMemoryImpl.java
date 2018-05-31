package net.andreinc.mockneat.cache;

/**
 * Copyright 2018, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.abstraction.MockUnit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class CacheableInMemoryImpl implements Cacheable {

    private Map<Class, Map<String, Object>> map = new ConcurrentHashMap<>();

    protected CacheableInMemoryImpl() {}

    @Override
    public <T> T get(String key, Class<T> type) {
        notNull(key, "key");
        notNull(type, "type");
        if (!map.containsKey(type) || !map.get(type).containsKey(key)) {
            invalidKeyAssociationException(key, type);
        }
        return type.cast(map.get(type).get(key));
    }

    @Override
    public <T> void put(String key, Class<T> type, MockUnit<T> mockUnit) {
        notNull(key, "key");
        notNull(type, "type");
        notNull(mockUnit, "mockUnit");
        map.putIfAbsent(type, new ConcurrentHashMap<>());
        map.get(type).put(key, mockUnit.val());
    }

}
