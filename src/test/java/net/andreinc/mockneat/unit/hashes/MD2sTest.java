package net.andreinc.mockneat.unit.hashes;

import org.junit.Test;

import static net.andreinc.mockneat.Constants.HASH_CYCLES;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 25/03/17.
 */
public class MD2sTest {
    @Test
    public void testMD2sHash() throws Exception {
        loop(
                HASH_CYCLES,
                MOCKS,
                m -> m.hashes().md2().val(),
                md2 ->assertTrue(md2.matches("^[0-9a-f]+$"))
        );
    }
}
