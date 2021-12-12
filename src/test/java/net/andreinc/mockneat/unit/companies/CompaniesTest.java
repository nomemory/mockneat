package net.andreinc.mockneat.unit.companies;

import net.andreinc.mockneat.utils.file.FileManager;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.types.enums.DictType.COMPANIES;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.assertTrue;

public class CompaniesTest {
    private static final FileManager fm = FileManager.getInstance();

    @Test
    public void testCompaniesCorrectValues() {
        Set<String> cmps = new HashSet<>(fm.getLines(COMPANIES));
        loop(COMPANIES_CYCLES, MOCKS, (m) -> m.companies().val(), d -> {
            assertTrue(isNotEmpty(d));
            assertTrue(cmps.contains(d));
        });
    }
}
