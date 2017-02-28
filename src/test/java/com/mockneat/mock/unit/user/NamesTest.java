package com.mockneat.mock.unit.user;

import com.mockneat.mock.utils.file.FileManager;
import com.mockneat.types.enums.NameType;
import org.junit.Test;

import java.util.*;

import static com.mockneat.mock.Constants.NAMES_CYCLES;
import static com.mockneat.mock.Constants.RAND;
import static com.mockneat.mock.Constants.RANDS;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static com.mockneat.types.enums.NameType.FIRST_NAME;
import static com.mockneat.types.enums.NameType.LAST_NAME;
import static java.lang.Character.isUpperCase;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 28/02/2017.
 */
public class NamesTest {

    private static Map<NameType, List<Set<String>>> NAMES = new EnumMap<>(NameType.class);
    private static FileManager FILE_MANAGER = FileManager.getInstance();

    static {
        Arrays.stream(NameType.values()).forEach(nt -> {
            List<Set<String>> ll = new LinkedList<>();
            Arrays.stream(nt.getDictionaries()).forEach(dict ->
                    ll.add(new HashSet<>(FILE_MANAGER.getLines(dict))));
            NAMES.put(nt, ll);
        });
    }

    private static boolean isInSets(String value, List<Set<String>> sets) {
        return sets.stream().filter(s -> s.contains(value)).findFirst().isPresent();
    }

    @Test
    public void testNames() throws Exception {
        loop(NAMES_CYCLES, RANDS, m -> m.names().val(), n -> {
            String[] split = n.split(" ");
            String firstName = split[0];
            String lastName = split[1];
            assertTrue(split.length==2);
            assertTrue(isInSets(firstName, NAMES.get(FIRST_NAME)));
            assertTrue(isInSets(lastName, NAMES.get(LAST_NAME)));
        });
    }

    @Test
    public void testNamesFirst() throws Exception {
       loop(NAMES_CYCLES, RANDS, r -> r.names().first().val(),
               n -> assertTrue(isInSets(n, NAMES.get(FIRST_NAME))));
    }

    @Test
    public void testNamesFirstNotEmpty() throws Exception {
        loop(NAMES_CYCLES, RANDS, r -> r.names().first().val(),
                n -> assertTrue(isNotEmpty(n)));
    }

    @Test
    public void testNamesLast() throws Exception {
        loop(NAMES_CYCLES, RANDS, r -> r.names().last().val(),
                n -> assertTrue(isInSets(n, NAMES.get(LAST_NAME))));
    }

    @Test
    public void testNamesLastNotEmpty() throws Exception {
        loop(NAMES_CYCLES, RANDS, r -> r.names().last().val(),
                n -> assertTrue(isNotEmpty(n)));
    }

    @Test
    public void testFullAlwaysMidName() throws Exception {
        loop(NAMES_CYCLES, RANDS, r -> r.names().full(100.0).val(), n -> {
            String[] split = n.split(" ");
            String first = split[0];
            String middle = split[1];
            String last = split[2];

            assertTrue(split.length==3);
            assertTrue(isInSets(first, NAMES.get(FIRST_NAME)));
            assertTrue(isInSets(last, NAMES.get(LAST_NAME)));
            assertTrue(!last.endsWith(" "));
            assertTrue(!first.endsWith(" "));

            assertTrue(middle.length()==2);
            assertTrue(isUpperCase(middle.charAt(0)));
            assertTrue('.' == middle.charAt(1));
        });
    }


    @Test
    public void testNamesFullNeverMid() throws Exception {
        loop(NAMES_CYCLES, RANDS, r -> r.names().full(0.0).val(), n -> {
            String[] split = n.split(" ");
            assertTrue(split.length==2);
            assertTrue(!split[0].endsWith(" "));
            assertTrue(!split[1].endsWith(" "));
        });
    }

    @Test(expected = NullPointerException.class)
    public void testNamesTypeIsNull() throws Exception {
        RAND.names().type(null).val();
    }

    @Test
    public void testNamesType() throws Exception {
        loop(NAMES_CYCLES, RANDS, m -> {
            NameType type = m.from(NameType.class).val();
            String name = m.names().type(type).val();
            assertTrue(isInSets(name, NAMES.get(type)));
        });
    }

    @Test(expected = NullPointerException.class)
    public void testNamesTypesNulL() throws Exception {
        NameType[] types = null;
        RAND.names().types(types).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNamesTypesEmptyArray() throws Exception {
        RAND.names().types(new NameType[]{}).val();
    }

    @Test(expected = NullPointerException.class)
    public void testNamesEmptyElementInArray() throws Exception {
        NameType[] types = {FIRST_NAME, null, LAST_NAME};
        RAND.names().types(types).val();
    }
}
