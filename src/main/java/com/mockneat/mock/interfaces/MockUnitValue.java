package com.mockneat.mock.interfaces;

public class MockUnitValue implements MockValue {

    private MockUnit mockUnit;

    public MockUnitValue(MockUnit mockUnit) {
        this.mockUnit = mockUnit;
    }

    @Override
    public Object get() {
        return mockUnit.supplier().get();
    }
}
