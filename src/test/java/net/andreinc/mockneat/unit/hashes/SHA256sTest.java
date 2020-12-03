package net.andreinc.mockneat.unit.hashes;

import org.junit.Test;

import static net.andreinc.mockneat.Constants.HASH_CYCLES;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class SHA256sTest {
    @Test
    public void testSHA256sHash() {
        loop(
                HASH_CYCLES,
                MOCKS,
                m -> m.hashes().sha256().val(),
                sha256 -> assertTrue(sha256.matches("^[0-9a-f]+$"))
        );
    }
}
