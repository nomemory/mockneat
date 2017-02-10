package com.mockneat.random.unit.objects;

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
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


import com.mockneat.random.interfaces.RandUnit;
import com.mockneat.types.Pair;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;
import static com.mockneat.utils.ValidationUtils.INPUT_COMPOSE_TYPE_NOT_NULL;
import static com.mockneat.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.Validate.notNull;

public class Compose {

    private Pair<Supplier, Class>[] pairs;

    public Compose(Pair<Supplier, Class>... pairs) {
        notNull(pairs, INPUT_PARAMETER_NOT_NULL, "pairs");
        stream(pairs)
                .forEach(p -> notNull(p.getSecond(), INPUT_COMPOSE_TYPE_NOT_NULL));
        this.pairs = pairs;
    }

    protected Class[] classes() {
        Class[] result = new Class[this.pairs.length];
        for(int i = 0 ; i < this.pairs.length; ++i) {
            result[i] = pairs[i].getSecond();
        }
        return result;
    }

    protected Object[] values() {
        Object[] result = new Object[this.pairs.length];
        Supplier supplier;
        for(int i = 0; i < this.pairs.length; ++i) {
            supplier = pairs[i].getFirst();
            result[i] = (null==supplier) ? null : supplier.get();
        }
        return result;
    }

    public <T> RandUnit<T> object(Class<T> cls) {
        notNull(cls, INPUT_PARAMETER_NOT_NULL, "cls");
        Supplier<T> supp = () -> {
            try {
                return cls.getDeclaredConstructor(classes()).newInstance(values());
            } catch (InstantiationException |
                     IllegalAccessException |
                     NoSuchMethodException  |
                     InvocationTargetException e) {
                throw new IllegalArgumentException("Cannot instantiate object: ", e);
            }
        };
        return () -> supp;
    }
}
