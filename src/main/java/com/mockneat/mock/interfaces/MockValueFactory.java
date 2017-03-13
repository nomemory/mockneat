package com.mockneat.mock.interfaces;

/**
 * Created by andreinicolinciobanu on 13/03/2017.
 */
public class MockValueFactory {
    public static MockValue value(MockUnit mockUnit) {
        return new MockUnitValue(mockUnit);
    }
    public static MockValue value(Object o) {
        return MockConstValue.val(o);
    }
}
