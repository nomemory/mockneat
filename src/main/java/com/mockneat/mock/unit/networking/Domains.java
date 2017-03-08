package com.mockneat.mock.unit.networking;

/*
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitString;
import com.mockneat.types.enums.DomainSuffixType;

import java.util.function.Supplier;

import static com.mockneat.mock.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY;
import static com.mockneat.mock.utils.ValidationUtils.notEmptyTypes;
import static com.mockneat.types.enums.DomainSuffixType.POPULAR;
import static org.apache.commons.lang3.Validate.notNull;

public class Domains implements MockUnitString {

    private final MockNeat mock;

    public Domains(MockNeat mock) {
        this.mock = mock;
    }

    @Override
    public Supplier<String> supplier() {
        return type(POPULAR)::val;
    }

    public MockUnitString type(DomainSuffixType type) {
        notNull(type, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "type");
        return () -> mock.dicts().type(type.getDictType())::val;
    }

    public MockUnitString types(DomainSuffixType... types) {
        notEmptyTypes(types);
        DomainSuffixType type = mock.from(types).val();
        return type(type);
    }
}
