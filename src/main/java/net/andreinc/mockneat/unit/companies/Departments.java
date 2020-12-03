package net.andreinc.mockneat.unit.companies;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.function.Supplier;

import static net.andreinc.mockneat.types.enums.DictType.DEPARTMENTS;

public class Departments extends MockUnitBase implements MockUnitString {

    public static Departments departments() {
        return MockNeat.threadLocal().departments();
    }

    protected Departments() { }

    public Departments(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts().type(DEPARTMENTS)::val;
    }
}
