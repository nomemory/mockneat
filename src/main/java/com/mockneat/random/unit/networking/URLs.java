package com.mockneat.random.unit.networking;

import com.mockneat.random.Rand;
import com.mockneat.random.interfaces.RandUnitString;
import com.mockneat.types.Pair;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.DomainSuffixType;
import com.mockneat.types.enums.HostNameType;

import java.util.List;
import java.util.function.Supplier;

import static com.mockneat.random.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.random.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY;
import static com.mockneat.types.enums.DomainSuffixType.POPULAR;
import static com.mockneat.types.enums.URLScheme.*;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class URLs implements RandUnitString {

    public static final Integer[] COMMON_HTTP_PORTS = {
            80, 1311, 2480, 4567, 4711, 4712, 5104, 5800, 5988, 5989,
            7000, 7001, 7002, 8008, 8080, 8088, 8280, 8281, 8530, 8531,
            8887, 8888, 9080, 9443, 9981, 9982, 11371, 16080, 18091,
            18092
    };

    // <schemes://><user:password@><host><.domain><:port></.../...>
    private static final String URL_FORMAT = "%s%s%s%s%s";

    private Rand rand;

    // Scheme
    private Supplier<String> schemeSupplier;

    // Auth
    private Supplier<String> userSupplier;
    private Supplier<String> passSupplier;

    //Host
    private Supplier<String> hostSupplier;
    private boolean www = true;

    // Domain
    private Supplier<String> domainSupplier;

    // Port
    private Supplier<String> portSupplier;

    public URLs(Rand rand) {
        this.rand = rand;
        this.initializeSuppliers();
    }

    private static final String urlFormat(String scheme,
                                          String auth,
                                          String host,
                                          String domain,
                                          String port,
                                          String paths) {
        return String.format(URL_FORMAT,
                scheme,
                auth,
                host,
                domain,
                port,
                paths);
    }

    protected void initializeSuppliers() {
        this.schemeSupplier = defaultSchemesSupplier();
        this.hostSupplier = defaultHostSupplier();
        this.domainSupplier = defaultDomainSupplier();
        this.portSupplier = defaultPortSupplier();
    }

    protected Supplier<String> defaultSchemesSupplier() {
        return schemeSupplier(HTTP);
    }

    protected Supplier<String> schemeSupplier(String... schemes) {
        return rand.fromStrings(schemes).append("://")::val;
    }

    protected Supplier<String> schemeSupplier(String scheme) {
        if (NONE.equals(scheme))
            return () -> "";
        return () -> scheme.concat("://");
    }

    public URLs schemes(String... schemes) {
        notEmpty(schemes, INPUT_PARAMETER_NOT_NULL, "schemes");
        this.schemeSupplier = schemeSupplier(schemes);
        return this;
    }

    public URLs scheme(String scheme) {
        notEmpty(scheme, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "scheme");
        this.schemeSupplier = schemeSupplier(scheme);
        return this;
    }

    protected Supplier<String> defaultHostSupplier() {
        return () -> {
            List<Pair<DictType, DictType>> comboList =
                    rand.from(HostNameType.class).val().getDictCombos();
            Pair<DictType, DictType> combo =
                    rand.from(comboList).val();
            String result = rand.dicts().type(combo.getFirst()).val() +
                    rand.dicts().type(combo.getSecond()).val();
            if (www) { result = "www.".concat(result); }
            return result;
        };
    }

    protected Supplier<String> hostSupplier(String... hosts) {
        return rand.fromStrings(hosts)::val;
    }

    protected Supplier<String> hostSupplier(String host) {
        return () -> host;
    }

    protected Supplier<String> hostSupplier(HostNameType... types) {
        HostNameType type = rand.from(types).val();
        return hostSupplier(type);
    }

    protected Supplier<String> hostSupplier(HostNameType hostNameType) {
       return () -> {
            List<Pair<DictType, DictType>> comboList = hostNameType.getDictCombos();
            Pair<DictType, DictType> combo = rand.from(comboList).val();
            String result1 = rand.dicts().type(combo.getFirst()).val();
            String result2 = rand.dicts().type(combo.getSecond()).val();
            String result = result1 + result2;
            if (www) result = "www.".concat(result);
            return result;
        };
    }

    public URLs hosts(String... hosts) {
        notEmpty(hosts, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "hosts");
        this.hostSupplier = hostSupplier(hosts);
        return this;
    }

    public URLs host(String host) {
        this.hostSupplier = hostSupplier(host);
        return this;
    }

    public URLs hosts(HostNameType... hostNameTypes) {
        notEmpty(hostNameTypes, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "hostNameTypes");
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
        return rand.domains().types(types).prepend(".")::val;
    }

    protected Supplier<String> domainSupplier(DomainSuffixType type) {
        return rand.domains().types(type).prepend(".")::val;
    }

    protected Supplier<String> domainSupplier(String... domains) {
        return rand.fromStrings(domains).prepend(".")::val;
    }

    protected Supplier<String> domainSupplier(String domain) {
        return () -> domain;
    }

    public URLs domains(DomainSuffixType... types) {
        notEmpty(types, INPUT_PARAMETER_NOT_NULL, "types");
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
        return rand.fromInts(array).mapToString().prepend(":")::val;
    }

    protected Supplier<String> portSupplier(Integer port) {
        return () -> ":" + port;
    }

    protected Supplier<String> portSupplier(List<Integer> list) {
        return rand.fromInts(list).mapToString().prepend(":")::val;
    }

    protected Supplier<String> portSupplier(Supplier<Integer> supplier) {
        return () -> ":" + supplier.get().toString();
    }

    public URLs ports() {
        this.portSupplier = portsSupplier();
        return this;
    }

    public URLs ports(Integer... array) {
        notEmpty(array, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "array");
        this.portSupplier = portSupplier(array);
        return this;
    }

    public URLs port(Integer port) {
        notNull(port, INPUT_PARAMETER_NOT_NULL, "port");
        this.portSupplier = portSupplier(port);
        return this;
    }

    public URLs ports(List<Integer> portList) {
        notEmpty(portList, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "portList");
        this.portSupplier = portSupplier(portList);
        return this;
    }

    public URLs ports(Supplier<Integer> portSupplier) {
        notNull(portSupplier, INPUT_PARAMETER_NOT_NULL, "portSupplier");
        this.portSupplier = portSupplier(portSupplier);
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
            String auth = "";
            String host = hostSupplier.get();
            String domain = domainSupplier.get();
            String port = portSupplier.get();
            String paths = "";
            return urlFormat(scheme, auth, host, domain, port, paths);
        };
    }
}
