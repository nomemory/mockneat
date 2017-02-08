package com.mockneat.random;

import com.mockneat.random.unit.*;
import com.mockneat.random.unit.interfaces.RandUnit;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.Pair;
import com.mockneat.types.enums.RandType;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class Rand {

    private Random random;

    private Bools rBools;
    private Countries rCountries;
    private CCS rCCS;
    private Chars rChars;
    private CVVS rCVVS;
    private Dicts rDicts;
    private Doubles rDoubles;
    private Emails rEmails;
    private Floats rFloats;
    private Ints rInts;
    private IPv4s rIPv4s;
    private Longs rLongs;
    private Macs rMacs;
    private Markovs rMarkovs;
    private Months rMonths;
    private Names rNames;
    private Objs rObjects;
    private Passwords rPasswords;
    private UUIDs rUUIDs;
    private Users rUsers;

    public Rand(RandType randomType) {
        this.random = randomType.getRandom();

        this.rChars = new Chars(this);
        this.rBools = new Bools(this);
        this.rCountries = new Countries(this);
        this.rCCS = new CCS(this);
        this.rCVVS = new CVVS(this);
        this.rDicts = new Dicts(this);
        this.rDoubles = new Doubles(this);
        this.rEmails = new Emails(this);
        this.rFloats = new Floats(this);
        this.rInts = new Ints(this);
        this.rIPv4s = new IPv4s(this);
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

    public Rand() {
        this(RandType.THREAD_LOCAL_RANDOM);
    }

    public Rand(RandType randomType, Long seed) {
        this(randomType);
        random.setSeed(seed);
    }

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

    public Doubles doubles() {
        return this.rDoubles;
    }

    public Emails emails() { return this.rEmails; }

    public Floats floats() { return this.rFloats; }

    public Ints ints() { return this.rInts; }

    public IPv4s ipv4s() { return this.rIPv4s; }

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
