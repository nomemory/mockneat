package net.andreinc.mockneat.unit.hashes;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Hashes extends MockUnitBase {

    private static final Integer HASHED_STRING_SIZE = 128;

    public static Hashes hashes() { return MockNeat.threadLocal().hashes(); }

    protected Hashes() { }

    public Hashes(MockNeat mockNeat) {
        super(mockNeat);
    }

    private Supplier<String> supplier(UnaryOperator<String> digester) {
        return mockNeat.strings().size(HASHED_STRING_SIZE).map(digester).supplier();
    }

    /**
     * <p>Returns a {@code MockUnitString} instance that can be used to generate MD2 hashes.</p>
     *
     * @return A {@code MockUnitString} instance.
     */
    public MockUnitString md2() {
        return () -> supplier(DigestUtils::md2Hex);
    }

    /**
     * <p>Returns a {@code MockUnitString} instance that can be used to generate MD5 hashes.</p>
     *
     * @return A {@code MockUnitString} instance.
     */
    public MockUnitString md5() {
        return () -> supplier(DigestUtils::md5Hex);
    }

    /**
     * <p>Returns a {@code MockUnitString} instance that can be used to generate SHA1 hashes.</p>
     *
     * @return A {@code MockUnitString} instance.
     */
    public MockUnitString sha1() {
        return () -> supplier(DigestUtils::sha1Hex);
    }

    /**
     * <p>Returns a {@code MockUnitString} instance that can be used to generate SHA256 hashes.</p>
     *
     * @return A {@code MockUnitString} instance.
     */
    public MockUnitString sha256() {
        return () -> supplier(DigestUtils::sha256Hex);
    }

    /**
     * <p>Returns a {@code MockUnitString} instance that can be used to generate SHA384 hashes.</p>
     *
     * @return A {@code MockUnitString} instance.
     */
    public MockUnitString sha384() {
        return () -> supplier(DigestUtils::sha384Hex);
    }

    /**
     * <p>Returns a {@code MockUnitString} instance that can be used to generate SHA512 hashes.</p>
     *
     * @return A {@code MockUnitString} instance.
     */
    public MockUnitString sha512() {
        return () -> supplier(DigestUtils::sha512Hex);
    }
}
