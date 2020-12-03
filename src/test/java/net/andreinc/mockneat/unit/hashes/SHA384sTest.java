package net.andreinc.mockneat.unit.hashes;

import org.junit.Test;

import static net.andreinc.mockneat.Constants.HASH_CYCLES;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class SHA384sTest {
    @Test
    public void testSHA384sHash() {
        loop(
                HASH_CYCLES,
                MOCKS,
                m -> m.hashes().sha384().val(),
                sha384 -> assertTrue(sha384.matches("^[0-9a-f]+$"))
        );
    }
}
