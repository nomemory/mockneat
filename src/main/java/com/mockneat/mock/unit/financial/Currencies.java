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
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitString;
import com.mockneat.types.enums.CurrencySymbolType;

import static com.mockneat.types.enums.DictType.FOREX_PAIRS;

public class Currencies {
    private final MockNeat mock;

    public Currencies(MockNeat mock) {
        this.mock = mock;
    }

    public MockUnitString forexPair() {
        return () -> mock.dicts().type(FOREX_PAIRS)::val;
    }

    public MockUnitString code() {
        return () -> mock.from(CurrencySymbolType.class)
                            .mapToString(CurrencySymbolType::getCode)::val;
    }

    public MockUnitString symbol() {
        return () -> mock.from(CurrencySymbolType.class)
                            .mapToString(CurrencySymbolType::getSymbol)::val;
    }

    public MockUnitString name() {
        return () -> mock.from(CurrencySymbolType.class)
                            .mapToString(CurrencySymbolType::getName)::val;
    }
}
