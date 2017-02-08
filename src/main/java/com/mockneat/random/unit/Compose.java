package com.mockneat.random.unit;

import com.mockneat.random.unit.interfaces.RandUnit;
import com.mockneat.types.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import static com.mockneat.utils.CheckUtils.checkComposeObjectNotNull;
import static com.mockneat.utils.CheckUtils.checkComposePairs;

/**
 * Created by andreinicolinciobanu on 08/02/2017.
 */
public class Compose {

    private Logger logger = LoggerFactory.getLogger(Compose.class);

    private Pair<Supplier, Class>[] pairs;

    public Compose(Pair<Supplier, Class>... pairs) {
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
        checkComposeObjectNotNull(cls);
        checkComposePairs(pairs);
        Supplier<T> supp = () -> {
            try {
                return cls.getDeclaredConstructor(classes()).newInstance(values());
            } catch (InstantiationException |
                     IllegalAccessException |
                     NoSuchMethodException  |
                     InvocationTargetException e) {
                logger.error("Cannot instantiate object", e);
                throw new IllegalArgumentException("Cannot instantiate object: ", e);
            }
        };
        return () -> supp;
    }
}
