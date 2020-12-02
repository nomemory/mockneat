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
import net.andreinc.mockneat.types.enums.HostNameType;
import net.andreinc.mockneat.types.enums.URLSchemeType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.RegexValidator;
import org.apache.commons.validator.routines.UrlValidator;
import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.types.enums.DomainSuffixType.POPULAR;
import static net.andreinc.mockneat.types.enums.HostNameType.ADJECTIVE_FIRST_NAME;
import static net.andreinc.mockneat.types.enums.HostNameType.NOUN_FIRST_NAME;
import static net.andreinc.mockneat.types.enums.URLSchemeType.*;
import static net.andreinc.mockneat.unit.networking.URLs.COMMON_HTTP_PORTS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.validator.routines.UrlValidator.ALLOW_ALL_SCHEMES;
import static org.junit.Assert.*;

public class URLsTest {

    private static final UrlValidator DEFAULT_URL_VALID = new UrlValidator();
    private static final UrlValidator FUT_URL_VALIDATOR = new UrlValidator(new String[]{"fut"});
    private static final UrlValidator CUSTOM_DOMAINS_VALIDATOR =
            new UrlValidator(
                    new RegexValidator("^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$"),
                    ALLOW_ALL_SCHEMES
            );

    @Test
    public void testURLDefault() {
        loop(URL_CYCLES, MOCKS, r -> r.urls().val(), u ->
                assertTrue(DEFAULT_URL_VALID.isValid(u)));
    }

    @Test
    public void testURLSchemes() {
        loop(URL_CYCLES, MOCKS, r -> r.urls().scheme(HTTPS).val(), u -> {
            assertTrue(u.startsWith(HTTPS.getStr()));
            assertTrue(DEFAULT_URL_VALID.isValid(u));
        });
    }

    @Test
    public void testURLSchemesMultiple() {
        loop(URL_CYCLES, MOCKS, r -> r.urls().schemes(FTP, HTTP, HTTPS).val(), u -> {
            assertTrue(u.startsWith(FTP.getStr()) ||
                    u.startsWith(HTTPS.getStr()) ||
                    u.startsWith(HTTP.getStr()));
            assertTrue(DEFAULT_URL_VALID.isValid(u));
        });
    }

    @Test
    public void testURLSCustomeScheme() {
        String scheme = "fut";
        loop(URL_CYCLES, MOCKS, r -> r.urls().scheme(scheme).val(), u -> {
            assertTrue(u.startsWith(scheme));
            assertTrue(FUT_URL_VALIDATOR.isValid(u));
        });
    }

    @Test
    public void testURLSNoScheme() {
        loop(URL_CYCLES, MOCKS, r -> r.urls().scheme(NONE.getStr()).val(), u -> {
            String[] usplit = u.split("\\.");
            assertEquals(3, usplit.length);
            assertEquals("www", usplit[0]);
        });
    }

    @Test
    public void testURLsHostString() {
        String host = "andreiciobanu";
        loop(URL_CYCLES, MOCKS, r -> r.urls().host(host).val(), u -> {
            String[] split = u.split("\\.");
            assertTrue(DEFAULT_URL_VALID.isValid(u));
            assertEquals(split[1], host);
        });
    }

    @Test
    public void testURLsHostsStrings() {
        String[] hosts = {"abc", "acd", "aef"};
        Set<String> hostsSet = new HashSet<>(asList(hosts));
        loop(URL_CYCLES, MOCKS, r -> r.urls().hosts(hosts).val(), u -> {
            String[] split = u.split("\\.");
            assertTrue(DEFAULT_URL_VALID.isValid(u));
            assertTrue(hostsSet.contains(split[1]));
        });
    }

    @Test
    public void testURLsHostType() {
        loop(URL_CYCLES,
                MOCKS,
                r -> {
                    HostNameType type = r.from(HostNameType.class).val();
                    return r.urls().host(type).val();
                },
                u -> assertTrue(DEFAULT_URL_VALID.isValid(u)));
    }

    @Test
    public void testURLsHostsType() {
        HostNameType[] hosts = new HostNameType[]{ADJECTIVE_FIRST_NAME, NOUN_FIRST_NAME};
        loop(URL_CYCLES,
                MOCKS,
                r -> r.urls().hosts(hosts).val(),
                u -> assertTrue(DEFAULT_URL_VALID.isValid(u)));
    }

    @Test
    public void testURLsPort() {
        Set<Integer> commonPortsSet = new HashSet<>(asList(COMMON_HTTP_PORTS));
        loop(URL_CYCLES, MOCKS, r -> r.urls().ports().val(), u -> {
            assertTrue(DEFAULT_URL_VALID.isValid(u));
            String[] uSplit = u.split(":");
            Integer port = parseInt(uSplit[2]);
            assertTrue(commonPortsSet.contains(port));
        });
    }

    @Test
    public void testURLsPortConstant() {
        loop(URL_CYCLES, MOCKS, r -> r.urls().port(1080).val(), u -> {
            assertTrue(DEFAULT_URL_VALID.isValid(u));
            String[] uSplit = u.split(":");
            int port = parseInt(uSplit[2]);
            assertEquals(1080, port);
        });
    }

    @Test
    public void testURLsPortConstants() {
        loop(URL_CYCLES, MOCKS, r -> r.urls().ports(1021, 1022, 1023).val(), u -> {
            assertTrue(DEFAULT_URL_VALID.isValid(u));
            String[] uSplit = u.split(":");
            Integer port = parseInt(uSplit[2]);
            assertTrue(port.equals(1021) || port.equals(1022) || port.equals(1023));
        });
    }

    @Test
    public void testURLsDomain() {
        loop(URL_CYCLES, MOCKS,
                r -> r.urls().domain("cucu").val(),
                u -> assertTrue(CUSTOM_DOMAINS_VALIDATOR.isValid(u)));
    }

