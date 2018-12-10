package net.andreinc.mockneat.unit.text;

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

import net.andreinc.aleph.UncheckedFormatterException;
import net.andreinc.mockneat.abstraction.MockUnit;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class FormatterTest {

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyFmt() throws Exception {
        M.fmt("").val();
    }

    @Test(expected = NullPointerException.class)
    public void testNullFmt() throws Exception {
        M.fmt(null).val();
    }

    @Test
    public void testFmtWithNoParams() throws Exception {
        loop(FMT_CYCLES, MOCKS,  m -> m.fmt("abc").val(), s -> assertTrue("abc".equals(s)));
    }

    @Test(expected = NullPointerException.class)
    public void testFmtWithNullParamName() throws Exception {
        M.fmt("#{param1}abc").param(null, M.ints()).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFmtWithEmptyParamName() throws Exception {
        M.fmt("#{param1}abc").param("", M.ints()).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFmtWithNonAlphaNumericParamName() throws Exception {
        M.fmt("#{@param1}abc").param("_@param1", M.ints()).val();
    }

    @Test(expected = NullPointerException.class)
    public void testFmtWithNullMockUnit() throws Exception {
        MockUnit unit = null;
        M.fmt("#{p1}abc").param("p1", unit).val();
    }

    @Test
    public void testFmtGeneratedNumber() throws Exception {
        loop(
            FMT_CYCLES,
            MOCKS,
            m -> m.fmt("#{p1}#{p2}#{p3}")
                  .param("p1", m.ints().range(0, 3))
                  .param("p2", m.ints().range(3, 6))
                  .param("p3", m.ints().range(6, 9))
                  .val(),
            n -> {
                Integer n1 = Integer.parseInt(n.charAt(0)+"");
                Integer n2 = Integer.parseInt(n.charAt(1)+"");
                Integer n3 = Integer.parseInt(n.charAt(2)+"");
                assertTrue(0<=n1 && n1 < 3);
                assertTrue(3<=n2 && n2 < 6);
                assertTrue(6<=n3 && n3 < 9);
            }
        );
    }

    @Test
    public void testFmtGenerateCSVLine() throws Exception {
        loop(
            FMT_CYCLES,
            MOCKS,
            m -> m.fmt("#{fullName},#{age},#{email}")
                  .param("fullName", M.names().full())
                  .param("age", M.ints().range(18,100))
                  .param("email", M.emails())
                  .val(),
            l -> {
                String[] split = l.split(",");
                String fullName = split[0];

                String[] fullNameSplit = fullName.split(" ");
                String firstName = fullNameSplit[0];
                String lastName = fullNameSplit[1];
                Integer age = Integer.parseInt(split[1]);
                String email = split[2];

                assertTrue(18<= age && age < 100);
                assertTrue(firstName.length()>0);
                assertTrue(lastName.length()>0);
                assertTrue(email.contains("@"));
            }
        );
    }

    @Test
    public void testFmt1() throws Exception {
        loop(
            FMT_CYCLES,
            MOCKS,
            m -> m.fmt("##}{#{p1}}")
                  .param("p1", M.fromInts(new int[]{1}))
                  .val(),
            s -> assertTrue(("##}{1}".equals(s)))
        );
    }

    @Test(expected = UncheckedFormatterException.class)
    public void testFmtInvalidFmt1() throws Exception {
        M.fmt("#{1#{2}}").val();
    }

    @Test(expected = IllegalArgumentException.class )
    public void testFmtInvalidFmt2() throws Exception {
        M.fmt("#{1#{2}}").param("1#{2", M.ints()).val();
    }
}
