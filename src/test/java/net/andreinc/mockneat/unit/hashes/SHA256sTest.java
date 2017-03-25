package net.andreinc.mockneat.unit.hashes;

import org.junit.Test;

import static net.andreinc.mockneat.Constants.HASH_CYCLES;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 25/03/17.
 */
public class SHA256sTest {
    @Test
    public void testSHA256sHash() throws Exception {
        loop(
                HASH_CYCLES,
                MOCKS,
                m -> m.sha256s().val(),
                sha256 -> assertTrue(sha256.matches("^[0-9a-f]+$"))
        );
    }
}
