package net.andreinc.mockneat.unit.hashes;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.interfaces.MockUnitString;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by andreinicolinciobanu on 25/03/17.
 */
public class Hashes {
    private MockNeat mock;

    public Hashes(MockNeat mock) {
        this.mock = mock;
    }

    private Supplier<String> supplier(Function<String, String> digester) {
        return mock.strings().size(32).map(digester).supplier();
    }

    public MockUnitString md2() {
        return () -> supplier(DigestUtils::md2Hex);
    }

    public MockUnitString md5() {
        return () -> supplier(DigestUtils::md5Hex);
    }

    public MockUnitString sha1() {
        return () -> supplier(DigestUtils::sha1Hex);
    }

    public MockUnitString sha256() {
        return () -> supplier(DigestUtils::sha256Hex);
    }

    public MockUnitString sha384() {
        return () -> supplier(DigestUtils::sha384Hex);
    }

    public MockUnitString sha512() {
        return () -> supplier(DigestUtils::sha512Hex);
    }
}
