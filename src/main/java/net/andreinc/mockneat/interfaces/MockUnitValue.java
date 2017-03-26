package net.andreinc.mockneat.interfaces;

public class MockUnitValue implements MockValue {

    private final MockUnit mockUnit;

    private MockUnitValue(MockUnit mockUnit) {
        this.mockUnit = mockUnit;
    }

    public static final MockUnitValue unit(MockUnit unit) {
        return new MockUnitValue(unit);
    }

    @Override
    public Object get() {
        return mockUnit.supplier().get();
    }
}
