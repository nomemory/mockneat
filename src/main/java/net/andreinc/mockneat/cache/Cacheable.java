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

import static net.andreinc.aleph.AlephFormatter.str;
import static net.andreinc.mockneat.utils.ValidationUtils.CACHE_INVALID_KEY_TYPE_ASSOCIATION;

public interface Cacheable {
    <T> T get(String key, Class<T> type);

    <T> void put(String key, Class<T> type, MockUnit<T> mockUnit);

    default <T> void invalidKeyAssociationException(String key, Class<T> type) {
        String exMsg = str(CACHE_INVALID_KEY_TYPE_ASSOCIATION)
                .arg("key", key)
                .arg("type", type)
                .fmt();
        throw new IllegalArgumentException(exMsg);
    }
}
