package com.mockneat.mock.unit.companies;

import com.mockneat.mock.utils.file.FileManager;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.mockneat.mock.Constants.DEP_CYCLES;
import static com.mockneat.mock.Constants.MOCKS;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static com.mockneat.types.enums.DictType.DEPARTMENTS;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 26/02/2017.
 */
public class DepartmentsTest {

    private static final FileManager fm = FileManager.getInstance();

    @Test
    public void testDepartmentsCorrectValues() throws Exception {
        Set<String> deps = new HashSet<>(fm.getLines(DEPARTMENTS));
        loop(DEP_CYCLES, MOCKS, (m) -> m.departments().val(), d -> {
            assertTrue(isNotEmpty(d));
            assertTrue(deps.contains(d));
        });
    }
}
