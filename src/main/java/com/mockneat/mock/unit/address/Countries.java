package com.mockneat.mock.unit.address;

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
import static com.mockneat.types.enums.DictType.COUNTRY_ISO_CODE_2;
import static com.mockneat.types.enums.DictType.COUNTRY_NAME;

/**
 * Created by andreinicolinciobanu on 16/01/2017.
 */
public class Countries {

    private MockNeat mock;

    public Countries(MockNeat mock) {
        this.mock = mock;
    }

    public MockUnitString names() {
        return mock.dicts().type(COUNTRY_NAME);
    }

    public MockUnitString iso2() {
        return mock.dicts().type(COUNTRY_ISO_CODE_2);
    }
}
