package net.andreinc.mockneat.unit.companies;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

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
