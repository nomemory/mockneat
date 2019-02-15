package net.andreinc.mockneat.unit.text;

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
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockConstValue;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.abstraction.MockValue;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toMap;
import static net.andreinc.aleph.AlephFormatter.str;
import static net.andreinc.mockneat.abstraction.MockConstValue.constant;
import static net.andreinc.mockneat.abstraction.MockUnitValue.unit;
import static net.andreinc.mockneat.utils.ValidationUtils.*;
import static org.apache.commons.lang3.StringUtils.isAlphanumeric;

//TODO allow constant as param - document this
public final class Formatter implements MockUnitString {

    private final Map<String, MockValue> fields = new HashMap<>();
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
     * @param <T>
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
                                             .collect(toMap(e -> e.getKey(),
                                                            e -> e.getValue().getStr()));
            return str(fmt).args(args).fmt();
        };
    }
}
