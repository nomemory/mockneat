package net.andreinc.mockneat.unit.companies;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.DictType;

import java.util.function.Supplier;

public class Companies extends MockUnitBase implements MockUnitString {

    /**
     * <p>Returns a {@code Companies} object that can be used to generate company names </p>
     *
     * @return A re-usable {@code Companies} object. The {@code Companies} class is implementing {@code MockUnitString}.
     */
    public static Companies companies() {
        return MockNeat.threadLocal().companies();
    }

    @Override
    public Supplier<String> supplier() {
        return mockNeat.dicts().type(DictType.COMPANIES).supplier();
    }

    public Companies(MockNeat mockNeat) {
        super(mockNeat);
    }

}
