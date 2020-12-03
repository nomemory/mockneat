package net.andreinc.mockneat.unit.user;

import net.andreinc.mockneat.Constants;
import net.andreinc.mockneat.types.enums.NameType;
import net.andreinc.mockneat.utils.NamesCheckUtils;
import org.junit.Test;

import static java.lang.Character.isUpperCase;
import static net.andreinc.mockneat.Constants.M;
import static net.andreinc.mockneat.types.enums.NameType.FIRST_NAME;
import static net.andreinc.mockneat.types.enums.NameType.LAST_NAME;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.*;

public class NamesTest {
    @Test
    public void testNames() {
        loop(
                Constants.NAMES_CYCLES,
                Constants.MOCKS,
                m -> m.names().val(), n -> {
                    String[] split = n.split(" ");
                    String firstName = split[0];
                    String lastName = split[1];
                    assertEquals(2, split.length);
                    assertTrue(NamesCheckUtils.isNameOfType(firstName, FIRST_NAME));
                    assertTrue(NamesCheckUtils.isNameOfType(lastName, LAST_NAME));
                }
        );
    }

    @Test
    public void testNamesFirst() {
       loop(
               Constants.NAMES_CYCLES,
               Constants.MOCKS,
               r -> r.names().first().val(),
               n -> assertTrue(NamesCheckUtils.isNameOfType(n, FIRST_NAME))
       );
    }

    @Test
    public void testNamesFirstNotEmpty() {
        loop(
                Constants.NAMES_CYCLES,
                Constants.MOCKS,
                r -> r.names().first().val(),
                n -> assertTrue(isNotEmpty(n))
        );
    }

    @Test
    public void testNamesLast() {
        loop(
                Constants.NAMES_CYCLES,
                Constants.MOCKS,
                r -> r.names().last().val(),
                n -> assertTrue(NamesCheckUtils.isNameOfType(n, LAST_NAME))
        );
    }

    @Test
    public void testNamesLastNotEmpty() {
        loop(
                Constants.NAMES_CYCLES,
                Constants.MOCKS,
                r -> r.names().last().val(),
                n -> assertTrue(isNotEmpty(n))
        );
    }

    @Test
    public void testFullAlwaysMidName() {
        loop(
                Constants.NAMES_CYCLES,
                Constants.MOCKS,
                r -> r.names().full(100.0).val(),
                n -> {
                        String[] split = n.split(" ");
                        String first = split[0];
                        String middle = split[1];
                        String last = split[2];

                    assertEquals(3, split.length);
                        assertTrue(NamesCheckUtils.isNameOfType(first, FIRST_NAME));
                        assertTrue(NamesCheckUtils.isNameOfType(last, LAST_NAME));
                    assertFalse(last.endsWith(" "));
                    assertFalse(first.endsWith(" "));

                    assertEquals(2, middle.length());
                        assertTrue(isUpperCase(middle.charAt(0)));
                    assertEquals('.', middle.charAt(1));
                }
        );
    }


    @Test
    public void testNamesFullNeverMid() {
        loop(Constants.NAMES_CYCLES, Constants.MOCKS, r -> r.names().full(0.0).val(), n -> {
            String[] split = n.split(" ");
            assertEquals(2, split.length);
            assertFalse(split[0].endsWith(" "));
            assertFalse(split[1].endsWith(" "));
        });
    }

    @Test(expected = NullPointerException.class)
    public void testNamesTypeIsNull() {
        M.names().type(null).val();
    }

    @Test
    public void testNamesType() {
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
    public void testNamesTypesNulL() {
        M.names().types((NameType[]) null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNamesTypesEmptyArray() {
        M.names().types(new NameType[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNamesEmptyElementInArray() {
        NameType[] types = {FIRST_NAME, null, LAST_NAME};
        M.names().types(types).val();
    }
}
