package net.andreinc.mockneat.unit.companies;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.DictType.DEPARTMENTS;

public class Departments extends MockUnitBase implements MockUnitString {

    /**
     * <p>Returns a {@code Departments} object that can be used to generate arbitrary names representing department names from a company.</p>
     *
     * @return A re-usable {@code Departments} object. The {@code Departments} class is implementing {@code MockUnitString}.
     */
    public static Departments departments() {
        return MockNeat.threadLocal().departments();
    }

    protected Departments() { }

    public Departments(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts().type(DEPARTMENTS).supplier();
    }
}
