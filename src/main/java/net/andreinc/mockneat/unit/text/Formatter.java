package net.andreinc.mockneat.unit.text;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.abstraction.MockValue;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toMap;
import static net.andreinc.aleph.AlephFormatter.str;
import static net.andreinc.mockneat.abstraction.MockConstValue.constant;
import static net.andreinc.mockneat.abstraction.MockUnitValue.unit;
import static net.andreinc.mockneat.utils.ValidationUtils.*;
import static org.apache.commons.lang3.StringUtils.isAlphanumeric;

public final class Formatter implements MockUnitString {

    private final Map<String, MockValue<?>> fields = new HashMap<>();
    private final String fmt;

    public static Formatter fmt(String fmt) {
        return MockNeat.threadLocal().fmt(fmt);
    }

    public static Formatter formatter(String fmt) {
        return new Formatter(fmt);
    }

    public Formatter(String fmt) {
        notEmpty(fmt, "fmt");
        this.fmt = fmt;
    }

    /**
     * <p>This method can be used to map a parameter from the template with a MockUnit.</p>
     *
     * @param param The parameter name as supplied in the template.
     * @param mock The {@code MockUnit} that generates values for the given parameter.
     * @param <T> The type of the MockUnit
     * @return The {@code Formatter} object.
     */
    public <T> Formatter param(String param, MockUnit<T> mock) {
        notEmpty(param, "param");
        notNull(mock, "mock");
        isTrue(isAlphanumeric(param), INPUT_PARAM_ALPHANUMERIC, "input", param);
        this.fields.put(param, unit(mock));
        return this;
    }

    /**
     * <p>This method can be used to map a parameter from the template with a String value (not a MockUnit!).</p>
     *
     * @param param The parameter name as supplied in the template.
     * @param constValue The string value.
     *                   
     * @return The {@code Formatter} object.
     */
    public Formatter param(String param, String constValue) {
        notEmpty(param, "param");
        notNull(constValue, "constValue");
        isTrue(isAlphanumeric(param), INPUT_PARAM_ALPHANUMERIC, "input", param);
        this.fields.put(param, constant(param));
        return this;
    }

    @Override
    public Supplier<String> supplier() {
        return () -> {
            Map<String, Object> args = fields.entrySet()
                                             .stream()
                                             .collect(toMap(Map.Entry::getKey,
                                                            e -> e.getValue().getStr()));
            return str(fmt).args(args).fmt();
        };
    }
}
