package net.andreinc.mockneat.unit.hashes;

import org.junit.Test;

import static net.andreinc.mockneat.Constants.HASH_CYCLES;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class SHA1sTest {
    @Test
    public void testSHA1sHash() {
        loop(
                HASH_CYCLES,
                MOCKS,
                m -> m.hashes().sha1().val(),
                sha1 -> assertTrue(sha1.matches("^[0-9a-f]+$"))
        );
    }
}
