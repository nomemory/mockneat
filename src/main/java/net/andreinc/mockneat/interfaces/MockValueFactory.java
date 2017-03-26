package net.andreinc.mockneat.interfaces;

import static net.andreinc.mockneat.interfaces.MockUnitValue.unit;

/**
 * Created by andreinicolinciobanu on 13/03/2017.
 */
public class MockValueFactory {
    public static MockValue value(MockUnit mockUnit) {
        return unit(mockUnit);
    }
    public static MockValue value(Object o) {
        return MockConstValue.constant(o);
    }
}
