package net.andreinc.mockneat;

import net.andreinc.mockneat.abstraction.*;
import net.andreinc.mockneat.types.enums.DictType;
import net.andreinc.mockneat.types.enums.RandomType;
import net.andreinc.mockneat.unit.address.Cities;
import net.andreinc.mockneat.unit.address.Countries;
import net.andreinc.mockneat.unit.address.USStates;
import net.andreinc.mockneat.unit.celebrities.Actors;
import net.andreinc.mockneat.unit.celebrities.Actresses;
import net.andreinc.mockneat.unit.celebrities.Celebrities;
import net.andreinc.mockneat.unit.celebrities.JazzArtists;
import net.andreinc.mockneat.unit.companies.Departments;
import net.andreinc.mockneat.unit.companies.Industries;
import net.andreinc.mockneat.unit.financial.*;
import net.andreinc.mockneat.unit.hashes.Hashes;
import net.andreinc.mockneat.unit.id.UUIDs;
import net.andreinc.mockneat.unit.misc.*;
import net.andreinc.mockneat.unit.networking.*;
import net.andreinc.mockneat.unit.objects.*;
import net.andreinc.mockneat.unit.regex.Regex;
import net.andreinc.mockneat.unit.seq.IntSeq;
import net.andreinc.mockneat.unit.seq.LongSeq;
import net.andreinc.mockneat.unit.seq.Seq;
import net.andreinc.mockneat.unit.text.*;
import net.andreinc.mockneat.unit.text.Formatter;
import net.andreinc.mockneat.unit.time.Days;
import net.andreinc.mockneat.unit.time.LocalDates;
import net.andreinc.mockneat.unit.time.Months;
import net.andreinc.mockneat.unit.types.*;
import net.andreinc.mockneat.unit.user.*;

import java.util.*;
import java.util.function.Supplier;

public class MockNeat {

    private static final MockNeat THREAD_LOCAL = new MockNeat(RandomType.THREAD_LOCAL);
    private static final MockNeat SECURE = new MockNeat(RandomType.SECURE);
    private static final MockNeat OLD = new MockNeat(RandomType.OLD);

    private final Random random;

    private final Actors rActors;
    private final Actresses rActresses;
    private final Bools rBools;
    private final Cars rCars;
    private final Celebrities rCelebrities;
    private final Cities rCities;
    private final Countries rCountries;
    private final CreditCards rCCS;
    private final Chars rChars;
    private final Creatures rCreatures;
    private final Currencies rCurrencies;
    private final CVVS rCVVS;
    private final Days rDays;
    private final Departments rDepartments;
    private final Dicts rDicts;
    private final Domains rDomains;
    private final Doubles rDoubles;
    private final Emails rEmails;
    private final Froms rFrom;
    private final FromFiles rFiles;
    private final Floats rFloats;
    private final Genders rGenders;
    private final Hashes rHashes;
    private final IBANs rIbans;
    private final Industries rIndustries;
    private final Ints rInts;
    private final ISSNS rISSNS;
    private final IPv4s rIPv4s;
    private final IPv6s rIPv6s;
    private final JazzArtists rJazzArtists;
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
    private final Primes rPrimes;
    private final Shufflers rShufflers;
    private final Space rSpace;
    private final SSCs rSSCs;
    private final UUIDs rUUIDs;
    private final Users rUsers;
    private final USStates rUSStates;
    private final Words rWords;

    public MockNeat(final RandomType randomTypeType) {

        this.random = randomTypeType.getRandom();

        this.rActors = new Actors(this);
        this.rActresses = new Actresses(this);
        this.rBools = new Bools(this);
        this.rCars = new Cars(this);
        this.rCCS = new CreditCards(this);
        this.rCelebrities = new Celebrities(this);
        this.rChars = new Chars(this);
        this.rCountries = new Countries(this);
        this.rCities = new Cities(this);
        this.rCreatures = new Creatures(this);
        this.rCurrencies = new Currencies(this);
        this.rCVVS = new CVVS(this);
        this.rDays = new Days(this);
        this.rDepartments = new Departments(this);
        this.rDomains = new Domains(this);
        this.rDicts = new Dicts(this);
        this.rDoubles = new Doubles(this);
        this.rEmails = new Emails(this);
        this.rFiles = new FromFiles(this);
        this.rFrom = new Froms(this);
        this.rFloats = new Floats(this);
        this.rGenders = new Genders(this);
        this.rHashes = new Hashes(this);
        this.rIbans = new IBANs(this);
        this.rIndustries = new Industries(this);
        this.rInts = new Ints(this);
        this.rIPv4s = new IPv4s(this);
        this.rIPv6s = new IPv6s(this);
        this.rISSNS = new ISSNS(this);
        this.rJazzArtists = new JazzArtists(this);
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
        this.rPrimes = new Primes(this);
        this.rShufflers = new Shufflers(this);
        this.rSpace = new Space(this);
        this.rSSCs = new SSCs(this);
        this.rUUIDs = new UUIDs();
        this.rUsers = new Users(this);
        this.rUSStates = new USStates(this);
        this.rWords = new Words(this);
    }

