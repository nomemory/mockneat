package com.mockneat.random;

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
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import com.mockneat.random.interfaces.*;
import com.mockneat.random.unit.address.Countries;
import com.mockneat.random.unit.financial.CCS;
import com.mockneat.random.unit.financial.CVVS;
import com.mockneat.random.unit.id.UUIDs;
import com.mockneat.random.unit.networking.Domains;
import com.mockneat.random.unit.networking.IPv4s;
import com.mockneat.random.unit.networking.Macs;
import com.mockneat.random.unit.networking.URLs;
import com.mockneat.random.unit.objects.Compose;
import com.mockneat.random.unit.text.Dicts;
import com.mockneat.random.unit.time.Days;
import com.mockneat.random.unit.time.LocalDates;
import com.mockneat.random.unit.user.Emails;
import com.mockneat.random.unit.user.Names;
import com.mockneat.random.unit.text.Markovs;
import com.mockneat.random.unit.time.Months;
import com.mockneat.random.unit.types.*;
import com.mockneat.random.unit.user.Passwords;
import com.mockneat.random.unit.user.Users;
import com.mockneat.types.Pair;
import com.mockneat.types.enums.RandType;

import java.util.*;
import java.util.function.Supplier;

import static com.mockneat.random.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static com.mockneat.random.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL_OR_EMPTY;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class Rand {

    private Random random;

    private Bools rBools;
    private Countries rCountries;
    private CCS rCCS;
    private Chars rChars;
    private CVVS rCVVS;
    private Days rDays;
    private Dicts rDicts;
    private Domains rDomains;
    private Doubles rDoubles;
    private Emails rEmails;
    private Floats rFloats;
    private Ints rInts;
    private IPv4s rIPv4s;
    private LocalDates rLocalDates;
    private Longs rLongs;
    private Macs rMacs;
    private Markovs rMarkovs;
    private Months rMonths;
    private Names rNames;
    private Passwords rPasswords;
    private UUIDs rUUIDs;
    private Users rUsers;

    protected Rand(RandType randomType) {
        this.random = randomType.getRandom();

        this.rChars = new Chars(this);
        this.rBools = new Bools(this);
        this.rCountries = new Countries(this);
        this.rCCS = new CCS(this);
        this.rCVVS = new CVVS(this);
        this.rDays = new Days(this);
        this.rDomains = new Domains(this);
        this.rDicts = new Dicts(this);
        this.rDoubles = new Doubles(this);
        this.rEmails = new Emails(this);
        this.rFloats = new Floats(this);
        this.rInts = new Ints(this);
        this.rIPv4s = new IPv4s(this);
        this.rLocalDates = new LocalDates(this);
        this.rLongs = new Longs(this);
        this.rMacs = new Macs(this);
        this.rMarkovs = new Markovs(this);
        this.rMonths = new Months(this);
        this.rNames = new Names(this);
        this.rPasswords = new Passwords(this);
        this.rUUIDs = new UUIDs();
        this.rUsers = new Users(this);
    }

    protected Rand() {
        this(RandType.THREAD_LOCAL_RANDOM);
    }

    protected Rand(RandType randomType, Long seed) {
        this(randomType);
        random.setSeed(seed);
    }

    public static Rand threadLocal() { return new Rand(RandType.THREAD_LOCAL_RANDOM); }
    public static Rand secure() { return new Rand(RandType.SECURE_RANDOM); }
    public static Rand old() { return new Rand(RandType.RANDOM); }

    public Bools bools() {
        return this.rBools;
    }

    public Countries countries() {
        return this.rCountries;
    }

    public CCS ccs() {
        return this.rCCS;
    }

    public CVVS cvvs() { return this.rCVVS; }

    public Dicts dicts() {
        return this.rDicts;
    }

    public Chars chars() { return this.rChars; }

    public Compose compose(Pair<Supplier, Class>... units) { return new Compose(units); }

    public Days days() { return this.rDays; }

    public Domains domains() { return this.rDomains;}

    public Doubles doubles() {
        return this.rDoubles;
    }

    public Emails emails() { return this.rEmails; }

    public Floats floats() { return this.rFloats; }

    public Ints ints() { return this.rInts; }

    public IPv4s ipv4s() { return this.rIPv4s; }

    public LocalDates localDates() { return this.rLocalDates; }

    public Longs longs() { return this.rLongs; }

    public Macs macs() { return this.rMacs; }

    public Markovs markovs() { return this.rMarkovs; }

    public Months months() { return this.rMonths; }

    public Names names() { return this.rNames; }

    public Passwords passwords() { return this.rPasswords; }

    public URLs urls() { return new URLs(this); }

    public UUIDs uuids() { return this.rUUIDs; }

    public Users users() { return this.rUsers; }

    public Random getRandom() {
        return random;
    }

    public <T> RandUnit<T> from(List<T> alphabet) {
        notEmpty(alphabet, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "alphabet");
        Supplier<T> supp = () -> {
            int idx = getRandom().nextInt(alphabet.size());
            return alphabet.get(idx);
        };
        return () -> supp;
    }

    public <T> RandUnit<T> from(T[] alphabet) {
        notEmpty(alphabet, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "alphabet");
        Supplier<T> supp = () -> {
            int idx = getRandom().nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }

    public <T extends Enum<?>> RandUnit<T> from(Class<T> enumClass) {
        notNull(enumClass, INPUT_PARAMETER_NOT_NULL, "enumClass");
        T[] arr = enumClass.getEnumConstants();
        return from(arr);
    }

    public <T> RandUnit<T> fromKeys(Map<T, ?> map) {
        notEmpty(map, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "map");
        Supplier<T> supp = () -> {
            T[] keys = (T[]) map.keySet().toArray();
            int idx = getRandom().nextInt(keys.length);
            return keys[idx];
        };
        return () -> supp;
    }

    public <T> RandUnit<T> fromValues(Map<?, T> map) {
        notEmpty(map, INPUT_PARAMETER_NOT_NULL_OR_EMPTY, "map");
        Supplier<T> supp = () -> {
            T[] values = (T[]) map.values().toArray();
            int idx = getRandom().nextInt(values.length);
            return values[idx];
        };
        return () -> supp;
    }

    public RandUnitInt fromInts(Integer[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitInt fromInts(int[] alphabet) {
        return () -> this.ints().from(alphabet)::val;
    }

    public RandUnitInt fromInts(List<Integer> alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitInt fromIntsValues(Map<?, Integer> map) {
        return () -> fromValues(map)::val;
    }

    public RandUnitInt fromIntsKeys(Map<Integer, ?> map) {
        return () -> fromKeys(map)::val;
    }

    public RandUnitDouble fromDoubles(Double[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitDouble fromDoubles(double[] alphabet) {
        return () -> this.doubles().from(alphabet)::val;
    }

    public RandUnitDouble fromDoubles(List<Double> alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitDouble fromDoublesValues(Map<?, Double> map) {
        return () -> fromValues(map)::val;
    }

    public RandUnitDouble fromDoublesKeys(Map<Double, ?> map) {
        return () -> fromKeys(map)::val;
    }

    public RandUnitLong fromLongs(Long[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitLong fromLongs(long[] alphabet) {
        return () -> this.longs().from(alphabet)::val;
    }

    public RandUnitLong fromLongs(List<Long> alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitLong fromLongsValues(Map<?, Long> map) {
        return () -> fromValues(map)::val;
    }

    public RandUnitLong fromLongsKeys(Map<Long, ?> map) {
        return () -> fromKeys(map)::val;
    }

    public RandUnitString fromStrings(String[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitString fromStrings(List<String> alphabet) {
        return () -> from(alphabet)::val;
    }

    public RandUnitString fromStringsValues(Map<?, String> map) {
        return () -> fromValues(map)::val;
    }

    public RandUnitString fromStringsKeys(Map<String, ?> map) {
        return () -> fromKeys(map)::val;
    }
}
