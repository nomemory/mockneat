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
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitString;
import com.mockneat.mock.utils.LoopsUtils;
import com.mockneat.types.enums.CVVType;

import java.util.function.Supplier;

import static com.mockneat.mock.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.types.enums.CVVType.CVV3;
import static org.apache.commons.lang3.Validate.notNull;

public class CVVS implements MockUnitString {

    private final MockNeat mock;

    public CVVS(MockNeat mock) {
        this.mock = mock;
    }

    @Override
    public Supplier<String> supplier() {
        return type(CVV3).supplier();
    }

    public MockUnitString type(CVVType type) {
        notNull(type, INPUT_PARAMETER_NOT_NULL, "type");
        Supplier<String> supplier = () -> {
            final StringBuilder builder = new StringBuilder();
            LoopsUtils.loop(type.getLength(), () ->
                    builder.append(mock.chars().digits().val()));
            return builder.toString();
        };
        return () -> supplier;
    }
}
