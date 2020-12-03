package net.andreinc.mockneat.unit.networking;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DomainSuffixType;

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
        return () -> {
            DomainSuffixType type = mockNeat.from(types).val();
            return type(type).supplier();
        };
    }
}
