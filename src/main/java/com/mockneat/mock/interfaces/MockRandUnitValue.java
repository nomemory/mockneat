package com.mockneat.mock.interfaces;

public class MockRandUnitValue implements MockValue {

    private MockUnit mockUnit;

    private boolean forced = false;

    public MockRandUnitValue(MockUnit mockUnit) {
        this.mockUnit = mockUnit;
    }

    public MockRandUnitValue(MockUnit mockUnit, boolean forced) {
        this(mockUnit);
        this.forced = forced;
    }

    @Override
    public Object get() {
        return mockUnit.supplier().get();
    }
}
