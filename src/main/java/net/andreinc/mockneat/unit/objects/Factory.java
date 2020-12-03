package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.utils.MockUnitUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;
import static java.util.stream.IntStream.range;
import static net.andreinc.aleph.AlephFormatter.str;
import static net.andreinc.mockneat.utils.MockUnitUtils.listTypes;
import static net.andreinc.mockneat.utils.ValidationUtils.*;
import static org.apache.commons.lang3.reflect.MethodUtils.invokeExactStaticMethod;

public class Factory<T, R> implements MockUnit<T> {

    private static final Pattern JAVA_FIELD_REGEX =
            compile("^[a-zA-Z_$][a-zA-Z_$0-9]*$");

    private final Class<T> targetClass;
    private final Class<R> factoryClass;
    private String method;
    private Object[] params = new Object[]{};

    public static <T, FT> Factory<T, FT> factory(Class<T> targetClass, Class<FT> factoryClass) {
        return MockNeat.threadLocal().factory(targetClass, factoryClass);
    }

    public Factory(Class<T> targetClass, Class<R> factoryClass) {
        this.targetClass = targetClass;
        this.factoryClass = factoryClass;
    }

    /**
     * <p>Specifies the static method name.</p>
     *
     * @param methodName The method factory method used to instantiate the class.
     * @return The same instance of the previously created {@code Factory} object. The {@code Factory} class implements the {@code MockUnit<T>} interface.
     */
    public Factory<T, R> method(String methodName) {
        this.method = methodName;
        return this;
    }

    /**
     * <p>Specified the params that are going to be supplied to the static factory method.</p>
     *
     * <p><em>Note:</em> If a {@code MockUnit} is supplied as a parameter the {@code val()} method is invoked instead of using the actual value.</p>
     *
     * @param params A var-arg array being the list of {@code params} supplied to the factory method.
     * @return The same instance of the {@code Factory} object. THe {@code Factory} class implements the {@code MockUnit<T>} interface.
     */
    public Factory<T, R> params(Object... params) {
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
        isTrue(JAVA_FIELD_REGEX.matcher(method).matches(), str(JAVA_METHOD_REGEX_MATCH).args("method", method).fmt());
        final Object[] args = new Object[params.length];
        return () -> {
            T result;
            try {
                range(0, params.length).forEach(i -> args[i] = MockUnitUtils.mockOrObject(params[i]));
                result = (T) invokeExactStaticMethod(factoryClass, method, args);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                String fmt = str(CANNOT_INVOKE_STATIC_FACTORY_METHOD)
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
