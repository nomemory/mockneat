package net.andreinc.mockneat.unit.misc;


import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.utils.LoopsUtils;
import net.andreinc.mockneat.utils.file.FileManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class MimesTest {

    private final FileManager fm =
            FileManager.getInstance();

    private final Set<String> allMimes = new HashSet<>(fm.getLines(DictType.MIME_TYPE));

    @Test
    public void testMimes() {
        LoopsUtils.loop(
                Constants.MIME_CYCLES,
                Constants.MOCKS,
                mockNeat -> mockNeat.mimes().val(),
                mime -> Assert.assertTrue(allMimes.contains(mime))
        );
    }

}
