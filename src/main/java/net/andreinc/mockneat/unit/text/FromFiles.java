package net.andreinc.mockneat.unit.text;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.utils.file.FileManager;

import static net.andreinc.mockneat.types.enums.StringFormatType.LOWER_CASE;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmpty;

public class FromFiles extends MockUnitBase {

    private final FileManager fm = FileManager.getInstance();

    /**
     * <p>Returns a {@code FromFiles} object that can used to generate random strings from a given text file.</p>
     *
     * <p><em>Note:</em> The file is loaded in memory. For the moment there is no functionality to "unload" it.</p>
     *
     * @return A re-usable {@code FromFiles} object.
     */
    public static FromFiles files() {
        return MockNeat.threadLocal().files();
    }

    public FromFiles() { }

    public FromFiles(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * <p>Generates a new {@code MockUnitString} that can be used to generate String lines from the given {@code path}.</p>
     *
     * <p><em>Note:</em> The files needs to exist and have read rights.</p>
     *
     * @param path The path of the file from the disk.
     * @return A new {@code MockUnitString}
     */
    public MockUnitString from(String path) {
        notEmpty(path, "path");
        return mockNeat.fromStrings(fm.getLines(path)).format(LOWER_CASE);
    }
}
