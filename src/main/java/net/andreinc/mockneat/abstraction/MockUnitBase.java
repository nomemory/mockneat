package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.MockNeat;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * An abstract class containing a MockNeat reference.
 * Most of the MockUnit implementations also extend from this base class.
 */
public abstract class MockUnitBase {

    protected final MockNeat mockNeat;

    public MockUnitBase(MockNeat mockNeat) {
        notNull(mockNeat, "mockNeat");
        this.mockNeat = mockNeat;
    }

    /**
     * By default the MockUnitBase is using {@code  MockNeat.threadLocal()} as the internal MockNeat implementation.
     */
    public MockUnitBase() {
        this.mockNeat = MockNeat.threadLocal();
    }
}
