package com.mockneat.annotations.generator;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.sequences.NumberSequence;
import com.mockneat.types.enums.RandType;
import com.mockneat.types.exceptions.GeneratorException;
import com.mockneat.types.exceptions.GeneratorExceptionType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SourcesRepository {

    public static final String DEFAULT_RAND_SOURCE_RANDOM = "DEFAULT_RANDOM_SOURCE_RANDOM";
    public static final String DEFAULT_RAND_SOURCE_SECURE_RANODM = "DEFAULT_RAND_SOURCE_SECURE_RANODM";
    public static final String DEFAULT_RAND_SOURCE_THREAD_LOCAL = "DEFAULT_RAND_SOURCE_THREAD_LOCAL";

    private static final Map<String, Rand> DEFAULT_RANDOM_SOURCES = new HashMap<>();
    private static final Map<String, Rand> CUSTOM_RANDOM_SOURCES = new ConcurrentHashMap<>();

    private static final Map<String, NumberSequence> NUMBER_SEQUENCES = new ConcurrentHashMap<>();

    static {
        DEFAULT_RANDOM_SOURCES.put(DEFAULT_RAND_SOURCE_RANDOM,
                new Rand(RandType.RANDOM));

        DEFAULT_RANDOM_SOURCES.put(DEFAULT_RAND_SOURCE_SECURE_RANODM,
                new Rand(RandType.SECURE_RANDOM));

        DEFAULT_RANDOM_SOURCES.put(DEFAULT_RAND_SOURCE_THREAD_LOCAL,
                new Rand(RandType.THREAD_LOCAL_RANDOM));
    }

    private static final SourcesRepository INSTANCE = new SourcesRepository();

    private SourcesRepository() {}

    public static SourcesRepository getInstance() {
        return INSTANCE;
    }

    public void putRandSource(String name, RandType randType) {
        if (DEFAULT_RANDOM_SOURCES.keySet().contains(name))
            throw new GeneratorException(GeneratorExceptionType.RESERVED_NAME, name);

        CUSTOM_RANDOM_SOURCES.put(name, new Rand(randType));
    }

    public void deleteRandomSource(String name) {
        CUSTOM_RANDOM_SOURCES.remove(name);
    }

    public Rand getRandSource(String name) {
        Rand result;

        result = DEFAULT_RANDOM_SOURCES.get(name);
        if (null!=result)
            return result;

        result = CUSTOM_RANDOM_SOURCES.get(name);
        if (null==result)
            throw new GeneratorException(GeneratorExceptionType.INVALID_RANDOM_SOURCE_REFERENCE, name);
        return result;
    }

    public void putSequence(String sequenceName, NumberSequence numberSequence) {
        NUMBER_SEQUENCES.put(sequenceName, numberSequence);
    }

    public void deleteSequence(String sequenceName) {
        NUMBER_SEQUENCES.remove(sequenceName);
    }

    public NumberSequence getSequence(String sequenceName) {
        if (!NUMBER_SEQUENCES.containsKey(sequenceName))
            throw new GeneratorException(GeneratorExceptionType.INVALID_SEQUNCE_REFERENCE, sequenceName);

        return NUMBER_SEQUENCES.get(sequenceName);
    }
}
