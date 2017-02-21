package com.mockneat.random.unit.networking;

import com.mockneat.types.enums.DomainSuffixType;
import com.mockneat.types.enums.HostNameType;
import com.mockneat.types.enums.URLSchemeType;
import org.apache.commons.validator.routines.RegexValidator;
import org.apache.commons.validator.routines.UrlValidator;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.mockneat.random.RandTestConstants.*;
import static com.mockneat.random.unit.networking.URLs.COMMON_HTTP_PORTS;
import static com.mockneat.random.utils.FunctUtils.loop;
import static com.mockneat.types.enums.DomainSuffixType.POPULAR;
import static com.mockneat.types.enums.HostNameType.ADJECTIVE_FIRST_NAME;
import static com.mockneat.types.enums.HostNameType.NOUN_FIRST_NAME;
import static com.mockneat.types.enums.URLSchemeType.*;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static junit.framework.Assert.assertTrue;
import static org.apache.commons.validator.routines.UrlValidator.ALLOW_ALL_SCHEMES;

public class URLsTest {

    private static final UrlValidator DEFAULT_URL_VALID = new UrlValidator();
    private static final UrlValidator FUT_URL_VALIDATOR = new UrlValidator(new String[]{"fut"});
    private static final UrlValidator CUSTOM_DOMAINS_VALIDATOR =
            new UrlValidator(
                    new RegexValidator("^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$"),
                    ALLOW_ALL_SCHEMES
            );

    @Test
    public void testURLDefault() throws Exception {
        loop(URL_CYCLES, RANDS, r -> r.urls().val(), u ->
                assertTrue(DEFAULT_URL_VALID.isValid(u)));
    }

    @Test
    public void testURLSchemes() throws Exception {
        loop(URL_CYCLES, RANDS, r -> r.urls().scheme(HTTPS).val(), u -> {
            assertTrue(u.startsWith(HTTPS.getStr()));
            assertTrue(DEFAULT_URL_VALID.isValid(u));
        });
    }

    @Test
    public void testURLSchemesMultiple() throws Exception {
        loop(URL_CYCLES, RANDS, r -> r.urls().schemes(FTP, HTTP, HTTPS).val(), u -> {
            assertTrue(u.startsWith(FTP.getStr()) ||
                        u.startsWith(HTTPS.getStr()) ||
                        u.startsWith(HTTP.getStr()));
            assertTrue(DEFAULT_URL_VALID.isValid(u));
        });
    }

    @Test
    public void testURLSCustomeScheme() throws Exception {
        String scheme = "fut";
        loop(URL_CYCLES, RANDS, r -> r.urls().scheme(scheme).val(), u -> {
            assertTrue(u.startsWith(scheme));
            assertTrue(FUT_URL_VALIDATOR.isValid(u));
        });
    }

    @Test
    public void testURLSNoScheme() throws Exception {
        URLSchemeType scheme = URLSchemeType.NONE;
        loop(URL_CYCLES, RANDS, r -> r.urls().scheme(scheme.getStr()).val(), u -> {
            String[] usplit = u.split("\\.");
            assertTrue(usplit.length==3);
            assertTrue(usplit[0].equals("www"));
        });
    }

    @Test
    public void testURLsHostString() throws Exception {
        String host = "andreiciobanu";
        loop(URL_CYCLES, RANDS, r -> r.urls().host(host).val(), u -> {
            String[] split = u.split("\\.");
            assertTrue(DEFAULT_URL_VALID.isValid(u));
            assertTrue(split[1].equals(host));
        });
    }

    @Test
    public void testURLsHostsStrings() throws Exception {
        String[] hosts = { "abc", "acd", "aef" };
        Set<String> hostsSet = new HashSet<>(asList(hosts));
        loop(URL_CYCLES, RANDS, r -> r.urls().hosts(hosts).val(), u -> {
            String[] split = u.split("\\.");
            assertTrue(DEFAULT_URL_VALID.isValid(u));
            assertTrue(hostsSet.contains(split[1]));
        });
    }

    @Test
    public void testURLsHostType() throws Exception {
        loop(URL_CYCLES,
                RANDS,
                r -> {
                    HostNameType type = r.from(HostNameType.class).val();
                    return r.urls().host(type).val();
                },
                u -> assertTrue(DEFAULT_URL_VALID.isValid(u)));
    }

    @Test
    public void testURLsHostsType() throws Exception {
        HostNameType[] hosts = new HostNameType[]{ ADJECTIVE_FIRST_NAME, NOUN_FIRST_NAME};
        loop(URL_CYCLES,
                RANDS,
                r -> r.urls().hosts(hosts).val(),
                u -> assertTrue(DEFAULT_URL_VALID.isValid(u)));
    }

    @Test
    public void testURLsPort() throws Exception {
        Set<Integer> commonPortsSet = new HashSet<>(asList(COMMON_HTTP_PORTS));
        loop(URL_CYCLES, RANDS, r -> r.urls().ports().val(), u -> {
            assertTrue(DEFAULT_URL_VALID.isValid(u));
            String[] uSplit = u.split(":");
            Integer port = parseInt(uSplit[2]);
            assertTrue(commonPortsSet.contains(port));
        });
    }

