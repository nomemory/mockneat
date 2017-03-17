package net.andreinc.mockneat.unit.networking;

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

/**
 * Created by andreinicolinciobanu on 21/02/2017.
 */
public class DomainsTest {

    public static final FileManager FM = FileManager.getInstance();

    @Test
    public void testDomain() throws Exception {
        Set<String> set = new HashSet<>(FM.getLines(DOMAIN_TOP_LEVEL_POPULAR));
        loop(DOMAIN_CYCLES, MOCKS, r -> r.domains().val(), d -> {
            assertTrue(set.contains(d));
        });
    }

    @Test(expected = NullPointerException.class)
    public void testDomainNullType() throws Exception {
        M.domains().type(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDomainNullTypes() throws Exception {
        DomainSuffixType[] types = null;
        M.domains().types(types).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDomainEmptyTypes() throws Exception {
        M.domains().types().val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDomainEmptyTypes1() throws Exception {
        M.domains().types(new DomainSuffixType[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testDomainNullElementInTypes() throws Exception {
        M.domains().types(new DomainSuffixType[]{ ALL, POPULAR, null}).val();
    }
}
