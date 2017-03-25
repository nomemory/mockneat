package net.andreinc.mockneat;

/*
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.interfaces.*;
import net.andreinc.mockneat.types.enums.RandomType;
import net.andreinc.mockneat.unit.address.Countries;
import net.andreinc.mockneat.unit.companies.Departments;
import net.andreinc.mockneat.unit.financial.CVVS;
import net.andreinc.mockneat.unit.financial.CreditCards;
import net.andreinc.mockneat.unit.financial.Currencies;
import net.andreinc.mockneat.unit.financial.Money;
import net.andreinc.mockneat.unit.hashes.Hashes;
import net.andreinc.mockneat.unit.id.UUIDs;
import net.andreinc.mockneat.unit.misc.SSCs;
import net.andreinc.mockneat.unit.networking.*;
import net.andreinc.mockneat.unit.objects.Constructor;
import net.andreinc.mockneat.unit.objects.Factory;
import net.andreinc.mockneat.unit.objects.Probabilities;
import net.andreinc.mockneat.unit.objects.Reflect;
import net.andreinc.mockneat.unit.seq.IntSeq;
import net.andreinc.mockneat.unit.seq.LongSeq;
import net.andreinc.mockneat.unit.text.*;
import net.andreinc.mockneat.unit.time.Days;
import net.andreinc.mockneat.unit.time.LocalDates;
import net.andreinc.mockneat.unit.time.Months;
import net.andreinc.mockneat.unit.types.*;
import net.andreinc.mockneat.unit.user.*;
import net.andreinc.mockneat.utils.ValidationUtils;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

@SuppressWarnings("unchecked")
public class MockNeat {

    private static final MockNeat THREAD_LOCAL = new MockNeat(RandomType.THREAD_LOCAL);
    private static final MockNeat SECURE = new MockNeat(RandomType.SECURE);
    private static final MockNeat OLD = new MockNeat(RandomType.OLD);

    private final Random random;

    private final Bools rBools;
    private final Countries rCountries;
    private final CreditCards rCCS;
    private final Chars rChars;
    private final Currencies rCurrencies;
    private final CVVS rCVVS;
    private final Days rDays;
    private final Departments rDepartments;
    private final Dicts rDicts;
    private final Domains rDomains;
    private final Doubles rDoubles;
    private final Emails rEmails;
    private final FromFiles rFiles;
    private final Floats rFloats;
    private final Genders rGenders;
    private final Hashes rHashes;
    private final Ints rInts;
    private final IPv4s rIPv4s;
    private final IPv6s rIPv6s;
    private final LocalDates rLocalDates;
    private final Longs rLongs;
    private final Macs rMacs;
    private final Markovs rMarkovs;
    private final Money rMoney;
    private final Months rMonths;
    private final Names rNames;
    private final Passwords rPasswords;
    private final SSCs rSSCs;
    private final UUIDs rUUIDs;
    private final Users rUsers;

    public MockNeat(RandomType randomTypeType) {
        this.random = randomTypeType.getRandom();

        this.rChars = new Chars(this);
        this.rBools = new Bools(this);
        this.rCountries = new Countries(this);
        this.rCCS = new CreditCards(this);
        this.rCurrencies = new Currencies(this);
        this.rCVVS = new CVVS(this);
        this.rDays = new Days(this);
        this.rDepartments = new Departments(this);
        this.rDomains = new Domains(this);
        this.rDicts = new Dicts(this);
        this.rDoubles = new Doubles(this);
        this.rEmails = new Emails(this);
        this.rFiles = new FromFiles(this);
        this.rFloats = new Floats(this);
        this.rGenders = new Genders(this);
        this.rHashes = new Hashes(this);
        this.rInts = new Ints(this);
        this.rIPv4s = new IPv4s(this);
        this.rIPv6s = new IPv6s(this);
        this.rLocalDates = new LocalDates(this);
        this.rLongs = new Longs(this);
        this.rMacs = new Macs(this);
        this.rMarkovs = new Markovs(this);
        this.rMoney = new Money(this);
        this.rMonths = new Months(this);
        this.rNames = new Names(this);
        this.rPasswords = new Passwords(this);
        this.rSSCs = new SSCs(this);
        this.rUUIDs = new UUIDs();
        this.rUsers = new Users(this);
    }

    protected MockNeat() {
        this(RandomType.THREAD_LOCAL);
    }

    public MockNeat(RandomType randomTypeType, Long seed) {
        this(randomTypeType);
        random.setSeed(seed);
    }

    public static MockNeat threadLocal() { return THREAD_LOCAL; }
    public static MockNeat secure() { return SECURE; }
    public static MockNeat old() { return OLD; }

    public Bools bools() { return this.rBools; }

    public Chars chars() { return this.rChars; }

    public CreditCards creditCards() {
        return this.rCCS;
    }

    public <T> Constructor<T> constructor(Class<T> cls) { return new Constructor<>(cls); }

    public Countries countries() {
        return this.rCountries;
    }

    public Currencies currencies() { return this.rCurrencies; }

    public CVVS cvvs() { return this.rCVVS; }

    public Dicts dicts() {
        return this.rDicts;
    }

    public Days days() { return this.rDays; }

    public Departments departments() { return this.rDepartments; }

    public Domains domains() { return this.rDomains;}

    public Doubles doubles() {
        return this.rDoubles;
    }

    public Emails emails() { return this.rEmails; }

    public <T, FT> Factory<T, FT> factory(Class<T> targetCls, Class<FT> factoryCls) {
        return new Factory<>(targetCls, factoryCls);
    }

    public FromFiles files() { return this.rFiles; }

    public Floats floats() { return this.rFloats; }

    public Formatter fmt(String fmt) { return Formatter.formatter(fmt); }

    public Genders genders() { return this.rGenders; }

    public Hashes hashes() { return this.rHashes; }

    public Ints ints() { return this.rInts; }

    public IntSeq intSeq() { return new IntSeq(); }

    public IPv4s ipv4s() { return this.rIPv4s; }

    public IPv6s iPv6s() { return this.rIPv6s; }

    public LocalDates localDates() { return this.rLocalDates; }

    public Longs longs() { return this.rLongs; }

    public LongSeq longSeq() { return new LongSeq(); }

    public Macs macs() { return this.rMacs; }

    public Markovs markovs() { return this.rMarkovs; }

    public Months months() { return this.rMonths; }

    public Money money() { return this.rMoney; }

    public Names names() { return this.rNames; }

    public Passwords passwords() { return this.rPasswords; }

    public <T> Probabilities<T> probabilites(Class<T> cls) { return new Probabilities<T>(this, cls); }

    public <T> Reflect<T> reflect(Class<T> cls) { return new Reflect<>(cls);}

    public Strings strings() { return new Strings(this); }

    public SSCs sccs() { return this.rSSCs; }

    public URLs urls() { return new URLs(this); }

    public UUIDs uuids() { return this.rUUIDs; }

    public Users users() { return this.rUsers; }

    public java.util.Random getRandom() {
        return random;
    }

    public <T> MockUnit<T> from(List<T> alphabet) {
        notEmpty(alphabet, ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "alphabet");
        Supplier<T> supp = () -> {
            int idx = getRandom().nextInt(alphabet.size());
            return alphabet.get(idx);
        };
        return () -> supp;
    }

    public <T> MockUnit<T> from(T[] alphabet) {
        notEmpty(alphabet, ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "alphabet");
        Supplier<T> supp = () -> {
            int idx = getRandom().nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }

    public <T extends Enum<?>> MockUnit<T> from(Class<T> enumClass) {
        notNull(enumClass, ValidationUtils.INPUT_PARAMETER_NOT_NULL, "enumClass");
        T[] arr = enumClass.getEnumConstants();
        return from(arr);
    }

    public <T> MockUnit<T> fromKeys(Map<T, ?> map) {
        notEmpty(map, ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "map");
        Supplier<T> supp = () -> {
            T[] keys = (T[]) map.keySet().toArray();
            int idx = getRandom().nextInt(keys.length);
            return keys[idx];
        };
        return () -> supp;
    }

    public <T> MockUnit<T> fromValues(Map<?, T> map) {
        notEmpty(map, ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "map");
        Supplier<T> supp = () -> {
            T[] values = (T[]) map.values().toArray();
            int idx = getRandom().nextInt(values.length);
            return values[idx];
        };
        return () -> supp;
    }

    public MockUnitInt fromInts(Integer... alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitInt fromInts(int... alphabet) {
        return () -> this.ints().from(alphabet)::val;
    }

    public MockUnitInt fromInts(List<Integer> alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitInt fromIntsValues(Map<?, Integer> map) {
        return () -> fromValues(map)::val;
    }

    public MockUnitInt fromIntsKeys(Map<Integer, ?> map) {
        return () -> fromKeys(map)::val;
    }

    public MockUnitDouble fromDoubles(Double... alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitDouble fromDoubles(double... alphabet) {
        return () -> this.doubles().from(alphabet)::val;
    }

    public MockUnitDouble fromDoubles(List<Double> alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitDouble fromDoublesValues(Map<?, Double> map) {
        return () -> fromValues(map)::val;
    }

    public MockUnitDouble fromDoublesKeys(Map<Double, ?> map) {
        return () -> fromKeys(map)::val;
    }

    public MockUnitLong fromLongs(Long... alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitLong fromLongs(long... alphabet) {
        return () -> this.longs().from(alphabet)::val;
    }

    public MockUnitLong fromLongs(List<Long> alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitLong fromLongsValues(Map<?, Long> map) {
        return () -> fromValues(map)::val;
    }

    public MockUnitLong fromLongsKeys(Map<Long, ?> map) {
        return () -> fromKeys(map)::val;
    }

    public MockUnitString fromStrings(String[] alphabet) {
        return () ->  () -> from(alphabet).val();
    }

    public MockUnitString fromStrings(List<String> alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitString fromStringsValues(Map<?, String> map) {
        return () -> fromValues(map)::val;
    }

    public MockUnitString fromStringsKeys(Map<String, ?> map) {
        return () -> fromKeys(map)::val;
    }
}
