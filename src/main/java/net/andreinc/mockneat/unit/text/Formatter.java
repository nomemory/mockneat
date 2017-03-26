package net.andreinc.mockneat.unit.text;

/*
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

import net.andreinc.mockneat.interfaces.MockUnit;
import net.andreinc.mockneat.interfaces.MockUnitString;
import net.andreinc.mockneat.interfaces.MockValue;
import net.andreinc.mockneat.utils.NamedFormatUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toMap;
import static net.andreinc.mockneat.interfaces.MockUnitValue.unit;
import static net.andreinc.mockneat.utils.ValidationUtils.*;
import static org.apache.commons.lang3.StringUtils.isAlphanumeric;
import static org.apache.commons.lang3.Validate.*;
import static org.apache.commons.lang3.Validate.notEmpty;

public class Formatter implements MockUnitString {

    private final Map<String, MockValue> fields = new HashMap<>();
    private final String fmt;

    private Formatter(String fmt) {
        this.fmt = fmt;
    }

    public static Formatter formatter(String fmt) {
        notEmpty(fmt, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "fmt");
        return new Formatter(fmt);
    }

    public <T> Formatter param(String param, MockUnit<T> mock) {
        notEmpty(param, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "param");
        notNull(mock, INPUT_PARAMETER_NOT_NULL, "mock");
        isTrue(isAlphanumeric(param), INPUT_PARAM_ALPHANUMERIC, param);
        this.fields.put(param, unit(mock));
        return this;
    }

    @Override
    public Supplier<String> supplier() {
        return () -> {
            Map<String, String> args = fields.entrySet()
                                             .stream()
                                             .collect(toMap(e -> e.getKey(),
                                                            e -> e.getValue().getStr()));
            return NamedFormatUtils.format(fmt, args);
        };
    }
}
