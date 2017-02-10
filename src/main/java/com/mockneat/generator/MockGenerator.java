package com.mockneat.generator;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


import com.mockneat.generator.units.SupplierUnit;
import com.mockneat.generator.units.ConstValueUnit;
import com.mockneat.generator.units.GeneratorUnit;
import com.mockneat.generator.units.ReferenceUnit;
import com.mockneat.types.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Statement;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.mockneat.utils.BeanUtils.getSetterName;

public class MockGenerator<T1> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MockGenerator.class);

    private Class<?> cls;

    private Map<String, GeneratorUnit> fields = new LinkedHashMap<>();

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
                new Statement(result, getSetterName(k), new Object[]{ value }).execute();
            } catch (Exception e) {
                String valueClass = (value!=null) ? value.getClass().getSimpleName() : "null";
                LOGGER.error("Cannot call method \"{}({})\" on bean {}.",
                        getSetterName(k), valueClass, result.getClass().getName(), e);
            }
        });
    }

    public Optional<T1> newInstance() {
        try {
            final Object result = cls.newInstance();
            setValues(result);
            return Optional.of((T1) result);
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("Cannot instantiate object of type '{}'. There is 'NO ARGUMENTS' public constructor not available.", cls.getName(), e);
        }
        return Optional.empty();
    }

    public Optional<T1> newInstace(Function<Optional<T1>, Optional<T1>> function) {
        return function.apply(newInstance());
    }
}
