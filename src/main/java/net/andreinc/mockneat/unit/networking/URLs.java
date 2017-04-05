package net.andreinc.mockneat.unit.networking;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.interfaces.MockUnitString;
import net.andreinc.mockneat.types.Pair;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.types.enums.DomainSuffixType;
import net.andreinc.mockneat.types.enums.HostNameType;
import net.andreinc.mockneat.types.enums.URLSchemeType;

import java.util.List;
import java.util.function.Supplier;

import static net.andreinc.aleph.AlephFormatter.template;
import static net.andreinc.mockneat.types.enums.DomainSuffixType.POPULAR;
import static net.andreinc.mockneat.types.enums.PassStrengthType.MEDIUM;
import static net.andreinc.mockneat.types.enums.StringFormatType.LOWER_CASE;
import static net.andreinc.mockneat.types.enums.URLSchemeType.HTTP;
import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class URLs implements MockUnitString {

    protected static final Integer[] COMMON_HTTP_PORTS = {
            80, 1311, 2480, 4567, 4711, 4712, 5104, 5800, 5988, 5989,
            7000, 7001, 7002, 8008, 8080, 8088, 8280, 8281, 8530, 8531,
            8887, 8888, 9080, 9443, 9981, 9982, 11371, 16080, 18091,
            18092
    };

    // <schemes://><user:password@><host><.domain><:port></.../...>

    private final MockNeat mock;

    // Scheme
    private Supplier<String> schemeSupplier;

    // Auth
    private Supplier<String> authSupplier;
    private Supplier<String> userNameSupplier;
    private Supplier<String> passWordSupplier;

    //Host
    private Supplier<String> hostSupplier;
    private boolean www = true;

    // Domain
    private Supplier<String> domainSupplier;

    // Port
    private Supplier<String> portSupplier;

    public URLs(MockNeat mock) {
        this.mock = mock;
        this.initializeSuppliers();
    }

    private static String urlFormat(String scheme,
                                          String auth,
                                          String host,
                                          String domain,
                                          String port,
                                          String paths) {
        StringBuilder buff = new StringBuilder();
        buff.append(scheme)
                .append(auth)
                .append(host)
                .append(domain)
                .append(port)
                .append(paths);
        return buff.toString();
    }

    private void initializeSuppliers() {
        this.schemeSupplier = defaultSchemesSupplier();
        this.authSupplier = defaultAuthSupplier();
        this.hostSupplier = defaultHostSupplier();
        this.domainSupplier = defaultDomainSupplier();
        this.portSupplier = defaultPortSupplier();
    }

    private Supplier<String> defaultSchemesSupplier() {
        return schemeSupplier(HTTP);
    }

    private Supplier<String> schemeSupplier(String... schemes) {
        return mock.fromStrings(schemes).append("://")::val;
    }

    private Supplier<String> schemeSupplier(String scheme) {
        if ("".equals(scheme))
            return () -> "";
        return () -> scheme.concat("://");
    }

    private Supplier<String> schemeSupplier(URLSchemeType scheme) {
        return schemeSupplier(scheme.getStr());
    }

    private Supplier<String> schemeSupplier(URLSchemeType... schemes) {
        return mock.from(schemes).mapToString().append("://")::val;
    }

    public URLs schemes(String... schemes) {
        notEmptyOrNullValues(schemes, "schemes");
        this.schemeSupplier = schemeSupplier(schemes);
        return this;
    }

    public URLs scheme(String scheme) {
        notNull(scheme, "scheme");
        this.schemeSupplier = schemeSupplier(scheme);
        return this;
    }

    public URLs schemes(URLSchemeType... schemes) {
        notEmptyOrNullValues(schemes, "schemes");
        this.schemeSupplier = schemeSupplier(schemes);
        return this;
    }

    public URLs scheme(URLSchemeType scheme) {
        notNull(scheme, "scheme");
        this.schemeSupplier = schemeSupplier(scheme);
        return this;
    }

    private Supplier<String> defaultAuthSupplier() {
        return () -> "";
    }

    private Supplier<String> authSupplier() {
        return () -> template("#{userName}:#{passWord}")
                        .arg("userName", this.userNameSupplier.get())
                        .arg("passWord", this.passWordSupplier.get())
                        .fmt();
    }

    public URLs auth() {
        this.userNameSupplier = mock.users()
                                    .urlEncode()
                                    .supplier();
        this.passWordSupplier = mock.passwords()
                                    .type(MEDIUM)
                                    .urlEncode()
                                    .supplier();
        this.authSupplier = authSupplier();
        return this;
    }

    private Supplier<String> defaultHostSupplier() {
        return () -> {
            List<Pair<DictType, DictType>> comboList =
                    mock.from(HostNameType.class).val().getDictCombos();
            Pair<DictType, DictType> combo =
                    mock.from(comboList).val();
            String result =
                    mock.dicts().type(combo.getFirst()).noSpecialChars().format(LOWER_CASE).val() +
                    mock.dicts().type(combo.getSecond()).noSpecialChars().format(LOWER_CASE).val();
            if (www) { result = "www.".concat(result); }
            return result;
        };
    }

    private Supplier<String> hostSupplier(String... hosts) {
        return mock.fromStrings(hosts).prepend(www ? "www." : "")::val;
    }

    private Supplier<String> hostSupplier(String host) {
        return () -> www ? "www.".concat(host) : host;
    }

    private Supplier<String> hostSupplier(HostNameType... types) {
        HostNameType type = mock.from(types).val();
        return hostSupplier(type);
    }

    private Supplier<String> hostSupplier(HostNameType hostNameType) {
       return () -> {
            List<Pair<DictType, DictType>> comboList = hostNameType.getDictCombos();
            Pair<DictType, DictType> combo = mock.from(comboList).val();
            String result1 = mock.dicts().type(combo.getFirst()).noSpecialChars().val();
            String result2 = mock.dicts().type(combo.getSecond()).noSpecialChars().val();
            String result = result1 + result2;
            if (www) result = "www.".concat(result);
            return result;
        };
    }

    public URLs hosts(String... hosts) {
        notEmptyOrNullValues(hosts, "hosts");
        this.hostSupplier = hostSupplier(hosts);
        return this;
    }

    public URLs host(String host) {
        notEmpty(host, "host");
        this.hostSupplier = hostSupplier(host);
        return this;
    }

    public URLs hosts(HostNameType... hostNameTypes) {
        notEmptyOrNullValues(hostNameTypes, "hostNameTypes");
        this.hostSupplier = hostSupplier(hostNameTypes);
        return this;
    }

    public URLs host(HostNameType hostNameType) {
        notNull(hostNameType, "hostNameType");
        this.hostSupplier = hostSupplier(hostNameType);
        return this;
    }

    private Supplier<String> defaultDomainSupplier() {
        return domainSupplier(POPULAR);
    }

    private Supplier<String> domainSupplier(DomainSuffixType... types) {
        return mock.domains().types(types).prepend(".")::val;
    }

    private Supplier<String> domainSupplier(DomainSuffixType type) {
        return mock.domains().types(type).prepend(".")::val;
    }

    private Supplier<String> domainSupplier(String... domains) {
        return mock.fromStrings(domains).prepend(".")::val;
    }

    private Supplier<String> domainSupplier(String domain) {
        return () -> "." + domain;
    }

    public URLs domains(DomainSuffixType... types) {
        notEmptyOrNullValues(types, "types");
        this.domainSupplier = domainSupplier(types);
        return this;
    }

    public URLs domain(DomainSuffixType type) {
        notNull(type, "types");
        this.domainSupplier = domainSupplier(type);
        return this;
    }

    public URLs domains(String... domains) {
        notEmptyOrNullValues(domains, "domains");
        this.domainSupplier = domainSupplier(domains);
        return this;
    }

    public URLs domain(String domain) {
        notEmpty(domain, "domain");
        this.domainSupplier = domainSupplier(domain);
        return this;
    }

    private Supplier<String> defaultPortSupplier() {
        return () -> "";
    }

    private Supplier<String> portsSupplier() {
        return portSupplier(COMMON_HTTP_PORTS);
    }

    private Supplier<String> portSupplier(Integer... array) {
        return mock.fromInts(array).mapToString().prepend(":")::val;
    }

    private Supplier<String> portSupplier(Integer port) {
        return () -> ":" + port;
    }

    public URLs ports() {
        this.portSupplier = portsSupplier();
        return this;
    }

    public URLs ports(Integer... array) {
        notEmptyOrNullValues(array, "array");
        this.portSupplier = portSupplier(array);
        return this;
    }

    public URLs port(Integer port) {
        notNull(port, "port");
        this.portSupplier = portSupplier(port);
        return this;
    }

    public URLs www(boolean hasWWW) {
        this.www = hasWWW;
        return this;
    }

    @Override
    public Supplier<String> supplier() {
        return () -> {
            String scheme = schemeSupplier.get();
            String auth = authSupplier.get();
            String host = hostSupplier.get();
            String domain = domainSupplier.get();
            String port = portSupplier.get();
            String paths = "";
            return urlFormat(scheme, auth, host, domain, port, paths);
        };
    }
}
