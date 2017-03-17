package net.andreinc.mockneat.unit.user;


import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.types.enums.NameType;
import net.andreinc.mockneat.utils.NamesCheckUtils;
import org.junit.Test;

import static java.lang.Character.isUpperCase;
import static net.andreinc.mockneat.types.enums.NameType.FIRST_NAME;
import static net.andreinc.mockneat.types.enums.NameType.LAST_NAME;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 28/02/2017.
 */
public class NamesTest {
    @Test
    public void testNames() throws Exception {
        loop(
                Constants.NAMES_CYCLES,
                Constants.MOCKS,
                m -> m.names().val(), n -> {
                    String[] split = n.split(" ");
                    String firstName = split[0];
                    String lastName = split[1];
                    assertTrue(split.length==2);
                    assertTrue(NamesCheckUtils.isNameOfType(firstName, FIRST_NAME));
                    assertTrue(NamesCheckUtils.isNameOfType(lastName, LAST_NAME));
                }
        );
    }

    @Test
    public void testNamesFirst() throws Exception {
       loop(
               Constants.NAMES_CYCLES,
               Constants.MOCKS,
               r -> r.names().first().val(),
               n -> assertTrue(NamesCheckUtils.isNameOfType(n, FIRST_NAME))
       );
    }

    @Test
    public void testNamesFirstNotEmpty() throws Exception {
        loop(
                Constants.NAMES_CYCLES,
                Constants.MOCKS,
                r -> r.names().first().val(),
                n -> assertTrue(isNotEmpty(n))
        );
    }

    @Test
    public void testNamesLast() throws Exception {
        loop(
                Constants.NAMES_CYCLES,
                Constants.MOCKS,
                r -> r.names().last().val(),
                n -> assertTrue(NamesCheckUtils.isNameOfType(n, LAST_NAME))
        );
    }

    @Test
    public void testNamesLastNotEmpty() throws Exception {
        loop(
                Constants.NAMES_CYCLES,
                Constants.MOCKS,
                r -> r.names().last().val(),
                n -> assertTrue(isNotEmpty(n))
        );
    }

    @Test
    public void testFullAlwaysMidName() throws Exception {
        loop(
                Constants.NAMES_CYCLES,
                Constants.MOCKS,
                r -> r.names().full(100.0).val(),
                n -> {
                        String[] split = n.split(" ");
                        String first = split[0];
                        String middle = split[1];
                        String last = split[2];

                        assertTrue(split.length==3);
                        assertTrue(NamesCheckUtils.isNameOfType(first, FIRST_NAME));
                        assertTrue(NamesCheckUtils.isNameOfType(last, LAST_NAME));
                        assertTrue(!last.endsWith(" "));
                        assertTrue(!first.endsWith(" "));

                        assertTrue(middle.length()==2);
                        assertTrue(isUpperCase(middle.charAt(0)));
                        assertTrue('.' == middle.charAt(1));
                }
        );
    }


    @Test
    public void testNamesFullNeverMid() throws Exception {
        loop(Constants.NAMES_CYCLES, Constants.MOCKS, r -> r.names().full(0.0).val(), n -> {
            String[] split = n.split(" ");
            assertTrue(split.length==2);
            assertTrue(!split[0].endsWith(" "));
            assertTrue(!split[1].endsWith(" "));
        });
    }

    @Test(expected = NullPointerException.class)
    public void testNamesTypeIsNull() throws Exception {
        Constants.M.names().type(null).val();
    }

    @Test
    public void testNamesType() throws Exception {
        loop(
                Constants.NAMES_CYCLES,
                Constants.MOCKS,
                m -> {
                    NameType type = m.from(NameType.class).val();
                    String name = m.names().type(type).val();
                    assertTrue(NamesCheckUtils.isNameOfType(name, type));
                }
        );
    }

    @Test(expected = NullPointerException.class)
    public void testNamesTypesNulL() throws Exception {
        NameType[] types = null;
        Constants.M.names().types(types).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNamesTypesEmptyArray() throws Exception {
        Constants.M.names().types(new NameType[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNamesEmptyElementInArray() throws Exception {
        NameType[] types = {FIRST_NAME, null, LAST_NAME};
        Constants.M.names().types(types).val();
    }
}
