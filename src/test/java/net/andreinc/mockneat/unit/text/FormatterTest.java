package net.andreinc.mockneat.unit.text;

import org.junit.Test;

import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 01/03/2017.
 */
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
        M.fmt("#{p1}abc").param("p1", null).val();
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

    @Test(expected = IllegalArgumentException.class)
    public void testFmtInvalidFmt1() throws Exception {
        M.fmt("#{1#{2}}").val();
    }

    @Test(expected = IllegalArgumentException.class )
    public void testFmtInvalidFmt2() throws Exception {
        M.fmt("#{1#{2}}").param("1#{2", M.ints()).val();
    }
}
