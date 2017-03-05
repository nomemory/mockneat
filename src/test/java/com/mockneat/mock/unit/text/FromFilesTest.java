package com.mockneat.mock.unit.text;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.mockneat.mock.Constants.*;
import static com.mockneat.mock.utils.LoopsUtils.loop;
import static com.mockneat.types.enums.StringType.LETTERS;
import static java.nio.file.StandardOpenOption.APPEND;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by andreinicolinciobanu on 05/03/2017.
 */
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
            tmp = Files.createTempFile(tmpFile, tmpFileExt);
            tmp.toFile().deleteOnExit();
            final String path = tmp.toFile().getPath();
            // Writing content to disk
            Files.write(tmp,  M.ints().range(0, 100).mapToString().list(100).val(), APPEND);
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
