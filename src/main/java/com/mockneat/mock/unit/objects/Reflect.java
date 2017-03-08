package com.mockneat.mock.unit.objects;

import com.mockneat.mock.interfaces.MockConstValue;
import com.mockneat.mock.interfaces.MockUnit;
import com.mockneat.mock.interfaces.MockUnitValue;
import com.mockneat.mock.interfaces.MockValue;
import org.apache.commons.lang3.Validate;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static com.mockneat.mock.utils.ValidationUtils.*;
import static java.lang.String.format;
import static java.lang.reflect.Modifier.FINAL;
import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;
import static org.apache.commons.lang3.reflect.FieldUtils.getDeclaredField;
import static org.apache.commons.lang3.reflect.FieldUtils.writeField;

//TODO document this
public class Reflect<T> implements MockUnit<T> {

    private static final Pattern JAVA_FIELD_REGEX =
            compile("^[a-zA-Z_$][a-zA-Z_$0-9]*$");

    private final Map<String, MockValue> fields = new LinkedHashMap<>();
    private Class<T> cls;

    public Reflect(Class<T> cls) {
        this.cls = cls;
    }

    @Override
    public Supplier<T> supplier() {
        notNull(cls, INPUT_PARAMETER_NOT_NULL, "cls");
        validateFields();
        return () -> {
            T instance = instance();
            setValues(instance);
            return instance;
        };
    }

    public <T1> Reflect<T> field(String fieldName, MockUnit<T1> mockUnit) {
        notNull(mockUnit, INPUT_PARAMETER_NOT_NULL, "mockUnit");
        this.fields.put(fieldName, new MockUnitValue(mockUnit));
        return this;
    }

    public Reflect<T> field(String fieldName, Object value) {
        this.fields.put(fieldName, MockConstValue.val(value));
        return this;
    }

    public void validateFields() {
        notNull(fields, INPUT_PARAMETER_NOT_NULL, "fields");
        fields.forEach((k, v) -> {
            Validate.notEmpty(k, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "fieldName");
            isTrue(JAVA_FIELD_REGEX.matcher(k).matches(), JAVA_FIELD_REGEX_MATCH, k);
            Field field = getDeclaredField(cls, k, true);
            if (field==null) {
                String fmt = format(JAVA_FIELD_DOESNT_EXIST_ON_CLASS, k);
                throw new IllegalArgumentException(fmt);
            }
            boolean isFinal = (field.getModifiers() & FINAL) == FINAL;
            isTrue(!isFinal, JAVA_FIELD_IS_FINAL, k);
        });
    }

    private T instance() {
        try {
            return cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            String fmt = format(CANNOT_INSTANTIATE_OBJECT_OF_CLASS,
                    cls.getSimpleName(),
                    cls.getSimpleName());
            throw new IllegalArgumentException(fmt, e);
        }
    }

    private void setValues(T object) {
        fields.forEach((key, val) -> {
            Object cVal = val.get();
            try {
                writeField(object, key, cVal, true);
            } catch (IllegalAccessException e) {
                String fmt = format(CANNOT_SET_FIELD_WITH_VALUE,
                        cls.getSimpleName(),
                        key,
                        cVal);
                throw new IllegalArgumentException(fmt, e);
            }
        });
    }

}
