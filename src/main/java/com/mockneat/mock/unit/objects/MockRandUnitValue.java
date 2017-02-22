package com.mockneat.mock.unit.objects;

import com.mockneat.mock.interfaces.MockUnit;

public class MockRandUnitValue implements MockValue {

    private MockUnit mockUnit;

    public MockRandUnitValue(MockUnit ru) {
        this.mockUnit = ru;
    }

    @Override
    public Object get() {
        return mockUnit.val();
    }
}
