package com.mockneat.random.unit;

import com.mockneat.random.unit.interfaces.RandUnit;
import com.mockneat.types.Pair;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import static com.mockneat.utils.ValidationUtils.INPUT_COMPOSE_TYPE_NOT_NULL;
import static com.mockneat.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static java.util.Arrays.stream;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by andreinicolinciobanu on 08/02/2017.
 */
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
