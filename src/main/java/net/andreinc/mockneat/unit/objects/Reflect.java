package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.interfaces.MockConstValue;
import net.andreinc.mockneat.interfaces.MockUnit;
import net.andreinc.mockneat.interfaces.MockValue;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static java.lang.reflect.Modifier.FINAL;
import static java.util.regex.Pattern.compile;
import static net.andreinc.aleph.AlephFormatter.template;
import static net.andreinc.mockneat.interfaces.MockUnitValue.unit;
import static net.andreinc.mockneat.utils.ValidationUtils.*;
import static org.apache.commons.lang3.reflect.FieldUtils.getDeclaredField;
import static org.apache.commons.lang3.reflect.FieldUtils.writeField;

//TODO document this
public class Reflect<T> implements MockUnit<T> {

    private static final Pattern JAVA_FIELD_REGEX =
            compile("^[a-zA-Z_$][a-zA-Z_$0-9]*$");

    private final Map<String, MockValue> fields = new LinkedHashMap<>();
    private final Map<Class<?>, MockValue> defaults = new HashMap<>();
    private final Class<T> cls;

    public Reflect(Class<T> cls) {
        this.cls = cls;
    }

    @Override
    public Supplier<T> supplier() {
        notNull(cls, "cls");
        validateFields();
        return () -> {
            T instance = instance();
            setValues(instance);
            return instance;
        };
    }

    public <T1> Reflect<T> field(String fieldName, MockUnit<T1> mockUnit) {
        notEmpty(fieldName, "fieldName");
        notNull(mockUnit, "mockUnit");
        this.fields.put(fieldName, unit(mockUnit));
        return this;
    }

    public Reflect<T> field(String fieldName, Object value) {
        notEmpty(fieldName, "fieldName");
        this.fields.put(fieldName, MockConstValue.constant(value));
        return this;
    }

    public void validateFields() {
        notNull(fields, INPUT_PARAMETER_NOT_NULL, "fields");
        fields.forEach((k, v) -> {
            notEmpty(k, INPUT_PARAMETER_NOT_EMPTY_OR_NULL, "fieldName");
            isTrue(JAVA_FIELD_REGEX.matcher(k).matches(), JAVA_FIELD_REGEX_MATCH, "field", k);
            Field field = getDeclaredField(cls, k, true);
            if (field==null) {
                String fmt = template(JAVA_FIELD_DOESNT_EXIST_ON_CLASS, "field", k).fmt();
                throw new IllegalArgumentException(fmt);
            }
            boolean isFinal = (field.getModifiers() & FINAL) == FINAL;
            isTrue(!isFinal, JAVA_FIELD_IS_FINAL, "field", k);
        });
    }

    private T instance() {
        try {
            return cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            String fmt = template(CANNOT_INSTANTIATE_OBJECT_OF_CLASS)
                            .arg("cls", cls)
                            .fmt();
            throw new IllegalArgumentException(fmt, e);
        }
    }

    private void setValues(T object) {
        fields.forEach((key, val) -> {
            Object cVal = val.get();
            try {
                writeField(object, key, cVal, true);
            } catch (IllegalAccessException e) {
                String fmt = template(CANNOT_SET_FIELD_WITH_VALUE)
                                .arg("cls", cls)
                                .arg("field", key)
                                .arg("val", cVal)
                                .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        });
    }

}
