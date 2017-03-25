package net.andreinc.mockneat.unit.hashes;

import org.junit.Test;

import static net.andreinc.mockneat.Constants.MD5_CYCLES;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;

/**
 * Created by andreinicolinciobanu on 25/03/17.
 */
public class MD5sTest {
    @Test
    public void testMD5sHash() throws Exception {
        loop(
                MD5_CYCLES,
                MOCKS,
                m -> m.md5s().val(),
                md5 -> md5.matches("^[0-9A-F]+$")
        );
    }
}
