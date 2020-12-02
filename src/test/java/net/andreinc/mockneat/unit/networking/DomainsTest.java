package net.andreinc.mockneat.unit.networking;

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

import net.andreinc.mockneat.types.enums.DomainSuffixType;
import net.andreinc.mockneat.utils.file.FileManager;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.types.enums.DictType.DOMAIN_TOP_LEVEL_POPULAR;
import static net.andreinc.mockneat.types.enums.DomainSuffixType.ALL;
import static net.andreinc.mockneat.types.enums.DomainSuffixType.POPULAR;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class DomainsTest {

    public static final FileManager FM = FileManager.getInstance();

    @Test
    public void testDomain() {
        Set<String> set = new HashSet<>(FM.getLines(DOMAIN_TOP_LEVEL_POPULAR));
        loop(DOMAIN_CYCLES, MOCKS, r -> r.domains().val(), d -> assertTrue(set.contains(d)));
    }

    @Test(expected = NullPointerException.class)
    public void testDomainNullType() {
        M.domains().type(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDomainNullTypes() {
        DomainSuffixType[] types = null;
        M.domains().types(types).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDomainEmptyTypes() {
        M.domains().types().val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDomainEmptyTypes1() {
        M.domains().types(new DomainSuffixType[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDomainNullElementInTypes() {
        M.domains().types(new DomainSuffixType[]{ ALL, POPULAR, null}).val();
    }
}
