package net.andreinc.mockneat.types.enums;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("ImmutableEnumChecker")
public enum RandomType {

    OLD(new Random()),
    SECURE(new SecureRandom()),
    THREAD_LOCAL(ThreadLocalRandom.current());

    private final Random random;

    RandomType(Random random) {
        this.random = random;
    }

    public Random getRandom() {
        return random;
    }
}
