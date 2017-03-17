package net.andreinc.mockneat.unit.user;

import net.andreinc.mockneat.types.enums.PassStrengthType;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.alphabets.Alphabets.SPECIAL_CHARACTERS;
import static net.andreinc.mockneat.types.enums.PassStrengthType.MEDIUM;
import static net.andreinc.mockneat.types.enums.PassStrengthType.STRONG;
import static net.andreinc.mockneat.types.enums.PassStrengthType.WEAK;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 18/02/2017.
 */
public class PasswordsTest {

    @Test(expected = NullPointerException.class)
    public void testPasswordNullType() throws Exception {
        M.passwords().type(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordEmptyTypes() throws Exception {
        M.passwords().types().val();
    }

    @Test(expected = NullPointerException.class)
    public void testPasswordNullTypes() throws Exception {
        PassStrengthType[] types = null;
        M.passwords().types(types).val();
    }

    protected Boolean hasCorrectLength(String genPass, PassStrengthType type) {
        int lower = type.getLength().getLowerBound();
        int upper = type.getLength().getUpperBound();
        int length = genPass.length();
        return length >= lower && length <= upper;
    }

    protected Boolean containsSpecial(String genPass) {
        Set<Character> specialChars = new HashSet<>(SPECIAL_CHARACTERS);
        boolean result = false;
        for (int i = 0; i < genPass.length(); i++) {
            if (specialChars.contains(genPass.charAt(i))) {
                result = true;
                break;
            }
        }
        return result;
    }

    protected void testPassLength(PassStrengthType type) throws Exception {
        loop(PASS_CYCLES,
                MOCKS,
                r -> r.passwords().type(type).val(),
                p -> assertTrue(hasCorrectLength(p, type)));
    }

    protected void testPassContainsSpecialChr(PassStrengthType type) throws Exception {
        loop(PASS_CYCLES,
                MOCKS,
                r -> r.passwords().type(type).val(),
                p -> assertTrue(containsSpecial(p)));
    }

    @Test
    public void testWeakPasswordLength() throws Exception {
        testPassLength(WEAK);
    }

    @Test
    public void testMediumPasswordLength() throws Exception {
        testPassLength(MEDIUM);
    }

    @Test
    public void testStrongPasswordLength() throws Exception {
        testPassLength(STRONG);
    }

    @Test
    public void testMediumContainsSpecialChr() throws Exception {
        testPassContainsSpecialChr(MEDIUM);
    }

    @Test
    public void testStrongContainsSpecialChr() throws Exception {
        testPassContainsSpecialChr(STRONG);
    }
}
