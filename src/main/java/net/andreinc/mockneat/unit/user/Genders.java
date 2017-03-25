package net.andreinc.mockneat.unit.user;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.interfaces.MockUnitString;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Genders implements MockUnitString {

    private static final List<String> LONG = Arrays.asList("Male", "Female");
    private static final List<String> SHORT = Arrays.asList("M", "F");

    private MockNeat mock;

    public Genders(MockNeat mock) {
        this.mock = mock;
    }

    @Override
    public Supplier<String> supplier() {
        return mock.from(LONG).supplier();
    }

    public MockUnitString letter() {
        return () -> mock.from(SHORT).supplier();
    }
}
