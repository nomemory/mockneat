package net.andreinc.mockneat.unit.user;

import net.andreinc.mockneat.utils.file.FileManager;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.types.enums.DictType.NON_BINARY_GENDERS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.assertTrue;

public class NonBinaryGenderTest {
    private static final FileManager fm = FileManager.getInstance();

    @Test
    public void testCompaniesCorrectValues() {
        Set<String> nbg = new HashSet<>(fm.getLines(NON_BINARY_GENDERS));
        loop(NON_BINARY_GENDER_CYLES, MOCKS, (m) -> m.nonBinaryGenders().val(), d -> {
            assertTrue(isNotEmpty(d));
            assertTrue(nbg.contains(d));
        });
    }
}
