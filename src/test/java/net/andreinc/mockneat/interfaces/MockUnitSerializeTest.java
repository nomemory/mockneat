package net.andreinc.mockneat.interfaces;

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

import net.andreinc.mockneat.interfaces.models.ClassicPojo;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.Constants.M;
import static org.apache.commons.lang3.SerializationUtils.deserialize;
import static org.junit.Assert.assertTrue;

public class MockUnitSerializeTest {

    @Test
    public void testSerializeInteger() throws Exception {
        String randomFileName = M.strings().size(36).val();
        M.ints().bound(10).serialize(randomFileName);

        Integer x = deserialize(new FileInputStream(randomFileName));
        assertTrue(x >= 0  && x < 10);

        new File(randomFileName).delete();
    }

    @Test
    public void testSerializeList() throws Exception {
        String randomFileName = M.strings().size(36).val();
        M.intSeq().list(100).serialize(randomFileName);

        List<Integer> list = deserialize(new FileInputStream(randomFileName));

        assertTrue(list != null);
        assertTrue( list.size() == 100);
        assertTrue(list.get(0) == 0);
        range(0, list.size()).forEach(i -> assertTrue(list.get(i) == i));

        new File(randomFileName).delete();
    }

    @Test
    public void testSerializeBean() throws Exception {
        String randomFileName = M.strings().size(36).val();

        M.reflect(ClassicPojo.class)
                .useDefaults(true)
                .list(100)
                .serialize(randomFileName);

        List<ClassicPojo> list = deserialize(new FileInputStream(randomFileName));

        assertTrue(list!=null);
        assertTrue(list.size() == 100);

        new File(randomFileName).delete();
    }
}