    protected MockNeat() {
        this(RandomType.THREAD_LOCAL);
    }

    public MockNeat(final RandomType randomTypeType, Long seed) {
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
     * <p>Returns a {@code Actors} object that can be used to generate arbitrary actor names (e.g.: Michael Douglas)</p>
     *
     * @return a re-usable {@code Actors} instance. This class implements {@code MockUnitString}
     */
    public Actors actors() { return this.rActors; }

    /**
     * <p>Returns a {@code Actresses} object that can be used to generate arbitrary actresses names (e.g.: Elizabeth Taylor)</p>
     *
     * @return a re-usable {@code Actresses} instance. This class implements {@code MockUnitString}
     */
    public Actresses actresses() {
        return  this.rActresses;
    }

    /**
     * <p>Returns a {@code Bools} object that can be used to generate arbitrary {@code Boolean} values.</p>
     *
     * @return A re-usable {@code Bools} instance. The class implements {@code MockUnit<Boolean>}.
     */
    public Bools bools() { return this.rBools; }

    public Celebrities celebrities() { return this.rCelebrities; }

    /**
     * <p>Returns a {@code Chars} object that can be used to generate arbitrary {@code Character} values.</p>
     *
     * <p><em>Note:</em> Without any additional constraint, the {@code Chars} object will generate alphanumeric characters.</p>
     *
     * @return A re-usable {@code Chars} instance. The class implements {@code MockUnit<Character>}.
     */
    public Chars chars() { return this.rChars; }

    /**
     * <p>Returns a {@code Cars} object that can be used to generate arbitrary car names</p>
     *
     * @return A re-usable {@code Cars} instance. The class implements {@code MockUnitString}.
     */
    public Cars cars() {
        return this.rCars;
    }

    /**
     * <p>Returns a {@code Cities} object that can be used to generate arbitrary city names from around the world.</p>
     *
     * @return A re-usable {@code Citites} object.
     */
    public Cities cities() { return this.rCities; }

    /**
     * <p> Returns a {@code Creatures} object that can be used to generate arbitrary creature names.</p>
     *
     * @return A re-usable {@code Creatures} instance. The class implements {@code MockUnitString}.
     */
    public Creatures creatures() {
        return this.rCreatures;
    }

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
     * <p>Returns a new {@code Constant} object.</p>
     *
     * <p>This method is a {@code MockUnit<T>} used to generate constant values</p>
     *
     * @param object The constant object to return
     * @param <T> The type of the object
     * @return The constant object to return
     */
    public <T> Constant<T> constant(T object) { return new Constant<>(object); }

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
     * <p> Returns a {@code CSVs} object that can be used to generate CSV lines or files.</p>
     *
     * @return A new CSVs object.
     */
    public CSVs csvs() { return new CSVs(this); }

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
     * @param <T> The type of the target class
     * @param <FT> The type of the factory class
     *
     * @return The factory MockUnit
     */
    public <T, FT> Factory<T, FT> factory(Class<T> targetCls, Class<FT> factoryCls) {
        return new Factory<>(targetCls, factoryCls);
    }

    public <T> Filler<T> filler(Supplier<T> supplier) {
        return new Filler<>(this, supplier);
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
     * <p><em>Note:</em> By default it generates float numbers in the [0.0f, 1.0f) range.</p>
     * @return A re-usable {@code Floats} object. The {@code Floats} class implements {@code MockUnitFloats}.
     */
    public Floats floats() { return this.rFloats; }

    /**
     * <p>Returns a {@code Formatter} object than can be used to generate arbitrary patterns based on a given format.</p>
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
     * <p>Returns a {@code Industries} object that can be used to generate valid Industry names (Eg.: "Education and Health Services")</p>
     *
     * @return A re-usable {@code Industries} object. The {@code Industries} class implements {@code MockUnitString}.
     */
    public Industries industries() { return this.rIndustries; }

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

    /**
     * <p>Returns a {@code IPv4s} object that can be used to generate arbitrary IPv4 addresses.</p>
     *
     * @return A re-usable {@code IPv4s} object. The {@code IPv4s} class implements {@code MockUnitString}.
     */
    public IPv4s ipv4s() { return this.rIPv4s; }

    /**
     * <p>Returns a {@code IPv6s} object that can be used to generate arbitrary IPv6 addresses.</p>
     *
     * @return A re-usable {@code IPv6s} object. The {@code IPv6s} class implements {@code MockUnitString}.
     */
    public IPv6s iPv6s() { return this.rIPv6s; }

    /**
     * <p>Returns a {@code ISSNS} object that can be used to generate arbitrary ISSN codes.</p>
     *
     * @return A re-usable {@code ISSNS} object. The {@code ISSNS} class implements {@code MockUnitString}.
     */
    public ISSNS issns() { return this.rISSNS; }

    /**
     * <p>Returns a {@code JazzArtists} object that can be used to generate arbitrary jazz artists names</p>
     *
     * @return a re-usable {@code JazzArtists} object. The {@code JazzArtists} class implements {@code MockUnitString}
     */
    public JazzArtists jazzArtists() {
        return this.rJazzArtists;
    }

    /**
     * <p>Returns a {@code LocalDates} object that can be used to generate arbitrary {@code LocalDate} objects.</p>
     *
     * @return A re-usable {@code LocalDates} object. The {@code LocalDates} implements {@code MockUnitLocalDate}.
     */
    public LocalDates localDates() { return this.rLocalDates; }

    /**
     * <p>Returns a {@code Longs} object that can be used to generate arbitrary {@code Long} numbers.</p>
     *
     * <p><em>Note:</em>By default the internal {@code Random.nextLong()} will be called.</p>
     *
     * @return A re-usable {@code Longs} object. The {@code Longs} class extends the {@code MockUnitLong} class.
     */
    public Longs longs() { return this.rLongs; }

    /**
     * <p>Returns a {@code LongSeq} object that can be used to generate arbitrary {@code Long} numbers in a sequence.</p>
     *
     * @return A re-usable {@code LongSeq} object. The {@code LongSeq} class implements {@code MockUnitLong}.
     */
    public LongSeq longSeq() { return new LongSeq(); }

    /**
     * <p>Returns a {@code Macs} object that can be used to generate arbitrary physical addresses (MAC).</p>
     *
     * @return A re-usable {@code Macs} object. The {@code Macs} class implements {@code MockUnitString}.
     */
    public Macs macs() { return this.rMacs; }

    /**
     * <p>Returns a {@code Markovs} object that can be used to generate arbitrary text that can pass as almost valid using Markov Chains</p>
     *
     * <p>It can also be used to generate Lorem Ipsum text that is different each time the ending method is invoked</p>
     *
     * @return A re-usable {@code Markovs} object. The {@code Markovs} class implements {@code MockUnitString}
     */
    public Markovs markovs() { return this.rMarkovs; }

    /**
     * <p>Returns a {@code Mimes} object that can be used to generate arbitrary Mime Types (Eg.: "text/html", "image/x-icon", "text/calendar")</p>
     *
     * @return A re-usable {@code Mimes} object. The {@code Mimes} class implements {@code MockUnitString}.
     */
    public Mimes mimes() { return this.rMimes; }

    /**
     * <p>Returns a {@code Months} object that can be used to generate arbitrary {@code Month} objects.</p>
     *
     * @return A re-usable {@code Months} object. The {@code Months} class implements {@code MockUnitMonth}.
     */
    public Months months() { return this.rMonths; }

    /**
     * <p>Returns a {@code Money} object that can be used to generate arbitrary "money" information.</p>
     *
     * <p>The generated text represents sums of money generated using {@code NumberFormat.getCurrencyInstance().format(...)}.</p>
     *
     * <p><em>Note:</em>By default it returns a random sum of money</p>
     *
     * @return A re-usable {@code Money} object. The {@code Money} class implements {@code MockUnitString}.
     */
    public Money money() { return this.rMoney; }

    /**
     * <p>Returns a {@code Names} object that can be used to generate "names" (full names, first names or last names).</p>
     *
     * <p><em>Note:</em>By default it can be used to generate people full names.</p>
     *
     * <p><em>Note:</em> The names are the most common names that appear in the US.</p>
     *
     * @return A re-usable {@code Names} object. The {@code Names} class implements {@code MockUnitString}.
     */
    public Names names() { return this.rNames; }

    /**
     * <p>Returns a {@code NaughtyStrings} object that can be used to generate "naughty strings".</p>
     *
     * <p>Please check this URL for more info about the naughty strings:</p>
     *
     * <p>https://github.com/minimaxir/big-list-of-naughty-strings/blob/master/blns.txt</p>
     *
     * @return A re-usable {@code NaughtyStrings} object. The {@code NaughtStrings} class implements {@code MockUnitString}.
     */
    public NaughtyStrings naughtyStrings() { return this.rNaughtyStrings; }

    /**
     * <p>Returns a {@code Passwords} object that can be used to generate arbitrary user passwords.</p>
     *
     * @return A re-usable {@code Passwords} object. The {@code Passwords} class implements {@code MockUnitString}.
     */
    public Passwords passwords() { return this.rPasswords; }


    /**
     * <p>Returns a {@code Primes} object that can be used to generate prime numbers (small) </p>
     *
     * <p>The primes are only generated in this interval [0, 7919]</p>
     *
     * @return A re-usable {@code Primes} object. The {@code Primes} class implements {@code MockUnitInt}
     */
    public Primes primes() { return this.rPrimes; }

    /**
     * <p>Returns a new {@code Regex} object that can be used to generate arbitrary text bassed on a certain regex pattern.</p>
     *
     * @param regex The regex pattern that the generated string needs to comply.
     * @return A new {@code Regex} object. The {@code Regex} class implements {@code MockUnitString}.
     */
    public Regex regex(String regex) { return new Regex(regex); }

    /**
     * <p>Returns a new {@code ObjectMap} object that can be used to easily generate json files. </p>
     *
     * @return An objectMap object used to easily generate json files
     */
    public ObjectMap objectMap() { return new ObjectMap(this); }

    /**
     * <p>Returns a {@code Probabilities} object that can be used to generate arbitrary data with a given probability.</p>
     *
     * @param cls The type we are going to generate.
     * @param <T> The type of the class for returning probabilities
     * @return A re-usable {@code Probabilities} object. The class implements {@code MockUnit<T>}.
     */
    public <T> Probabilities<T> probabilites(Class<T> cls) { return new Probabilities<>(this, cls); }

    /**
     * <p>Returns a new {@code Reflect<T>} object that can be used to fill-up objects through reflection</p>
     *
     * <p>Check {@link #filler(Supplier)} as an alternative that doesn't use reflection. </p>
     *
     * @param cls The type of the class you want to construct
     * @param <T> The generic type of the class
     *
     * @return A re-usable {@code Reflect<T>} instance. The class implements {@code MockUnit<T>}
     */
    public <T> Reflect<T> reflect(Class<T> cls) { return new Reflect<>(this, cls);}

    /**
     * <p>Returns a new {@code Seq<T>} object that can be used to generate values by iterating trough the given {@code Iterable<T>} in order.</p>
     *
     * @param iterable The {@code Iterable<T>} values.
     * @param <T> The type of both the resulting {@code Seq<T>} and the {@code Iterable<T>}
     *
     * @return A re-usable {@code Seq<T>} object. The class implements {@code MockUnit<T>}
     */
    public <T> Seq<T> seq(Iterable<T> iterable) { return Seq.fromIterable(iterable); }

    /**
     * <p>Returns a new {@code Seq<String>} object that can be used to generate values by iterating through all the lines associated with a {@code DictType} in order.</p>
     *
     * @param dictType The {@code DictType} to iterate over
     * @return A re-usable {@code Seq<String>} object. The class implements {@code MockUnitString}
     */
    public Seq<String> seq(DictType dictType) { return Seq.fromDict(dictType); }

    /**
     * <p>Returns a {@code Seq<T>} object that can be used to generate values by iterating through all the elements of an array</p> in order.<p>
     *
     * @param array The array to iterate over
     * @param <T> The type of the array
     * @return A re-usable {@code Seq<T>}. The class implement {@code MockUnit<T>}
     */
    public <T> Seq<T> seq(T[] array) { return Seq.fromArray(array); }

    /**
     * <p>Returns a {@code SQLInserts} object that can be used to generate SQL Inserts</p>
     *
     * @return A re-usable {@code SQLInserts} object. This class implements {@code MockUnit<SQLInsert>}.
     */
    public SQLInserts sqlInserts() { return new SQLInserts(this); }

    /**
     * <p>Returns a {@code Shufflers} object than gets the arbitrary permutations of the source.<p>
     *
     * @return A re-usable {@code Shufflers} object.
     */
    public Shufflers shufflers() { return rShufflers; }

    public Strings strings() { return new Strings(this); }

    public Space space() { return rSpace; }

    public SSCs sscs() { return this.rSSCs; }

    public URLs urls() { return new URLs(this); }

    public UUIDs uuids() { return this.rUUIDs; }

    public Users users() { return this.rUsers; }

    public USStates usStates() { return this.rUSStates; }

    public Words words() { return this.rWords; }

    public Random getRandom() {
        return random;
    }

    public <T> MockUnit<T> from(List<T> alphabet) {
        return rFrom.from(alphabet);
    }

    public <T> MockUnit<T> from(T[] alphabet) {
        return rFrom.from(alphabet);
    }

    public <T extends Enum<?>> MockUnit<T> from(Class<T> enumClass) {
        return rFrom.from(enumClass);
    }

    public <T> MockUnit<T> fromKeys(Map<T, ?> map) {
        return rFrom.fromKeys(map);
    }

    public <T> MockUnit<T> fromValues(Map<?, T> map) {
        return rFrom.fromValues(map);
    }

    public MockUnitInt fromInts(Integer[] alphabet) {
        return rFrom.fromInts(alphabet);
    }

    public MockUnitInt fromInts(int[] alphabet) {
        return rFrom.fromInts(alphabet);
    }

    public MockUnitInt fromInts(List<Integer> alphabet) {
        return rFrom.fromInts(alphabet);
    }

    public MockUnitInt fromIntsValues(Map<?, Integer> map) {
        return rFrom.fromIntsValues(map);
    }

    public MockUnitInt fromIntsKeys(Map<Integer, ?> map) {
        return rFrom.fromIntsKeys(map);
    }

    public MockUnitDouble fromDoubles(Double[] alphabet) {
        return rFrom.fromDoubles(alphabet);
    }

    public MockUnitDouble fromDoubles(double[] alphabet) {
        return rFrom.fromDoubles(alphabet);
    }

    public MockUnitDouble fromDoubles(List<Double> alphabet) {
        return rFrom.fromDoubles(alphabet);
    }

    public MockUnitDouble fromDoublesValues(Map<?, Double> map) {
        return rFrom.fromDoublesValues(map);
    }

    public MockUnitDouble fromDoublesKeys(Map<Double, ?> map) {
        return rFrom.fromDoublesKeys(map);
    }

    public MockUnitLong fromLongs(Long[] alphabet) {
        return rFrom.fromLongs(alphabet);
    }

    public MockUnitLong fromLongs(long[] alphabet) {
        return rFrom.fromLongs(alphabet);
    }

    public MockUnitLong fromLongs(List<Long> alphabet) {
        return rFrom.fromLongs(alphabet);
    }

    public MockUnitLong fromLongsValues(Map<?, Long> map) {
        return rFrom.fromLongsValues(map);
    }

    public MockUnitLong fromLongsKeys(Map<Long, ?> map) {
        return rFrom.fromLongsKeys(map);
    }

    public MockUnitString fromStrings(String[] alphabet) {
        return rFrom.fromStrings(alphabet);
    }

    public MockUnitString fromStrings(List<String> alphabet) {
        return rFrom.fromStrings(alphabet);
    }

    public MockUnitString fromStringsValues(Map<?, String> map) {
        return rFrom.fromStringsValues(map);
    }

    public MockUnitString fromStringsKeys(Map<String, ?> map) {
        return rFrom.fromStringsKeys(map);
    }

}
