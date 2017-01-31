package com.mockneat.types.enums;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by andreinicolinciobanu on 24/10/2016.
 */
public enum RandType {

    RANDOM (new Random()),
    SECURE_RANDOM (new SecureRandom()),
    THREAD_LOCAL_RANDOM (ThreadLocalRandom.current());

    private Random random;

    RandType(Random random) {
        this.random = random;
    }

    public Random getRandom() {
        return random;
    }
}
