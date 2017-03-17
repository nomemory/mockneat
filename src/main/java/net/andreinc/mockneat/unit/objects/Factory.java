package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.interfaces.MockUnit;
import net.andreinc.mockneat.utils.MockUnitUtils;
import net.andreinc.mockneat.utils.ValidationUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.util.regex.Pattern.compile;
import static java.util.stream.IntStream.range;
import static org.apache.commons.lang3.Validate.*;
import static org.apache.commons.lang3.Validate.notEmpty;
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
        notNull(targetClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "targetClass");
        notNull(factoryClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "factoryClass");
        notNull(params, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "params");
        notEmpty(method, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "method");
        isTrue(JAVA_FIELD_REGEX.matcher(method).matches(), ValidationUtils.JAVA_METHOD_REGEX_MATCH, method);
        final Object[] args = new Object[params.length];
        return () -> {
            T result = null;
            try {
                range(0, params.length).forEach(i -> args[i] = MockUnitUtils.mockOrObject(params[i]));
                result = (T) invokeExactStaticMethod(factoryClass, method, args);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                String fmt = String.format(ValidationUtils.CANNOT_INVOKE_STATIC_FACTORY_METHOD,
                        targetClass.getClass().getName(),
                        method,
                        MockUnitUtils.listTypes(args));
                throw new IllegalArgumentException(fmt, e);
            }
            return result;
        };
    }
}
