package net.andreinc.mockneat.unit.objects;

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

import net.andreinc.mockneat.interfaces.MockUnit;
import net.andreinc.mockneat.utils.MockUnitUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import static java.util.stream.IntStream.range;
import static net.andreinc.aleph.AlephFormatter.template;
import static net.andreinc.mockneat.utils.MockUnitUtils.listTypes;
import static net.andreinc.mockneat.utils.ValidationUtils.CANNOT_INFER_CONSTRUCTOR;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;
import static org.apache.commons.lang3.reflect.ConstructorUtils.invokeConstructor;

public class Constructor<T> implements MockUnit<T> {

    private final Class<T> cls;
    private Object[] params;

    public Constructor(Class<T> cls) {
        this.cls = cls;
    }

    public Constructor<T> params(Object... params) {
        this.params = params;
        return this;
    }

    @Override
    public Supplier<T> supplier() {
        notNull(cls, "cls");
        notNull(params, "params");
        final Object[] args = new Object[params.length];
        return () -> {
            range(0, params.length).forEach(i -> args[i] = MockUnitUtils.mockOrObject(params[i]));
            try {
                return (T) invokeConstructor(cls, args);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                String fmt = template(CANNOT_INFER_CONSTRUCTOR)
                                .args("c", cls)
                                .args("params", listTypes(params))
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
    }
}
