package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.interfaces.MockUnit;
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
            T result = null;
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
