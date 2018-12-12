package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.MockNeat;
import org.junit.Assert;
import org.junit.Test;

public class MockUnitBaseTest {
    @Test
    public void testMockUnitBase() {
        MockUnitBase mub = new MockUnitBase() {};
        Assert.assertEquals(mub.mockNeat, MockNeat.threadLocal());
    }
}
