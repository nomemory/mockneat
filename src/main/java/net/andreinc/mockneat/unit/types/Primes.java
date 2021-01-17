package net.andreinc.mockneat.unit.types;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitInt;
import net.andreinc.mockneat.alphabets.Alphabets;

import java.util.function.Supplier;

import static net.andreinc.mockneat.unit.objects.From.from;

public class Primes extends MockUnitBase implements MockUnitInt {

    /**
     * <p>Returns a {@code Primes} object that can be used to generate prime numbers (small) </p>
     *
     * <p>The primes are only generated in this interval [0, 7919]</p>
     *
     * @return A re-usable {@code Primes} object. The {@code Primes} class implements {@code MockUnitInt}
     */
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
