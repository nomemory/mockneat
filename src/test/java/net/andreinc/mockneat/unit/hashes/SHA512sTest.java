package net.andreinc.mockneat.unit.hashes;

import org.junit.Assert;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.HASH_CYCLES;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;

public class SHA512sTest {

    @Test
    public void testSHA512sHash() {
        loop(
                HASH_CYCLES,
                MOCKS,
                m -> m.hashes().sha512().val(),
                sha512 -> Assert.assertTrue(sha512.matches("^[0-9a-f]+$"))
        );
    }
}
