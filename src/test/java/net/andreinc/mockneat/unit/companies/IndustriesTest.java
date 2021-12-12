package net.andreinc.mockneat.unit.companies;

import net.andreinc.mockneat.utils.file.FileManager;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.types.enums.DictType.INDUSTRIES;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.assertTrue;

public class IndustriesTest {
    private static final FileManager fm = FileManager.getInstance();

    @Test
    public void testCompaniesCorrectValues() {
        Set<String> inds = new HashSet<>(fm.getLines(INDUSTRIES));
        loop(INDUSTRIES_CYCLES, MOCKS, (m) -> m.industries().val(), d -> {
            assertTrue(isNotEmpty(d));
            assertTrue(inds.contains(d));
        });
    }
}
