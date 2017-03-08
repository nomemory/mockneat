package com.mockneat.mock.unit.objects;

import com.mockneat.mock.interfaces.MockUnit;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import static com.mockneat.mock.utils.MockUnitUtils.listTypes;
import static com.mockneat.mock.utils.MockUnitUtils.mockOrObject;
import static com.mockneat.mock.utils.ValidationUtils.CANNOT_INFER_CONSTRUCTOR;
import static com.mockneat.mock.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static java.lang.String.format;
import static java.util.stream.IntStream.range;
import static org.apache.commons.lang3.Validate.notNull;
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
        notNull(cls, INPUT_PARAMETER_NOT_NULL, "cls");
        notNull(params, INPUT_PARAMETER_NOT_NULL, "params");
        final Object[] args = new Object[params.length];
        return () -> {
            range(0, params.length).forEach(i -> args[i] = mockOrObject(params[i]));
            try {
                return (T) invokeConstructor(cls, args);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                String fmt = format(CANNOT_INFER_CONSTRUCTOR, cls.getName(), listTypes(params));
                throw new IllegalArgumentException(fmt, e);
            }
        };
    }
}
