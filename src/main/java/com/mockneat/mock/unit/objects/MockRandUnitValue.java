package com.mockneat.mock.unit.objects;

import com.mockneat.mock.interfaces.MockUnit;

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
        return mockUnit.val();
    }

    @Override
    public Boolean isForced() {
        return forced;
    }
}
