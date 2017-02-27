package com.mockneat.mock.unit.financial;

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

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.utils.ValidationUtils;
import com.mockneat.mock.interfaces.MockUnitString;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Supplier;

import static java.text.NumberFormat.getCurrencyInstance;
import static java.util.Locale.US;
import static org.apache.commons.lang3.Validate.notNull;

public class Money implements MockUnitString {

    private MockNeat mock;
    private NumberFormat formatter = getCurrencyInstance(US);
    public static final double DEFAULT_LOWER = 0.0;
    public static final double DEFAULT_UPPER = 10000.0;

    public Money(MockNeat mock) {
        this.mock = mock;
    }

    public Money locale(Locale locale) {
        notNull(locale, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "locale");
        this.formatter = getCurrencyInstance(locale);
        return this;
    }

    public MockUnitString range(double lowerBound, double upperBound) {
        return () -> mock.doubles()
                         .range(lowerBound, upperBound)
                         .mapToString(formatter::format).supplier();
    }

    public MockUnitString bound(double bound) {
        return () -> mock.doubles()
                            .bound(bound)
                            .mapToString(formatter::format)::val;
    }

    @Override
    public Supplier<String> supplier() {
        return range(DEFAULT_LOWER, DEFAULT_UPPER).supplier();
    }
}
