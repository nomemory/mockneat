package net.andreinc.mockneat.unit.user;

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

public class PasswordsTest {

    @Test(expected = NullPointerException.class)
    public void testPasswordNullType() {
        M.passwords().type(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordEmptyTypes() {
        M.passwords().types().val();
    }

    @Test(expected = NullPointerException.class)
    public void testPasswordNullTypes() {
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

    protected void testPassLength(PassStrengthType type) {
        loop(PASS_CYCLES,
                MOCKS,
                r -> r.passwords().type(type).val(),
                p -> assertTrue(hasCorrectLength(p, type)));
    }

    protected void testPassContainsSpecialChr(PassStrengthType type) {
        loop(PASS_CYCLES,
                MOCKS,
                r -> r.passwords().type(type).val(),
                p -> assertTrue(containsSpecial(p)));
    }

    @Test
    public void testWeakPasswordLength() {
        testPassLength(WEAK);
    }

    @Test
    public void testMediumPasswordLength() {
        testPassLength(MEDIUM);
    }

    @Test
    public void testStrongPasswordLength() {
        testPassLength(STRONG);
    }

    @Test
    public void testMediumContainsSpecialChr() {
        testPassContainsSpecialChr(MEDIUM);
    }

    @Test
    public void testStrongContainsSpecialChr() {
        testPassContainsSpecialChr(STRONG);
    }
}
