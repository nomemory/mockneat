package net.andreinc.mockneat.unit.hashes;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.interfaces.MockUnitString;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.function.Supplier;

/**
 * Created by andreinicolinciobanu on 25/03/17.
 */
public class SHA512s implements MockUnitString {
    private MockNeat mock;

    public SHA512s(MockNeat mock) {
        this.mock = mock;
    }

    @Override
    public Supplier<String> supplier() {
        return mock.strings().size(32).map(DigestUtils::sha512Hex).supplier();
    }
}
