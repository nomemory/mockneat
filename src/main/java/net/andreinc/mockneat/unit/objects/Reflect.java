package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockValue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import static java.lang.reflect.Modifier.FINAL;
import static java.util.regex.Pattern.compile;
import static net.andreinc.aleph.AlephFormatter.str;
import static net.andreinc.mockneat.abstraction.MockConstValue.constant;
import static net.andreinc.mockneat.abstraction.MockUnitValue.unit;
import static net.andreinc.mockneat.utils.ValidationUtils.*;
import static org.apache.commons.lang3.reflect.FieldUtils.*;

public class Reflect<T> extends MockUnitBase implements MockUnit<T> {

    private static final Pattern JAVA_FIELD_REGEX =
            compile("^[a-zA-Z_$][a-zA-Z_$0-9]*$");

    private final Map<String, MockValue<?>> fields = new LinkedHashMap<>();

    private boolean useDefaults;
    private final Map<Class<?>, MockValue<?>> defaults = new HashMap<>();
    private final Class<T> cls;

    /**
     * <p>Returns a new {@code Reflect<T>} object that can be used to fill-up objects through reflection</p>
     *
     * <p>Check {@code filler(Supplier<T>)} as an alternative that doesn't use reflection. </p>
     *
     * @param cls The type of the class you want to construct
     * @param <T> The generic type of the class
     *
     * @return A re-usable {@code Reflect<T>} instance. The class implements {@code MockUnit<T>}
     */
    public static <T> Reflect<T> reflect(Class<T> cls) {
        return MockNeat.threadLocal().reflect(cls);
    }

    protected Reflect(Class<T> cls) {
        this(MockNeat.threadLocal(), cls);
    }

    public Reflect(MockNeat mockNeat, Class<T> cls) {
        super(mockNeat);
        this.cls = cls;
        initDefaults();
    }

