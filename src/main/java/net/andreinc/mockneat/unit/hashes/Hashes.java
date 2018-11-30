package net.andreinc.mockneat.unit.hashes;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.function.Function;
import java.util.function.Supplier;

public class Hashes extends MockUnitBase {

    private static final Integer HASHED_STRING_SIZE = 128;

    public static Hashes hashes() { return MockNeat.threadLocal().hashes(); }

    protected Hashes() { }

    public Hashes(MockNeat mockNeat) {
        super(mockNeat);
    }

    private Supplier<String> supplier(Function<String, String> digester) {
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
