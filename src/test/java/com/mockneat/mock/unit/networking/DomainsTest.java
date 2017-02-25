package com.mockneat.mock.unit.networking;

import com.mockneat.mock.utils.file.FileManager;
import com.mockneat.types.enums.DomainSuffixType;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.mockneat.mock.Constants.DOMAIN_CYCLES;
import static com.mockneat.mock.Constants.RAND;
import static com.mockneat.mock.Constants.RANDS;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static com.mockneat.types.enums.DictType.DOMAIN_TOP_LEVEL_POPULAR;
import static com.mockneat.types.enums.DomainSuffixType.ALL;
import static com.mockneat.types.enums.DomainSuffixType.POPULAR;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 21/02/2017.
 */
public class DomainsTest {

    public static final FileManager FM = FileManager.getInstance();

    @Test
    public void testDomain() throws Exception {
        Set<String> set = new HashSet<>(FM.getLines(DOMAIN_TOP_LEVEL_POPULAR));
        loop(DOMAIN_CYCLES, RANDS, r -> r.domains().val(), d -> {
            assertTrue(set.contains(d));
        });
    }

    @Test(expected = NullPointerException.class)
    public void testDomainNullType() throws Exception {
        RAND.domains().type(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDomainNullTypes() throws Exception {
        DomainSuffixType[] types = null;
        RAND.domains().types(types).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDomainEmptyTypes() throws Exception {
        RAND.domains().types().val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDomainEmptyTypes1() throws Exception {
        RAND.domains().types(new DomainSuffixType[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDomainNullElementInTypes() throws Exception {
        RAND.domains().types(new DomainSuffixType[]{ ALL, POPULAR, null}).val();
    }
}
