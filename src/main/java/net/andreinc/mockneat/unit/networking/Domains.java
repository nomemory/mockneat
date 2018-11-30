package net.andreinc.mockneat.unit.networking;

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
import net.andreinc.mockneat.types.enums.DomainSuffixType;
import net.andreinc.mockneat.utils.ValidationUtils;

import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.DomainSuffixType.ALL;
import static net.andreinc.mockneat.types.enums.DomainSuffixType.POPULAR;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmptyOrNullValues;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Domains extends MockUnitBase implements MockUnitString {

    public static Domains domains() {
        return MockNeat.threadLocal().domains();
    }

    protected Domains() { }

    public Domains(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return type(POPULAR)::val;
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate popular domain names (like: "com", "org", "net", "edu", "gov", "info", "io").</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString popular() {
        return type(POPULAR);
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate domain names, both popular (eg.: "com") or lesser known ("cern", "chat).</p>
     *
     * @return A new {@code MockUnitString}
     */
    public MockUnitString all() {
        return type(ALL);
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate domains based on the specified {@link DomainSuffixType}.</p>
     *
     * @param type The type of the domains to generated.
     * @return A new {@code MockUnitString}
     */
    public MockUnitString type(DomainSuffixType type) {
        notNull(type, "type");
        return () -> mockNeat.dicts().type(type.getDictType())::val;
    }

    /**
     * <p>Returns a new {@code MockUnitString} that can be used to generate domain names based on the specified {@link DomainSuffixType}s.</p>
     * @param types A var-arg array of {@link DomainSuffixType}.
     * @return A new {@code MockUnitString}
     */
    public MockUnitString types(DomainSuffixType... types) {
        notEmptyOrNullValues(types, "types");
        DomainSuffixType type = mockNeat.from(types).val();
        return type(type);
    }
}
