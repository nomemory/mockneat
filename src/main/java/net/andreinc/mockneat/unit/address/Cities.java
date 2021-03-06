package net.andreinc.mockneat.unit.address;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;

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

public class Cities extends MockUnitBase {

    /**
     * <p>Returns a {@code Cities} object that can be used to generate arbitrary city names from around the world.</p>
     *
     * @return A re-usable {@code Citites} object.
     */
    public static Cities cities() {
        return MockNeat.threadLocal().cities();
    }

    public Cities(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * Returns a {@code MockUnitString} that can be used to generate capital city names from around the world. (Eg.: "Paris")
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString capitals() {
        return () -> mockNeat.dicts()
                                .type(DictType.CITIES_CAPITALS)
                                .supplier();
    }

    /**
     * Returns a {@code MockUnitString} that can be used to generate capital city names from Europe (Eg.: "London")
     *
     * @return A new {@code MockUnitString}
     */
    public MockUnitString capitalsEurope() {
        return () -> mockNeat.dicts()
                                .type(DictType.CITIES_CAPITALS_EUROPE)
                                .supplier();
    }

    /**
     * Returns a {@code MockUnitString} that can be used to generate capital city names from Africa (Eg.: "Mogadishu")
     *
     * @return A new {@code MockUnitString}
     */
    public MockUnitString capitalsAfrica() {
        return () -> mockNeat.dicts()
                                .type(DictType.CITIES_CAPITALS_AFRICA)
                                .supplier();
    }

    /**
     * Returns a {@code MockUnitString} that can be used to generate capital city names from Asia
     *
     * @return A new {@code MockUnitString}
     */
public MockUnitString capitalsAsia() {
        return () -> mockNeat.dicts()
                                .type(DictType.CITIES_CAPITALS_ASIA)
                                .supplier();
    }

    /**
     * Returns a {@code MockUnitString} that can be used to generate capital city names from America
     *
     * @return A new {@code MockUnitString}
     */
    public MockUnitString capitalsAmerica() {
        return () -> mockNeat.dicts()
                        .type(DictType.CITIES_CAPITALS_AMERICA)
                        .supplier();
    }

    /**
     * Returns a {@code MockUnitString} that can be used to generate Australia and Oceania capitals
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString capitalsAustraliaAndOceania() {
        return () -> mockNeat.dicts()
                                .type(DictType.CITIES_CAPITALS_AUSTRALIA_AND_OCEANIA)
                                .supplier();
    }

    /**
     * Returns a {@code MockUnitString} that can be used to generate US city names. (Eg.: "New York")
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString us() {
        return () -> mockNeat.dicts()
                                .type(DictType.CITIES_US)
                                .supplier();
    }
}
