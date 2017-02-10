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

import com.mockneat.random.unit.address.Countries;
import com.mockneat.random.unit.financial.CCS;
import com.mockneat.random.unit.financial.CVVS;
import com.mockneat.random.unit.id.UUIDs;
import com.mockneat.random.unit.networking.IPv4s;
import com.mockneat.random.unit.networking.Macs;
import com.mockneat.random.unit.objects.Compose;
import com.mockneat.random.unit.objects.Objs;
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

public class Rand {

    private Random random;

    private Bools rBools;
    private Countries rCountries;
    private CCS rCCS;
    private Chars rChars;
    private CVVS rCVVS;
    private Days rDays;
    private Dicts rDicts;
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
    private Objs rObjects;
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
        this.rDicts = new Dicts(this);
        this.rDays = new Days(this);
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
        this.rObjects = new Objs(this);
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

    public Objs objs() { return this.rObjects; }

    public Passwords passwords() { return this.rPasswords; }

    public UUIDs uuids() { return this.rUUIDs; }

    public Users users() { return this.rUsers; }

    public Random getRandom() {
        return random;
    }
}
