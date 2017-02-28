package com.mockneat.mock.unit.user;

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
import com.mockneat.mock.interfaces.MockUnitString;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.NameType;

import java.util.function.Supplier;

import static com.mockneat.mock.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.mock.utils.ValidationUtils.notEmptyTypes;
import static com.mockneat.types.enums.NameType.FIRST_NAME;
import static com.mockneat.types.enums.NameType.LAST_NAME;
import static org.apache.commons.lang3.Validate.inclusiveBetween;
import static org.apache.commons.lang3.Validate.notNull;

public class Names implements MockUnitString {

    private MockNeat mock;

    public Names(MockNeat mock) {
        this.mock = mock;
    }

    @Override
    public Supplier<String> supplier() {
        return full().supplier();
    }

    public MockUnitString first() { return type(FIRST_NAME); }

    public MockUnitString last() { return type(LAST_NAME); }

    public MockUnitString full() {
        Supplier<String> supp = () -> first().val() + " " + last().val();
        return () -> supp;
    }

    public MockUnitString full(double middleInitialProbability) {
        inclusiveBetween(0.0, 100.0, middleInitialProbability);
        Supplier<String> supp = () -> {
            boolean middleName = mock.bools().probability(middleInitialProbability).val();
            String initial = (middleName) ? " " + mock.chars().upperLetters().val() + "." : "";
            return first().val() + initial + " " + last().val();
        };
        return () -> supp;
    }

    public MockUnitString types(NameType... types) {
        notEmptyTypes(types);
        NameType nameType = mock.from(types).val();
        return type(nameType);
    }

    public MockUnitString type(NameType type) {
        notNull(type, INPUT_PARAMETER_NOT_NULL, "type");
        DictType dictType = mock.from(type.getDictionaries()).val();
        return () -> mock.dicts().type(dictType)::val;
    }
}
