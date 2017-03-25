package net.andreinc.mockneat.unit.hashes;

import net.andreinc.mockneat.utils.LoopsUtils;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.MD5_CYCLES;
import static net.andreinc.mockneat.Constants.MOCKS;

/**
 * Created by andreinicolinciobanu on 25/03/17.
 */
public class MD5sTest {
    @Test
    public void testMD5sHash() throws Exception {
        LoopsUtils.loop(
                true,
                MD5_CYCLES,
                MOCKS,
                m -> m.md5s().val(),
                md5 -> md5.matches("^[0-9A-F]+$")
        );
    }
}
