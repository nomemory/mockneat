package net.andreinc.mockneat.unit.objects;

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

//TODO document this
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
