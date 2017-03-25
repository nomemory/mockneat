package net.andreinc.mockneat.unit.hashes;

import org.junit.Assert;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.HASH_CYCLES;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;

/**
 * Created by andreinicolinciobanu on 25/03/17.
 */
public class SHA512sTest {

    @Test
    public void testSHA512sHash() throws Exception {
        loop(
                HASH_CYCLES,
                MOCKS,
                m -> m.hashes().sha512().val(),
                sha512 -> Assert.assertTrue(sha512.matches("^[0-9a-f]+$"))
        );
    }
}
