package net.andreinc.mockneat.unit.financial;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.CurrencySymbolType;

import static net.andreinc.mockneat.types.enums.DictType.FOREX_PAIRS;

public class Currencies extends MockUnitBase {

    /**
     * <p>Returns a {@code Currencies} object that can be used to generate currencies-related information: </p>
     *
     * <ul>
     *     <li>Forex pairs. (Eg.: "USD/CAD")</li>
     *     <li>Currency codes. (Eg.: "USD", "GBP")</li>
     *     <li>Currency symbols. (Eg.: "$", "¥")</li>
     *     <li>Currency names. (Eg.: "Boliviano", "Dollar")</li>
     * </ul>
     *
     * @return A re-usable {@code Currencies} object.
     */
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
