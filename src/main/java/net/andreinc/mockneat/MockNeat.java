package net.andreinc.mockneat;

/**
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

import net.andreinc.mockneat.abstraction.*;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.types.enums.RandomType;
import net.andreinc.mockneat.unit.address.Cities;
import net.andreinc.mockneat.unit.address.Countries;
import net.andreinc.mockneat.unit.address.UsStates;
import net.andreinc.mockneat.unit.companies.Departments;
import net.andreinc.mockneat.unit.financial.*;
import net.andreinc.mockneat.unit.hashes.Hashes;
import net.andreinc.mockneat.unit.id.UUIDs;
import net.andreinc.mockneat.unit.misc.ISSNS;
import net.andreinc.mockneat.unit.misc.Mimes;
import net.andreinc.mockneat.unit.misc.SSCs;
import net.andreinc.mockneat.unit.networking.*;
import net.andreinc.mockneat.unit.objects.*;
import net.andreinc.mockneat.unit.regex.Regex;
import net.andreinc.mockneat.unit.seq.IntSeq;
import net.andreinc.mockneat.unit.seq.LongSeq;
import net.andreinc.mockneat.unit.seq.Seq;
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

import static net.andreinc.mockneat.utils.ValidationUtils.notNull;
import static org.apache.commons.lang3.Validate.notEmpty;

@SuppressWarnings("unchecked")
public class MockNeat {

    private static final MockNeat THREAD_LOCAL = new MockNeat(RandomType.THREAD_LOCAL);
    private static final MockNeat SECURE = new MockNeat(RandomType.SECURE);
    private static final MockNeat OLD = new MockNeat(RandomType.OLD);

    private final Random random;

    private final Bools rBools;
    private final Cities rCities;
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
    private final IBANs rIbans;
    private final Ints rInts;
    private final ISSNS rISSNS;
    private final IPv4s rIPv4s;
    private final IPv6s rIPv6s;
    private final LocalDates rLocalDates;
    private final Longs rLongs;
    private final Macs rMacs;
    private final Markovs rMarkovs;
    private final Mimes rMimes;
    private final Money rMoney;
    private final Months rMonths;
    private final Names rNames;
    private final NaughtyStrings rNaughtyStrings;
    private final Passwords rPasswords;
    private final Shufflers rShufflers;
    private final SSCs rSSCs;
    private final UUIDs rUUIDs;
    private final Users rUsers;
    private final UsStates rUsStates;
    private final Words rWords;

    public MockNeat(RandomType randomTypeType) {
        this.random = randomTypeType.getRandom();

        this.rChars = new Chars(this);
        this.rBools = new Bools(this);
        this.rCountries = new Countries(this);
        this.rCCS = new CreditCards(this);
        this.rCities = new Cities(this);
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
        this.rIbans = new IBANs(this);
        this.rInts = new Ints(this);
        this.rIPv4s = new IPv4s(this);
        this.rIPv6s = new IPv6s(this);
        this.rISSNS = new ISSNS(this);
        this.rLocalDates = new LocalDates(this);
        this.rLongs = new Longs(this);
        this.rMacs = new Macs(this);
        this.rMarkovs = new Markovs(this);
        this.rMimes = new Mimes(this);
        this.rMoney = new Money(this);
        this.rMonths = new Months(this);
        this.rNames = new Names(this);
        this.rNaughtyStrings = new NaughtyStrings(this);
        this.rPasswords = new Passwords(this);
        this.rShufflers = new Shufflers(this);
        this.rSSCs = new SSCs(this);
        this.rUUIDs = new UUIDs();
        this.rUsers = new Users(this);
        this.rUsStates = new UsStates(this);
        this.rWords = new Words(this);
    }

    protected MockNeat() {
        this(RandomType.THREAD_LOCAL);
    }

    public MockNeat(RandomType randomTypeType, Long seed) {
        this(randomTypeType);
        random.setSeed(seed);
    }

    /**
     * <p>Re-use the default instance of a {@code MockNeat} class that internally uses a {@code ThreadLocalRandom} implementation from the Java Standard Library.</p>
     *
     * <p><em>Note:</em> This is the recommended way to obtain a new MockUnit.</p>
     *
     * @return An already instantiated {@code MockNeat} instance that can be reused.
     */
    public static MockNeat threadLocal() { return THREAD_LOCAL; }

    /**
     * <p>Re-use the default instance of a {@code MockNeat} class that internally uses a SecureRandom implementation from the Java Standard Library.</p>
     *
     * @return An already instantiated {@code MockNeat} instance that can be reused.
     */
    public static MockNeat secure() { return SECURE; }

    /**
     * <p>Re-use the default instance of a {@code MockNeat} class that internally uses the old Random class from the Java Standard Library.</p>
     *
     * @return An already instantiated {@code MockNeat} instance that can be reused.
     */
    public static MockNeat old() { return OLD; }

    /**
     * <p>Returns a {@code Bools} object that can be used to generate arbitrary {@code Boolean} values.</p>
     *
     * @return A re-usable {@code Bools} instance. The class implements {@code MockUnit<Boolean>}.
     */
    public Bools bools() { return this.rBools; }

    /**
     * <p>Returns a {@code Chars} object that can be used to generate arbitrary {@code Character} values.</p>
     *
     * <p><em>Note:</em> Without any additional constraint, the {@code Chars} object will generate alphanumeric characters.</p>
     *
     * @return A re-usable {@code Chars} instance. The class implements {@code MockUnit<Character>}.
     */
    public Chars chars() { return this.rChars; }

    /**
     * <p>Returns a {@code Cities} object that can be used to generate arbitrary city names from around the world.</p>
     *
     * @return A re-usable {@code Citites} object.
     */
    public Cities cities() { return this.rCities; }

    /**
     * <p>Returns a {@code CreditCards} object that can be used to generate valid Credit Card numbers.</p>
     *
     * <p><em>Note: </em>By default it generates AMERICAN_EXPRESS valid credit card numbers.</p>
     *
     * <p><em>Note: </em> Credit card numbers are financial information. The values are generated at random so don't use them in real-life scenarios.</p>
     *
     * @return A re-usable {@code CreditCards} instance. The class implements {@code MockUnitString}.
     */
    public CreditCards creditCards() {
        return this.rCCS;
    }

    /**
     * <p>Returns a new {@code Constructor} object.</p>
     *
     * <p>This method can be used to generate {@code MockUnit<T>} from a Java Bean {@code <T>} by accessing it's constructor and supply it with arbitrary input.</p>
     *
     * @param cls The class for type {@code <T>}. (Eg.: Student.class)
     * @param <T> The type of {@code MockUnit<T>}. This is the wrapped type.
     * @return A new {@code Constructor} object. The {@code Constructor} class implements {@code MockUnit<T>}.
     */
    public <T> Constructor<T> constructor(Class<T> cls) { return new Constructor<>(cls); }

    /**
     * <p>Returns a {@code Countries} object that can be used to generate country names or country ISO2 codes.</p>
     *
     * @return A re-usable {@code Countries} object.
     */
    public Countries countries() {
        return this.rCountries;
    }

    /**
     * <p>Returns a {@code Currencies} object that can be used to generate currencies-related information: </p>
     *
     * <ul>
     *     <li>Forex pairs. (Eg.: "USD/CAD")</li>
     *     <li>Currency codes. (Eg.: "USD", "GBP")</li>
     *     <li>Currency symbols. (Eg.: "$", "¥")</li>
     *     <li>Currency names. (Eg.: "Boliviano", "Dollar")</li>
     * </ul>
     *
     * @return A re-usable {@code Currencies} object.
     */
    public Currencies currencies() { return this.rCurrencies; }

    /**
     * <p>Returns a {@code CVVS} object that can be used to generate credit card cvv codes.</p>
     *
     * <p><em>Note: </em> By default the values generated are 3-digits CVV codes.</p>
     *
     * @return A re-usable {@code CVVS} object. The {@code CVVS} class implements the {@code MockUnitString} interface.
     */
    public CVVS cvvs() { return this.rCVVS; }

    /**
     * <p>Returns a {@code Dicts} object that can be used to generate data from the library existing dictionaries.</p>
     *
     * <p>A dictionary is an {@code enum} mapping a text file containing a set of data</p>
     *
     * <p>The file contents are loaded in memory after the first call.</p>
     *
     * <p>Check {@link DictType} to see the comprehensive list.</p>
     *
     * @return A re-usable {@code Dicts} object.
     */
    public Dicts dicts() {
        return this.rDicts;
    }

    /**
     * <p>Returns a {@code Days} object that can be used to generate a random {@code java.time.DayOfWeek} object.</p>
     *
     * <p><em>Note: </em> By default the {@code Days} object returns a random day of the week.</p>
     *
     * @return A re-usable {@code Days} object. The {@code Days} class implements {@code MockUnitDays} interface.
     */
    public Days days() { return this.rDays; }

    /**
     * <p>Returns a {@code Departments} object that can be used to generate arbitrary names representing department names from a company.</p>
     *
     * @return A re-usable {@code Departments} object. The {@code Departments} class is implementing {@code MockUnitString}.
     */
    public Departments departments() { return this.rDepartments; }

    /**
     * <p>Returns a {@code Domains} object</p> that can be used to generate domain names. (eg.: "www", "info")
     *
     * <p><em>Note: </em> By default "popular" domains will be generated (like: "com", "org", "net", "edu", "gov", "info", "io")</p>
     *
     * @return A re-usable {@code Domains} object. The {@code Domains} class implements {@code MockUnitString}
     */
    public Domains domains() { return this.rDomains;}

    /**
     * <p>Returns a {@code Doubles} object that can be used to generate arbitrary {@code double} values.</p>
     *
     * <p><em>Note:</em> By default the {@code Doubles} object returns a random double in the [0.0, 1.0) interval. </p>
     *
     * @return A re-usable {@code Doubles} object. The {@code Doubles} class implements {@code MockUnitDouble}.
     */
    public Doubles doubles() {
        return this.rDoubles;
    }

    /**
     * <p>Returns an {@code Email} object that can be used to generate arbitrary email address.</p>
     *
     * @return A re-usable {@code Emails} object. The {@code Emails} class implements {@code MockUnitString}.
     */
    public Emails emails() { return this.rEmails; }

    /**
     * <p>Returns a new {@code Factory} object that can be used to instantiate Java Objects by calling a static factory method and supplying it with arbitrary data.</p>
     *
     * <p>The {@code Factory} class implements {@code MockUnit<T>}.</p>
     *
     * @param targetCls The Java class we want to create instances of.
     * @param factoryCls The Java class that contains the static-factory method we want to invoke.
     * @param <T>
     * @param <FT>
     * @return
     */
    public <T, FT> Factory<T, FT> factory(Class<T> targetCls, Class<FT> factoryCls) {
        return new Factory<>(targetCls, factoryCls);
    }

    /**
     * <p>Returns a {@code FromFiles} object that can used to generate random strings from a given text file.</p>
     *
     * <p><em>Note:</em> The file is loaded in memory. For the moment there is no functionality to "unload" it.</p>
     *
     * @return A re-usable {@code FromFiles} object.
     */
    public FromFiles files() { return this.rFiles; }

    /**
     * <p>Returns a {@code Floats} object than can be used to generate random float numbers.</p>
     *
     * <p><em>Note:</em> By defult it generates float numbers in the [0.0f, 1.0f) range.</p>
     * @return A re-usable {@code Floats} object. The {@code Floats} class implements {@code MockUnitFloats}.
     */
    public Floats floats() { return this.rFloats; }

    /**
     * <p>Returns a {@code Formatter} object than can be used to generate arbitrary patterns based on a given format.</p>
     *
     * <p>Eg.: m.fmt("#{oneInt} + #{aLetter}").params("oneInt", m.ints(), "aLetter", m.chars().letters()).val()</p>
     *
     * @param fmt The template of the desired pattern.
     * @return A <strong>new</strong> {@code Formatter} object. The {@code Formatter} class implements {@code MockUnitString}.
     */
    public Formatter fmt(String fmt) { return Formatter.formatter(fmt); }

    /**
     * <p>Returns a {@code Genders} object that can be used to generate "gender" data. (Eg.: "Male", "Female")</p>
     *
     * @return A re-usable {@code Genders} object. The {@code Genders} class implements {@code MockUnitString}.
     */
    public Genders genders() { return this.rGenders; }

    /**
     * <p>Returns {@code Hashes} object that can be used to generate "hash" strings.</p>
     *
     * <p>The values are actually computed from arbitrary strings of length 128.</p>
     *
     * @return A re-usable {@code Hashes} object. The {@code Hashes} class implements {@code MockUnitString}.
     */
    public Hashes hashes() { return this.rHashes; }

    /**
     * <p>Returns a {@code IBANs} object that can be used to generate valid IBANs codes.</p>
     *
     * @return A re-usable {@code IBANs} object. The {@code IBANs} class implements {@code MockUnitString}.
     */
    public IBANs ibans() { return this.rIbans; }

    /**
     * <p>Returns a {@code Ints} object that can be used to generate arbitrary {@code Integer} numbers.</p>
     *
     * <p>Internally the method uses the {@code Random::nextInt} implementation.</p>
     *
     * @return A re-usable {@code Ints} object. The {@code Ints} class implements {@code MockUnitInt}.
     */
    public Ints ints() { return this.rInts; }

    /**
     * <p>Returns a {@code IntSeq} object that can be used to generate arbitrary {@code Integer} numbers in a sequence.</p>
     *
     * @return A re-usable {@code IntSeq} object. The {@code IntSeq} class implements {@code MockUnitInt}.
     */
    public IntSeq intSeq() { return new IntSeq(); }

    public IPv4s ipv4s() { return this.rIPv4s; }

    public IPv6s iPv6s() { return this.rIPv6s; }

    public ISSNS issns() { return this.rISSNS; }

    public LocalDates localDates() { return this.rLocalDates; }

    public Longs longs() { return this.rLongs; }

    public LongSeq longSeq() { return new LongSeq(); }

    public Macs macs() { return this.rMacs; }

    public Markovs markovs() { return this.rMarkovs; }

    public Mimes mimes() { return this.rMimes; }

    public Months months() { return this.rMonths; }

    public Money money() { return this.rMoney; }

    public Names names() { return this.rNames; }

    public NaughtyStrings naughtyStrings() { return this.rNaughtyStrings; }

    public Passwords passwords() { return this.rPasswords; }

    public Regex regex(String regex) { return new Regex(regex); }

    public <T> Probabilities<T> probabilites(Class<T> cls) { return new Probabilities<T>(this, cls); }

    public <T> Reflect<T> reflect(Class<T> cls) { return new Reflect<>(this, cls);}

    public <T> Seq<T> seq(Iterable<T> iterable) { return Seq.fromIterable(iterable); }

    public Seq<String> seq(DictType dictType) { return Seq.fromDict(dictType); }

    public Shufflers shufflers() { return rShufflers; }

    public Strings strings() { return new Strings(this); }

    public SSCs sccs() { return this.rSSCs; }

    public URLs urls() { return new URLs(this); }

    public UUIDs uuids() { return this.rUUIDs; }

    public Users users() { return this.rUsers; }

    public UsStates usStates() { return this.rUsStates; }

    public Words words() { return this.rWords; }

    public java.util.Random getRandom() {
        return random;
    }

    public <T> MockUnit<T> from(List<T> alphabet) {
        notEmpty(alphabet, "alphabet");
        Supplier<T> supp = () -> {
            int idx = getRandom().nextInt(alphabet.size());
            return alphabet.get(idx);
        };
        return () -> supp;
    }

    public <T> MockUnit<T> from(T[] alphabet) {
        notEmpty(alphabet, "alphabet");
        Supplier<T> supp = () -> {
            int idx = getRandom().nextInt(alphabet.length);
            return alphabet[idx];
        };
        return () -> supp;
    }

    public <T extends Enum<?>> MockUnit<T> from(Class<T> enumClass) {
        notNull(enumClass, "enumClass");
        T[] arr = enumClass.getEnumConstants();
        return from(arr);
    }

    public <T> MockUnit<T> fromKeys(Map<T, ?> map) {
        notEmpty(map, ValidationUtils.INPUT_PARAMETER_NOT_EMPTY_OR_NULL, "map");
        Supplier<T> supp = () -> {
            T[] keys = (T[]) map.keySet().toArray();
            int idx = getRandom().nextInt(keys.length);
            return keys[idx];
        };
        return () -> supp;
    }

    public <T> MockUnit<T> fromValues(Map<?, T> map) {
        notEmpty(map, ValidationUtils.INPUT_PARAMETER_NOT_EMPTY_OR_NULL, "map");
        Supplier<T> supp = () -> {
            T[] values = (T[]) map.values().toArray();
            int idx = getRandom().nextInt(values.length);
            return values[idx];
        };
        return () -> supp;
    }

    public MockUnitInt fromInts(Integer[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitInt fromInts(int[] alphabet) {
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

    public MockUnitDouble fromDoubles(Double[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitDouble fromDoubles(double[] alphabet) {
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

    public MockUnitLong fromLongs(Long[] alphabet) {
        return () -> from(alphabet)::val;
    }

    public MockUnitLong fromLongs(long[] alphabet) {
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
