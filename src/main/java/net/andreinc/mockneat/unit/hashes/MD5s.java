package net.andreinc.mockneat.unit.hashes;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.interfaces.MockUnitString;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.Supplier;

/**
 * Created by andreinicolinciobanu on 25/03/17.
 */
public class MD5s implements MockUnitString {

    private MockNeat mock;

    public MD5s(MockNeat mock) {
        this.mock = mock;
    }

    @Override
    public Supplier<String> supplier() {
        return () -> mock.strings().size(32).map(this::md5Diggest).val();
    }

    // http://stackoverflow.com/questions/5470219/get-md5-string-from-message-digest
    private String md5Diggest(String initialString) {
        StringBuilder hexString = new StringBuilder();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(initialString.getBytes());

            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0"
                            + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