    @Test
    public void testURLsPortConstant() throws Exception {
        loop(URL_CYCLES, RANDS, r -> r.urls().port(1080).val(), u -> {
            assertTrue(DEFAULT_URL_VALID.isValid(u));
            String[] uSplit = u.split(":");
            Integer port = parseInt(uSplit[2]);
            assertTrue(port.equals(1080));
        });
    }

    @Test
    public void testURLsPortConstants() throws Exception {
        loop(URL_CYCLES, RANDS, r -> r.urls().ports(1021, 1022, 1023).val(), u -> {
            assertTrue(DEFAULT_URL_VALID.isValid(u));
            String[] uSplit = u.split(":");
            Integer port = parseInt(uSplit[2]);
            assertTrue(port.equals(1021) || port.equals(1022) || port.equals(1023));
        });
    }

    @Test
    public void testURLsDomain() throws Exception {
        loop(URL_CYCLES, RANDS, r -> r.urls().domain("cucu").val(), u -> {
            assertTrue(CUSTOM_DOMAINS_VALIDATOR.isValid(u));
        });
    }

    @Test
    public void testURLsDomainAndPort() throws Exception {
        loop(URL_CYCLES, RANDS, r -> r.urls().domain("mucu").ports(8080, 8090).val(), u -> {
            Integer port = parseInt(u.split(":")[2]);
            String domain  = u.split("\\.")[2].split(":")[0];
            assertTrue(port.equals(8090)||port.equals(8080));
            assertTrue(domain.equals("mucu"));
        });
    }

    @Test
    public void testURLsDomains() throws Exception {
        loop(URL_CYCLES, RANDS, r -> r.urls().domains("cu", "mu", "bu", "la").ports(100,200).val(), u -> {       Integer port = parseInt(u.split(":")[2]);
            String domain  = u.split("\\.")[2].split(":")[0];
            assertTrue(port.equals(100)||port.equals(200));
            assertTrue(domain.equals("cu")||
                        domain.equals("mu")||
                        domain.equals("bu")||
                        domain.equals("la"));
        });
    }


    //
    // Test for invalid input parameters for exceptions
    //

    //
    // Schemes
    //

    @Test(expected = NullPointerException.class)
    public void testURLNullScheme() throws Exception {
        String scheme = null;
        RAND.urls().scheme(scheme).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testURLEmptySchemes() throws Exception {
        RAND.urls().schemes(new String[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLNullSchemes() throws Exception {
        String[] schemes = null;
        RAND.urls().schemes(schemes).val();
    }

    @Test
    public void testURLEmptyStringScheme() throws Exception {
        RAND.urls().scheme("").val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testURLEmptyStringSchemes() throws Exception {
        RAND.urls().schemes("", "abba", "baab").val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLNullStringsSchemes() throws Exception {
        RAND.urls().schemes(new String[]{null, "abba", "baab"}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLNullSchemeType() throws Exception {
        URLSchemeType schemeType = null;
        RAND.urls().scheme(schemeType).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLNullSchemeTypes() throws Exception {
        URLSchemeType[] schemes = null;
        RAND.urls().schemes(schemes).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLSchemeTypesNulLValue() throws Exception {
        URLSchemeType[] schemes = new URLSchemeType[]{FTP, HTTP, null, HTTPS};
        RAND.urls().schemes(schemes).val();
    }

    //
    // Hosts
    //

    @Test(expected = NullPointerException.class)
    public void testURLHostNullString() throws Exception {
        String host = null;
        RAND.urls().host(host).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testURLHostEmptyString() throws Exception {
        String host = "";
        RAND.urls().host(host).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLHostTypeNull() throws Exception {
        HostNameType type = null;
        RAND.urls().host(type).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLHostsNullString() throws Exception {
        RAND.urls().hosts(null, "1", "2").val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testURLHostsEmptyString() throws Exception {
        RAND.urls().hosts("2", "", "3").val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLHostsNull() throws Exception {
        HostNameType[] type = null;
        RAND.urls().hosts(type).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLHostsWithNull() throws Exception {
        RAND.urls().hosts(ADJECTIVE_FIRST_NAME, null).val();
    }

    //
    // Domains
    //

    @Test(expected = NullPointerException.class)
    public void testURLDomainNullString() throws Exception {
        String domain = null;
        RAND.urls().domain(domain).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testURLDomainEmptyString() throws Exception {
        String domain = "";
        RAND.urls().domain(domain).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLDomainTypeNull() throws Exception {
        DomainSuffixType domainType = null;
        RAND.urls().domain(domainType).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLDomainsNullString() throws Exception {
        RAND.urls().domains(null, "com", "net").val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testURLDomainsEmptyString() throws Exception {
        RAND.urls().domains("net", "", "org").val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLDomainsNull() throws Exception {
        DomainSuffixType[] types = null;
        RAND.urls().domains(types).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLDomainsWithNull() throws Exception {
        RAND.urls().domains(POPULAR, null).val();
    }

    //
    // Ports
    //

    @Test(expected = NullPointerException.class)
    public void testURLPortNull() throws Exception {
        Integer port = null;
        RAND.urls().port(port).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLPortsNullArray() throws Exception {
        Integer[] ports = null;
        RAND.urls().ports(ports).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLPortsNullPort() throws Exception {
        RAND.urls().ports(3, 5, null).val();
    }
}
