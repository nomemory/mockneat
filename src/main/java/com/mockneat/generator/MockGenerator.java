package com.mockneat.generator;

import com.mockneat.generator.units.SupplierUnit;
import com.mockneat.generator.units.ConstValueUnit;
import com.mockneat.generator.units.MockGeneratorUnit;
import com.mockneat.generator.units.ReferenceUnit;
import com.mockneat.types.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Statement;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static com.mockneat.utils.BeanUtils.getSetterName;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class MockGenerator {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MockGenerator.class);

    private Class<?> cls;

    private Map<String, MockGeneratorUnit> fields = new LinkedHashMap<>();

    protected MockGenerator(Class<?> cls) {
        this.cls = cls;
    }

    public static MockGenerator forClass(Class<?> cls) {
        return new MockGenerator(cls);
    }

    public MockGenerator field(String name, Value value) {
        fields.put(name, ConstValueUnit.from(value));
        return this;
    }

    public <T> MockGenerator field(String name, Supplier<T> supplier) {
        fields.put(name, SupplierUnit.from(supplier));
        return this;
    }

    public MockGenerator field(String name, MockGenerator generator) {
        fields.put(name, ReferenceUnit.from(generator));
        return this;
    }

    protected void setValues(Object result) {
        fields.forEach((k,v) -> {
            Object value = v.next();
            try {
                new Statement(result, getSetterName(k.toString()), new Object[]{ value }).execute();
            } catch (Exception e) {
                String valueClass = (value!=null) ? value.getClass().getSimpleName() : "null";
                LOGGER.error("Cannot call method \"{}({})\" on bean {}.",
                        getSetterName(k.toString()), valueClass, result.getClass().getName(), e);
            }
        });
    }

    public Optional<Object> newInstance() {
        try {
            final Object result = cls.newInstance();
            setValues(result);
            return Optional.of(result);
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("Cannot instantiate object of type '{}'. There is 'NO ARGUMENTS' public constructor not available.", cls.getName());
        }
        return Optional.empty();
    }
}
