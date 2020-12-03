package net.andreinc.mockneat.unit.companies;

import net.andreinc.mockneat.utils.file.FileManager;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static net.andreinc.mockneat.Constants.DEP_CYCLES;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.types.enums.DictType.DEPARTMENTS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 26/02/2017.
 */
public class DepartmentsTest {

    private static final FileManager fm = FileManager.getInstance();

    @Test
    public void testDepartmentsCorrectValues() {
        Set<String> deps = new HashSet<>(fm.getLines(DEPARTMENTS));
        loop(DEP_CYCLES, MOCKS, (m) -> m.departments().val(), d -> {
            assertTrue(isNotEmpty(d));
            assertTrue(deps.contains(d));
        });
    }
}
