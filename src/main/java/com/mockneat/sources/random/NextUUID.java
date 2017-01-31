package com.mockneat.sources.random;

import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Created by andreinicolinciobanu on 15/01/2017.
 */
public class NextUUID {
    private Rand rand;
    private Random random;

    protected NextUUID(Rand rand) {
        this.rand = rand;
        this.random = rand.getRandom();
    }

    /**
     * @return A new UUID object
     */
    protected UUID nextUUIDObject() {
        return UUID.randomUUID();
    }

    /**
     * @return A names of UUID objs
     */
    protected Stream<UUID> streamUUIDObject() {
        return Stream.generate(this::nextUUIDObject);
    }

    /**
     * @return A String representation of an UUID
     */
    protected String nextUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return A names of String representations of UUIDs
     */
    protected Stream<String> streamUUID() {
        return Stream.generate(this::nextUUID);
    }

}