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

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.write;
import static java.nio.file.StandardOpenOption.APPEND;
import static net.andreinc.mockneat.Constants.*;
import static net.andreinc.mockneat.types.enums.StringType.LETTERS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class FromFilesTest {

    @Test(expected = NullPointerException.class)
    public void testNullPath() throws Exception {
        M.files().from(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPath() throws Exception {
        M.files().from("").val();
    }

    @Test
    public void testFiles() throws Exception {
        String tmpFile = M.strings().size(32).type(LETTERS).val();
        String tmpFileExt = M.strings().size(3).type(LETTERS).val();
        Path tmp = null;
        try {
            tmp = createTempFile(tmpFile, tmpFileExt);
            tmp.toFile().deleteOnExit();
            final String path = tmp.toFile().getPath();
            // Writing content to disk
            write(tmp,  M.ints().range(0, 100).mapToString().list(100).val(), APPEND);
            loop(
                FILES_CYCLES,
                MOCKS,
                m -> m.files().from(path).val(),
                l -> {
                    Integer x = Integer.parseInt(l);
                    assertTrue(0<=x && x <100);
                }
            );
        } catch (IOException e) {
            if (null != tmp) {
                tmp.toFile().deleteOnExit();
            }
            fail("Cannot write tmp file to disk");
        }
    }
}
