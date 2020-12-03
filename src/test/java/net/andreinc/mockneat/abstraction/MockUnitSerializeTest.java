package net.andreinc.mockneat.abstraction;

import net.andreinc.mockneat.abstraction.models.ClassicPojo;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.Constants.M;
import static org.apache.commons.lang3.SerializationUtils.deserialize;
import static org.junit.Assert.*;

public class MockUnitSerializeTest {

    @Test
    public void testSerializeInteger() throws Exception {
        String randomFileName = M.strings().size(36).val();
        M.ints().bound(10).serialize(randomFileName);

        Integer x = deserialize(new FileInputStream(randomFileName));
        assertTrue(x >= 0  && x < 10);

        boolean isDeleted = new File(randomFileName).delete();
        assertTrue(isDeleted);
    }

    @Test
    public void testSerializeList() throws Exception {
        String randomFileName = M.strings().size(36).val();
        M.intSeq().list(100).serialize(randomFileName);

        List<Integer> list = deserialize(new FileInputStream(randomFileName));

        assertNotNull(list);
        assertEquals(100, list.size());
        assertEquals(0, (int) list.get(0));
        range(0, list.size()).forEach(i -> assertEquals((int) list.get(i), i));

        boolean isDeleted = new File(randomFileName).delete();
        assertTrue(isDeleted);
    }

    @Test
    public void testSerializeBean() throws Exception {
        String randomFileName = M.strings().size(36).val();

        M.reflect(ClassicPojo.class)
                .useDefaults(true)
                .list(100)
                .serialize(randomFileName);

        List<ClassicPojo> list = deserialize(new FileInputStream(randomFileName));

        assertNotNull(list);
        assertEquals(100, list.size());

        boolean isDeleted = new File(randomFileName).delete();
        assertTrue(isDeleted);
    }
}
