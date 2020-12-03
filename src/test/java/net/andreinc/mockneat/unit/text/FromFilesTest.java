package net.andreinc.mockneat.unit.text;

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
    public void testNullPath() {
        M.files().from(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPath() {
        M.files().from("").val();
    }

    @Test
    public void testFiles() {
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
                    int x = Integer.parseInt(l);
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
