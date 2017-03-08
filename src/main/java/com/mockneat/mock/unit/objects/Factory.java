package com.mockneat.mock.unit.objects;

import com.mockneat.mock.interfaces.MockUnit;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static com.mockneat.mock.utils.MockUnitUtils.listTypes;
import static com.mockneat.mock.utils.MockUnitUtils.mockOrObject;
import static com.mockneat.mock.utils.ValidationUtils.*;
import static java.lang.String.format;
import static java.util.regex.Pattern.compile;
import static java.util.stream.IntStream.range;
import static org.apache.commons.lang3.Validate.*;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.reflect.MethodUtils.invokeExactStaticMethod;

//TODO document this
public class Factory<T, FT> implements MockUnit<T> {

    private static final Pattern JAVA_FIELD_REGEX =
            compile("^[a-zA-Z_$][a-zA-Z_$0-9]*$");

    private final Class<T> targetClass;
    private Class<FT> factoryClass;
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
    public Supplier<T> supplier() {
        notNull(targetClass, INPUT_PARAMETER_NOT_NULL, "targetClass");
        notNull(factoryClass, INPUT_PARAMETER_NOT_NULL, "factoryClass");
        notNull(params, INPUT_PARAMETER_NOT_NULL, "params");
        notEmpty(method, INPUT_PARAMETER_NOT_NULL, "method");
        isTrue(JAVA_FIELD_REGEX.matcher(method).matches(), JAVA_METHOD_REGEX_MATCH, method);
        final Object[] args = new Object[params.length];
        return () -> {
            T result = null;
            try {
                range(0, params.length).forEach(i -> args[i] = mockOrObject(params[i]));
                result = (T) invokeExactStaticMethod(factoryClass, method, args);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                String fmt = format(CANNOT_INVOKE_STATIC_FACTORY_METHOD,
                        targetClass.getClass().getName(),
                        method,
                        listTypes(args));
                throw new IllegalArgumentException(fmt, e);
            } catch (ClassCastException cce) {
                String fmt = format(CANNOT_INVOKE_STATIC_FACTORY_METHOD_RETURN,
                        targetClass.getClass().getName(),
                        method,
                        listTypes(args));
                throw new IllegalArgumentException(fmt, cce);
            }
            return result;
        };
    }
}
