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

import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.utils.MockUnitUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;
import static java.util.stream.IntStream.range;
import static net.andreinc.aleph.AlephFormatter.template;
import static net.andreinc.mockneat.utils.MockUnitUtils.listTypes;
import static net.andreinc.mockneat.utils.ValidationUtils.*;
import static org.apache.commons.lang3.reflect.MethodUtils.invokeExactStaticMethod;

public class Factory<T, FT> implements MockUnit<T> {

    private static final Pattern JAVA_FIELD_REGEX =
            compile("^[a-zA-Z_$][a-zA-Z_$0-9]*$");

    private final Class<T> targetClass;
    private final Class<FT> factoryClass;
    private String method;
    private Object[] params;

    public Factory(Class<T> targetClass, Class<FT> factoryClass) {
        this.targetClass = targetClass;
        this.factoryClass = factoryClass;
    }

    public Factory<T, FT> method(String methodName) {
        this.method = methodName;
        return this;
    }

    public Factory<T, FT> params(Object... params) {
        this.params = params;
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Supplier<T> supplier() {
        notNull(targetClass,  "targetClass");
        notNull(factoryClass,  "factoryClass");
        notNull(params, "params");
        notEmpty(method,  "method");
        isTrue(JAVA_FIELD_REGEX.matcher(method).matches(), template(JAVA_METHOD_REGEX_MATCH, "method", method).fmt());
        final Object[] args = new Object[params.length];
        return () -> {
            T result;
            try {
                range(0, params.length).forEach(i -> args[i] = MockUnitUtils.mockOrObject(params[i]));
                result = (T) invokeExactStaticMethod(factoryClass, method, args);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                String fmt = template(CANNOT_INVOKE_STATIC_FACTORY_METHOD)
                                .arg("cls", targetClass)
                                .arg("method", method)
                                .arg("types", listTypes(args))
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
            return result;
        };
    }
}
