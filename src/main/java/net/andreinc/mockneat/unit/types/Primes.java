package net.andreinc.mockneat.unit.types;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitInt;
import net.andreinc.mockneat.alphabets.Alphabets;

import java.util.function.Supplier;

import static net.andreinc.mockneat.unit.objects.From.from;

public class Primes extends MockUnitBase implements MockUnitInt {

    public static Primes primes() { return MockNeat.threadLocal().primes(); }

    public Primes(MockNeat mockNeat) {
        super(mockNeat);
    }

    public MockUnitInt smallPrimes() {
        return () -> from(Alphabets.SMALL_PRIMES).supplier();
    }

    @Override
    public Supplier<Integer> supplier() {
        return from(Alphabets.SMALL_PRIMES).supplier();
    }
}
