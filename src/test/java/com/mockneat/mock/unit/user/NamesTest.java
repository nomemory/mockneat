package com.mockneat.mock.unit.user;

import com.mockneat.types.enums.NameType;
import org.junit.Test;

import static com.mockneat.mock.Constants.*;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static com.mockneat.mock.utils.NamesCheckUtils.isNameOfType;
import static com.mockneat.types.enums.NameType.FIRST_NAME;
import static com.mockneat.types.enums.NameType.LAST_NAME;
import static java.lang.Character.isUpperCase;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 28/02/2017.
 */
public class NamesTest {
    @Test
    public void testNames() throws Exception {
        loop(
                NAMES_CYCLES,
                MOCKS,
                m -> m.names().val(), n -> {
                    String[] split = n.split(" ");
                    String firstName = split[0];
                    String lastName = split[1];
                    assertTrue(split.length==2);
                    assertTrue(isNameOfType(firstName, FIRST_NAME));
                    assertTrue(isNameOfType(lastName, LAST_NAME));
                }
        );
    }

    @Test
    public void testNamesFirst() throws Exception {
       loop(
               NAMES_CYCLES,
               MOCKS,
               r -> r.names().first().val(),
               n -> assertTrue(isNameOfType(n, FIRST_NAME))
       );
    }

    @Test
    public void testNamesFirstNotEmpty() throws Exception {
        loop(
                NAMES_CYCLES,
                MOCKS,
                r -> r.names().first().val(),
                n -> assertTrue(isNotEmpty(n))
        );
    }

    @Test
    public void testNamesLast() throws Exception {
        loop(
                NAMES_CYCLES,
                MOCKS,
                r -> r.names().last().val(),
                n -> assertTrue(isNameOfType(n, LAST_NAME))
        );
    }

    @Test
    public void testNamesLastNotEmpty() throws Exception {
        loop(
                NAMES_CYCLES,
                MOCKS,
                r -> r.names().last().val(),
                n -> assertTrue(isNotEmpty(n))
        );
    }

    @Test
    public void testFullAlwaysMidName() throws Exception {
        loop(
                NAMES_CYCLES,
                MOCKS,
                r -> r.names().full(100.0).val(),
                n -> {
                        String[] split = n.split(" ");
                        String first = split[0];
                        String middle = split[1];
                        String last = split[2];

                        assertTrue(split.length==3);
                        assertTrue(isNameOfType(first, FIRST_NAME));
                        assertTrue(isNameOfType(last, LAST_NAME));
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
        loop(NAMES_CYCLES, MOCKS, r -> r.names().full(0.0).val(), n -> {
            String[] split = n.split(" ");
            assertTrue(split.length==2);
            assertTrue(!split[0].endsWith(" "));
            assertTrue(!split[1].endsWith(" "));
        });
    }

    @Test(expected = NullPointerException.class)
    public void testNamesTypeIsNull() throws Exception {
        M.names().type(null).val();
    }

    @Test
    public void testNamesType() throws Exception {
        loop(
                NAMES_CYCLES,
                MOCKS,
                m -> {
                    NameType type = m.from(NameType.class).val();
                    String name = m.names().type(type).val();
                    assertTrue(isNameOfType(name, type));
                }
        );
    }

    @Test(expected = NullPointerException.class)
    public void testNamesTypesNulL() throws Exception {
        NameType[] types = null;
        M.names().types(types).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNamesTypesEmptyArray() throws Exception {
        M.names().types(new NameType[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNamesEmptyElementInArray() throws Exception {
        NameType[] types = {FIRST_NAME, null, LAST_NAME};
        M.names().types(types).val();
    }
}
