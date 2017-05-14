package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.MockNeat;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by andreinicolinciobanu on 14/05/17.
 */
public abstract class MockUnitBase {

    protected final MockNeat mockNeat;

    public MockUnitBase(MockNeat mockNeat) {
        notNull(mockNeat, "mockNeat");
        this.mockNeat = mockNeat;
    }
}
