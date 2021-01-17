package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.utils.MockUnitUtils;
import net.andreinc.mockneat.utils.ValidationUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import static java.util.stream.IntStream.range;
import static net.andreinc.aleph.AlephFormatter.str;
import static net.andreinc.mockneat.utils.MockUnitUtils.listTypes;
import static net.andreinc.mockneat.utils.ValidationUtils.CANNOT_INFER_CONSTRUCTOR;
import static org.apache.commons.lang3.reflect.ConstructorUtils.invokeConstructor;

public class Constructor<T> implements MockUnit<T> {

    private final Class<T> cls;
    private Object[] params = new Object[0];

    /**
     * <p>Returns a new {@code Constructor} object.</p>
     *
     * <p>This method can be used to generate {@code MockUnit<T>} from a Java Bean {@code <T>} by accessing it's constructor and supply it with arbitrary input.</p>
     *
     * @param cls The class for type {@code <T>}. (Eg.: Student.class)
     * @param <T> The type of {@code MockUnit<T>}. This is the wrapped type.
     * @return A new {@code Constructor} object. The {@code Constructor} class implements {@code MockUnit<T>}.
     */
    public static <T> Constructor<T> constructor(Class<T> cls) {
        return MockNeat.threadLocal().constructor(cls);
    }

    public Constructor(Class<T> cls) {
        this.cls = cls;
    }

    /**
     * <p>Specifies the values which are supplied to the constructor.</p>
     *
     * <p>As a param you can supply a {@code MockUnit<T>} or a constant value. If you chose to supply {@code MockUnit}s the {@code val()} method will be automatically invoked.</p>
     *
     * @param params A var-arg array of constant values or {@code MockUnit}s.
     * @return The current {@code Constructor} instance.
     */
    public Constructor<T> params(Object... params) {
        this.params = params;
        return this;
    }

    @Override
    public Supplier<T> supplier() {
        ValidationUtils.notNull(cls, "cls");
        ValidationUtils.notNull(params, "params");
        final Object[] args = new Object[params.length];
        return () -> {
            range(0, params.length).forEach(i -> args[i] = MockUnitUtils.mockOrObject(params[i]));
            try {
                return (T) invokeConstructor(cls, args);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                String fmt = str(CANNOT_INFER_CONSTRUCTOR)
                                .args("c", cls)
                                .args("params", listTypes(params))
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        };
    }
}