    private void initDefaults() {
        type(boolean.class, mockNeat.bools());
        type(Boolean.class, mockNeat.bools());

        type(char.class, mockNeat.chars().letters());
        type(Character.class, mockNeat.chars().letters());

        type(short.class, mockNeat.ints().bound(100).map(Integer::shortValue));
        type(Short.class, mockNeat.ints().bound(100).map(Integer::shortValue));

        type(int.class, mockNeat.ints().bound(100));
        type(Integer.class, mockNeat.ints().bound(100));

        type(long.class, mockNeat.longs().bound(100L));
        type(Long.class, mockNeat.longs().bound(100L));

        type(double.class, mockNeat.doubles().bound(10));
        type(Double.class, mockNeat.doubles().bound(10));

        type(float.class, mockNeat.floats().bound(10));
        type(Float.class, mockNeat.floats().bound(10));

        type(String.class, mockNeat.strings().size(32));
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

    /**
     * <p>Associates the class field with a given {@code MockUnit<T>} when generating values.</p>
     *
     * <p>Field can be private by not final.</p>
     *
     * @param fieldName The field name (not the setter name!)
     * @param mockUnit The unit that generates values for the field
     * @param <T1> the type fo the mock unit used.
     *
     * @return The same {@code Reflect<T>} object.
     */
    public <T1> Reflect<T> field(String fieldName, MockUnit<T1> mockUnit) {
        notEmpty(fieldName, "fieldName");
        notNull(mockUnit, "mockUnit");
        this.fields.put(fieldName, unit(mockUnit));
        return this;
    }

    /**
     * <p>Associates the class field with a constant value.</p>
     *
     * <p>Field can be private by not final.</p>
     *
     * @param fieldName The field name (not the setter name!)
     * @param value The constant value the field will have
     *
     * @return The same {@code Reflect<T>} object
     */
    public Reflect<T> field(String fieldName, Object value) {
        notEmpty(fieldName, "fieldName");
        this.fields.put(fieldName, constant(value));
        return this;
    }

    /**
     * <p>If the field is not specified, you can enable the library to use default values for a limited range of common types:</p>
     *
     * <ul>
     *     <li>{@code Boolean}/{@code boolean} - the default value is either {@code true} or {@code false}</li>
     *     <li>{@code Character}/{@code char} - the default value is an arbitrary letter.</li>
     *     <li>{@code Double}/{@code double} - the default value is an arbitrary double in the range [0.0, 10.0)</li>
     *     <li>{@code Float}/{@code float} - the default value is an arbitrary float in the range [0,0, 10.0)</li>
     *     <li>{@code Integer}/{@code int} - the default value is an arbitrary int number in the [0, 100) range</li>
     *     <li>{@code Short}/{@code short} - the default value is a short number in the [0, 100) range</li>
     *     <li>{@code String} - a random string with a size of 32</li>
     * </ul>
     *
     * <p>By default the defaults are disabled</p>
     *
     * @param status true to enable, false otherwise.
     * @return The same {@code Reflect<T>} object
     */
    public Reflect<T> useDefaults(boolean status) {
        this.useDefaults = status;
        return this;
    }

    /**
     * <p>Adds a default constant value for a type when the field is not defined using {@link #field(String, Object)}</p>
     * 
     * <p>This definition will be used only if the default values where activated with {@link #useDefaults(boolean)}</p>
     *
     * <p>Can be used to override default values.</p>
     *
     * @param cls The type of the field
     * @param value THe constant value
     * @return The same {@code Reflect<T>} object.
     */
    public Reflect<T> type(Class<?> cls, Object value) {
        notNull(cls, "cls");
        this.defaults.put(cls, constant(value));
        return this;
    }

    /**
     * <p>Adds a default mock unit for a type when the field is not defined using {@link #field(String, MockUnit)} </p>
     *
     * <p>This definition will be used only if the default values where activated with {@link #useDefaults(boolean)}</p>
     *
     * <p>Can be used to override default values.</p>
     *
     * @param cls The type of the field
     * @param mockUnit The unit used to generate the arbitrary value
     * @param <T1> The MockUnit type
     *
     * @return The same {@code Reflect<T>} object.
     */
    public <T1> Reflect<T> type(Class<T1> cls, MockUnit<T1> mockUnit) {
        notNull(cls, "cls");
        notNull(mockUnit, "mockUnit");
        this.defaults.put(cls, unit(mockUnit));
        return this;
    }

    private void validateFields() {
        notNull(fields, "fields");
        fields.forEach((k, v) -> {
            notEmpty(k, "fieldName");
            isTrue(JAVA_FIELD_REGEX.matcher(k).matches(), JAVA_FIELD_REGEX_MATCH, "field", k);
            Field field = getDeclaredField(cls, k, true);
            if (field==null) {
                String fmt = str(JAVA_FIELD_DOESNT_EXIST_ON_CLASS).args("field", k).fmt();
                throw new IllegalArgumentException(fmt);
            }
            boolean isFinal = (field.getModifiers() & FINAL) == FINAL;
            isTrue(!isFinal, JAVA_FIELD_IS_FINAL, "field", k);
        });
    }

    private T instance() {
        try {
            return cls.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            String fmt = str(CANNOT_INSTANTIATE_OBJECT_OF_CLASS)
                            .arg("cls", cls)
                            .fmt();
            throw new IllegalArgumentException(fmt, e);
        }
    }

    private void setValues(T object) {
        getAllFieldsList(cls).forEach(field -> {

            if (field.isSynthetic()) {
                // Skip synthetic fields;
                return;
            }
            else  if (Modifier.isStatic(field.getModifiers())) {
                // Skip static fields
                return;
            }

            String name = field.getName();
            Class<?> fieldCls = field.getType();
            Object val = null;

            if (fields.containsKey(name)) {
                val = fields.get(name).get();
            } else if (defaults.containsKey(fieldCls) && useDefaults) {
                // Fallback to useDefaults
                val = defaults.get(fieldCls).get();
            }
            try {
                writeField(object, name, val, true);
            } catch (IllegalAccessException e) {
                String fmt = str(CANNOT_SET_FIELD_WITH_VALUE)
                        .arg("cls", fieldCls)
                        .arg("field", name)
                        .arg("val", val)
                        .fmt();
                throw new IllegalArgumentException(fmt, e);
            }
        });
    }
}
