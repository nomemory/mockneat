package net.andreinc.mockneat.unit.companies;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.function.Supplier;

/**
 * Copyright 2019, Andrei N. Ciobanu

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

public class Industries extends MockUnitBase implements MockUnitString {

    /**
     * <p>Returns a {@code Industries} object that can be used to generate valid Industry names (Eg.: "Education and Health Services")</p>
     *
     * @return A re-usable {@code Industries} object. The {@code Industries} class implements {@code MockUnitString}.
     */
    public static Industries industries() {
        return MockNeat.threadLocal().industries();
    }

    public Industries(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts().type(DictType.INDUSTRIES).supplier();
    }
}
