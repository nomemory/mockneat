package com.mockneat.mock.unit.networking;

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.interfaces.MockUnitString;
import com.mockneat.types.Pair;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.DomainSuffixType;
import com.mockneat.types.enums.HostNameType;
import com.mockneat.types.enums.URLSchemeType;

import java.util.List;
import java.util.function.Supplier;

import static com.mockneat.mock.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.mock.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY;
import static com.mockneat.types.enums.DomainSuffixType.POPULAR;
import static com.mockneat.types.enums.PassStrengthType.MEDIUM;
import static com.mockneat.types.enums.URLSchemeType.HTTP;
import static java.util.stream.IntStream.range;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class URLs implements MockUnitString {

    protected static final Integer[] COMMON_HTTP_PORTS = {
            80, 1311, 2480, 4567, 4711, 4712, 5104, 5800, 5988, 5989,
            7000, 7001, 7002, 8008, 8080, 8088, 8280, 8281, 8530, 8531,
            8887, 8888, 9080, 9443, 9981, 9982, 11371, 16080, 18091,
            18092
    };

    // <schemes://><user:password@><host><.domain><:port></.../...>

    private MockNeat mock;

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

    protected void initializeSuppliers() {
        this.schemeSupplier = defaultSchemesSupplier();
        this.authSupplier = defaultAuthSupplier();
        this.hostSupplier = defaultHostSupplier();
        this.domainSupplier = defaultDomainSupplier();
        this.portSupplier = defaultPortSupplier();
    }

    protected Supplier<String> defaultSchemesSupplier() {
        return schemeSupplier(HTTP);
    }

    protected Supplier<String> schemeSupplier(String... schemes) {
        return mock.fromStrings(schemes).append("://")::val;
    }

    protected Supplier<String> schemeSupplier(String scheme) {
        if ("".equals(scheme))
            return () -> "";
        return () -> scheme.concat("://");
    }

    protected Supplier<String> schemeSupplier(URLSchemeType scheme) {
        return schemeSupplier(scheme.getStr());
    }

    protected Supplier<String> schemeSupplier(URLSchemeType... schemes) {
        return mock.from(schemes).mapToString().append("://")::val;
    }

    public URLs schemes(String... schemes) {
        notEmpty(schemes, INPUT_PARAMETER_NOT_NULL, "schemes");
        range(0, schemes.length).forEach(i ->
                notEmpty(schemes[i], INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "schemes[" + i + "]"));
        this.schemeSupplier = schemeSupplier(schemes);
        return this;
    }

    public URLs scheme(String scheme) {
        notNull(scheme, INPUT_PARAMETER_NOT_NULL, "scheme");
        this.schemeSupplier = schemeSupplier(scheme);
        return this;
    }

    public URLs schemes(URLSchemeType... schemes) {
        notEmpty(schemes, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "schemes");
        range(0, schemes.length).forEach(i ->
            notNull(schemes[i], INPUT_PARAMETER_NOT_NULL, "schemes[" + i + "]"));
        this.schemeSupplier = schemeSupplier(schemes);
        return this;
    }

    public URLs scheme(URLSchemeType scheme) {
        notNull(scheme, INPUT_PARAMETER_NOT_NULL, "scheme");
        this.schemeSupplier = schemeSupplier(scheme);
        return this;
    }

    protected Supplier<String> defaultAuthSupplier() {
        return () -> "";
    }

    protected Supplier<String> authSupplier() {
        return () -> String.format("%s:%s@",
                                    this.userNameSupplier.get(),
                                    this.passWordSupplier.get());
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

    protected Supplier<String> defaultHostSupplier() {
        return () -> {
            List<Pair<DictType, DictType>> comboList =
                    mock.from(HostNameType.class).val().getDictCombos();
            Pair<DictType, DictType> combo =
                    mock.from(comboList).val();
            String result =
                    mock.dicts().type(combo.getFirst()).noSpecialChars().val() +
                    mock.dicts().type(combo.getSecond()).noSpecialChars().val();
            if (www) { result = "www.".concat(result); }
            return result;
        };
    }

    protected Supplier<String> hostSupplier(String... hosts) {
        return mock.fromStrings(hosts).prepend(www ? "www." : "")::val;
    }

    protected Supplier<String> hostSupplier(String host) {
        return () -> www ? "www.".concat(host) : host;
    }

    protected Supplier<String> hostSupplier(HostNameType... types) {
        HostNameType type = mock.from(types).val();
        return hostSupplier(type);
    }

    protected Supplier<String> hostSupplier(HostNameType hostNameType) {
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
        notEmpty(hosts, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "hosts");
        range(0, hosts.length).forEach(i ->
                        notEmpty(hosts[i], INPUT_PARAMETER_NOT_NULL_OR_EMPTY,
                                "hosts[" + i + "]"));
        this.hostSupplier = hostSupplier(hosts);
        return this;
    }

    public URLs host(String host) {
        notEmpty(host, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "host");
        this.hostSupplier = hostSupplier(host);
        return this;
    }

    public URLs hosts(HostNameType... hostNameTypes) {
        notEmpty(hostNameTypes, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "hostNameTypes");
        range(0, hostNameTypes.length).forEach(i ->
                notNull(hostNameTypes[i], INPUT_PARAMETER_NOT_NULL,  "hostNameTypes[" + i + "]"));
        this.hostSupplier = hostSupplier(hostNameTypes);
        return this;
    }

    public URLs host(HostNameType hostNameType) {
        notNull(hostNameType, INPUT_PARAMETER_NOT_NULL, "hostNameType");
        this.hostSupplier = hostSupplier(hostNameType);
        return this;
    }

    protected Supplier<String> defaultDomainSupplier() {
        return domainSupplier(POPULAR);
    }

    protected Supplier<String> domainSupplier(DomainSuffixType... types) {
        return mock.domains().types(types).prepend(".")::val;
    }

    protected Supplier<String> domainSupplier(DomainSuffixType type) {
        return mock.domains().types(type).prepend(".")::val;
    }

    protected Supplier<String> domainSupplier(String... domains) {
        return mock.fromStrings(domains).prepend(".")::val;
    }

    protected Supplier<String> domainSupplier(String domain) {
        return () -> "." + domain;
    }

    public URLs domains(DomainSuffixType... types) {
        notEmpty(types, INPUT_PARAMETER_NOT_NULL, "types");
        range(0, types.length).forEach(i ->
                notNull(types[i], INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "types[" + i + "]"));
        this.domainSupplier = domainSupplier(types);
        return this;
    }

    public URLs domain(DomainSuffixType type) {
        notNull(type, INPUT_PARAMETER_NOT_NULL, "types");
        this.domainSupplier = domainSupplier(type);
        return this;
    }

    public URLs domains(String... domains) {
        notEmpty(domains, INPUT_PARAMETER_NOT_NULL, "domains");
        range(0, domains.length).forEach(i ->
                notEmpty(domains[i], INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "domains[" + i + "]"));
        this.domainSupplier = domainSupplier(domains);
        return this;
    }

    public URLs domain(String domain) {
        notEmpty(domain, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "domain");
        this.domainSupplier = domainSupplier(domain);
        return this;
    }

    protected Supplier<String> defaultPortSupplier() {
        return () -> "";
    }

    protected Supplier<String> portsSupplier() {
        return portSupplier(COMMON_HTTP_PORTS);
    }

    protected Supplier<String> portSupplier(Integer... array) {
        return mock.fromInts(array).mapToString().prepend(":")::val;
    }

    protected Supplier<String> portSupplier(Integer port) {
        return () -> ":" + port;
    }

    public URLs ports() {
        this.portSupplier = portsSupplier();
        return this;
    }

    public URLs ports(Integer... array) {
        notEmpty(array, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "array");
        range(0, array.length).forEach(i ->
            notNull(array[i], INPUT_PARAMETER_NOT_NULL, "array[" + i + "]"));
        this.portSupplier = portSupplier(array);
        return this;
    }

    public URLs port(Integer port) {
        notNull(port, INPUT_PARAMETER_NOT_NULL, "port");
        this.portSupplier = portSupplier(port);
        return this;
    }

    public URLs www(boolean hasWWW) {
        this.www = hasWWW;
        return this;
    }

    protected String emptyIfNullSupplier(Supplier<String> supplier) {
        if (null==supplier)
            return "";
        else
            return supplier.get();
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
