package net.andreinc.mockneat.unit.financial;

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

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.CurrencySymbolType;

import static net.andreinc.mockneat.types.enums.DictType.FOREX_PAIRS;

public class Currencies extends MockUnitBase {

    public static Currencies currencies() {
        return MockNeat.threadLocal().currencies();
    }

    protected Currencies() { }

    public Currencies(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate Forex Pairs (eg.: "EUR/USD")</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString forexPair() {
        return () -> mockNeat.dicts().type(FOREX_PAIRS)::val;
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate Currency Codes (eg.: "USD")</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString code() {
        return () -> mockNeat.from(CurrencySymbolType.class)
                            .mapToString(CurrencySymbolType::getCode)::val;
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate Currency Symbols (eg.: "$")</p>
     *
     * @return A new {@code MockUnitString}
     */
    public MockUnitString symbol() {
        return () -> mockNeat.from(CurrencySymbolType.class)
                            .mapToString(CurrencySymbolType::getSymbol)::val;
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate Currency Names (eg: "Dollar")</p>
     *
     * @return A new {@code MockUnitString}
     */
    public MockUnitString name() {
        return () -> mockNeat.from(CurrencySymbolType.class)
                            .mapToString(CurrencySymbolType::getName)::val;
    }
}
