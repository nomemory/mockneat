package net.andreinc.mockneat.unit.text;

import net.andreinc.mockneat.Constants;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.APPEND;
import static net.andreinc.mockneat.types.enums.StringType.LETTERS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by andreinicolinciobanu on 05/03/2017.
 */
public class FromFilesTest {

    @Test(expected = NullPointerException.class)
    public void testNullPath() throws Exception {
        Constants.M.files().from(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPath() throws Exception {
        Constants.M.files().from("").val();
    }

    @Test
    public void testFiles() throws Exception {
        String tmpFile = Constants.M.strings().size(32).type(LETTERS).val();
        String tmpFileExt = Constants.M.strings().size(3).type(LETTERS).val();
        Path tmp = null;
        try {
            tmp = Files.createTempFile(tmpFile, tmpFileExt);
            tmp.toFile().deleteOnExit();
            final String path = tmp.toFile().getPath();
            // Writing content to disk
            Files.write(tmp,  Constants.M.ints().range(0, 100).mapToString().list(100).val(), APPEND);
            loop(
                Constants.FILES_CYCLES,
                Constants.MOCKS,
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