    @Test
    public void testURLsDomainAndPort() {
        loop(URL_CYCLES, MOCKS, r -> r.urls().domain("mucu").ports(8080, 8090).val(), u -> {
            Integer port = parseInt(u.split(":")[2]);
            String domain = u.split("\\.")[2].split(":")[0];
            assertTrue(port.equals(8090) || port.equals(8080));
            assertEquals("mucu", domain);
        });
    }

    @Test
    public void testURLsDomains() {
        loop(URL_CYCLES, MOCKS, r -> r.urls().domains("cu", "mu", "bu", "la").ports(100, 200).val(), u -> {
            Integer port = parseInt(u.split(":")[2]);
            String domain = u.split("\\.")[2].split(":")[0];
            assertTrue(port.equals(100) || port.equals(200));
            assertTrue(domain.equals("cu") ||
                    domain.equals("mu") ||
                    domain.equals("bu") ||
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
    public void testURLNullScheme() {
        String scheme = null;
        M.urls().scheme(scheme).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testURLEmptySchemes() {
        M.urls().schemes(new String[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLNullSchemes() {
        String[] schemes = null;
        M.urls().schemes(schemes).val();
    }

    @Test
    public void testURLEmptyStringScheme() {
        M.urls().scheme("").val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testURLEmptyStringSchemes() {
        M.urls().schemes("", "abba", "baab").val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLNullStringsSchemes() {
        M.urls().schemes(new String[]{null, "abba", "baab"}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLNullSchemeType() {
        URLSchemeType schemeType = null;
        M.urls().scheme(schemeType).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLNullSchemeTypes() {
        URLSchemeType[] schemes = null;
        M.urls().schemes(schemes).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLSchemeTypesNulLValue() {
        URLSchemeType[] schemes = new URLSchemeType[]{FTP, HTTP, null, HTTPS};
        M.urls().schemes(schemes).val();
    }

    //
    // Hosts
    //

    @Test(expected = NullPointerException.class)
    public void testURLHostNullString() {
        String host = null;
        M.urls().host(host).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testURLHostEmptyString() {
        String host = "";
        M.urls().host(host).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLHostTypeNull() {
        HostNameType type = null;
        M.urls().host(type).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLHostsNullString() {
        M.urls().hosts(null, "1", "2").val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testURLHostsEmptyString() {
        M.urls().hosts("2", "", "3").val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLHostsNull() {
        HostNameType[] type = null;
        M.urls().hosts(type).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLHostsWithNull() {
        M.urls().hosts(ADJECTIVE_FIRST_NAME, null).val();
    }

    //
    // Domains
    //

    @Test(expected = NullPointerException.class)
    public void testURLDomainNullString() {
        String domain = null;
        M.urls().domain(domain).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testURLDomainEmptyString() {
        String domain = "";
        M.urls().domain(domain).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLDomainTypeNull() {
        DomainSuffixType domainType = null;
        M.urls().domain(domainType).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLDomainsNullString() {
        M.urls().domains(null, "com", "net").val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testURLDomainsEmptyString() {
        M.urls().domains("net", "", "org").val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLDomainsNull() {
        DomainSuffixType[] types = null;
        M.urls().domains(types).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLDomainsWithNull() {
        M.urls().domains(POPULAR, null).val();
    }

    //
    // Ports
    //

    @Test(expected = NullPointerException.class)
    public void testURLPortNull() {
        Integer port = null;
        M.urls().port(port).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLPortsNullArray() {
        Integer[] ports = null;
        M.urls().ports(ports).val();
    }

    @Test(expected = NullPointerException.class)
    public void testURLPortsNullPort() {
        M.urls().ports(3, 5, null).val();
    }

    //
    // Auth
    //

    @Test
    public void testURLAuth() {
        loop(
                URL_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.urls().auth().val(),
                url -> {
                    assertNotNull(url);
                    try {
                        URL urlObj = new URL(url);
                        String[] userInfo = urlObj.getUserInfo().split(":");

                        assertEquals(2, userInfo.length);
                        assertTrue(StringUtils.isNotEmpty(userInfo[0]));
                        assertTrue(StringUtils.isNotEmpty(userInfo[1]));

                        assertTrue(UrlValidator.getInstance().isValid(url));

                    } catch (MalformedURLException e) {
                        Assert.fail();
                    }
                }
        );
    }

    //
    // Schemes
    //

    @Test(expected = NullPointerException.class)
    public void testSchemeNullSchemeString() {
        String scheme = null;
        M.urls().scheme(scheme).val();
    }


    @Test
    public void testSchemeString() {
        loop(
                URL_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.urls().scheme("a").val(),
                url -> {
                    String[] splits = url.split("//");

                    assertEquals(2, splits.length);
                    assertEquals("a:", splits[0]);
                }
        );
    }

    @Test
    public void testSchemeType() {
        loop(
                URL_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.urls().scheme(FTP).val(),
                url -> {
                    String[] splits = url.split("//");

                    assertEquals(2, splits.length);
                    assertEquals("ftp:", splits[0]);
                }
        );
    }

    @Test
    public void testSchemesTypes() {
        loop(
                URL_CYCLES,
                MOCKS,
                mockNeat -> mockNeat.urls()
                                    .schemes(HTTPS, HTTP)
                                    .val(),
                url -> {

                    String[] splits = url.split("//");

                    assertEquals(2, splits.length);
                    assertTrue("http:".equals(splits[0]) ||
                                        "https:".equals(splits[0]));
                }
        );
    }


    @Test(expected = NullPointerException.class)
    public void testSchemeNullType() {
        URLSchemeType schemeType = null;
        M.urls().scheme(schemeType).val();
    }

}
