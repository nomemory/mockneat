package net.andreinc.mockneat.unit.user;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Genders extends MockUnitBase implements MockUnitString {

    private static final List<String> LONG = Arrays.asList("Male", "Female");
    private static final List<String> SHORT = Arrays.asList("M", "F");

    public Genders(MockNeat mockNeat) {
        super(mockNeat);
    }

    @Override
    public Supplier<String> supplier() {
        return mockNeat.from(LONG).supplier();
    }

    /**
     * <p>Returns a new {@code MockUnitString} that is used to return "M" or "F".</p>
     *
     * @return A new {@code MockUnitString}.
     */
    public MockUnitString letter() {
        return () -> mockNeat.from(SHORT).supplier();
    }
}
