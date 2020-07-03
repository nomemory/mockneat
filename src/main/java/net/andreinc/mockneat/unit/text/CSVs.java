package net.andreinc.mockneat.unit.text;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.*;
import org.apache.commons.text.StringEscapeUtils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;
import static net.andreinc.mockneat.abstraction.MockConstValue.constant;
import static net.andreinc.mockneat.abstraction.MockUnitValue.unit;
import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class CSVs extends MockUnitBase implements MockUnitString {

    private String separator = ",";
    private final List<MockValue> columns = new LinkedList<>();

    public static CSVs csvs() {
        return MockNeat.threadLocal().csvs();
    }

    protected CSVs() {}

    public CSVs(MockNeat mockNeat) {
        super(mockNeat);
    }

    public CSVs separator(String separator) {
        notEmpty(separator, "separator");
        this.separator = separator;
        return this;
    }

    /**
     * Add a new column to the resulting csv line.
     * Internally the supplied mockUnit is transformed to a MockUnitString and afterwards
     * the `escapeCsvMethod()` is called.
     *
     * Order is kept.
     *
     * @param mockUnit The supplied mockUnit
     *
     * @return (same object)
     */
    public CSVs column(MockUnit mockUnit) {
        notNull(mockUnit, "mockUnit");
        columns.add(unit(mockUnit));
        return this;
    }

    /**
     * Add a new column to resulting CVS line with a constant value.
     * The toString() method of the supplied object is invoked.
     *
     * Order is kept.
     *
     * @param value The constant value used
     *
     * @return (same object)
     */
    public CSVs column(Object value) {
        notNull(value, "value");
        columns.add(constant(value));
        return this;
    }

    @Override
    public Supplier<String> supplier() {
        isTrue(!columns.isEmpty(), EMPTY_CSV_NO_COLUMNS);
        return () -> {
            StringBuilder buff = new StringBuilder();
            columns.stream().forEach(v -> {
                buff.append(StringEscapeUtils.escapeCsv(v.getStr()));
                buff.append(separator);
            });
            buff.delete(buff.length() - separator.length(), buff.length());
            return buff.toString();
        };
    }

    /**
     * Writes a csv file.
     *
     * If the specified location is not available for writing an UncheckedIOException is thrown.
     *
     * @param filePath The path were the csv content is written
     * @param numberOfLines The number of files to be contained in the csv file
     */
    public void write(String filePath, int numberOfLines) {
        write(filePath, numberOfLines, false);
    }

    public void write(Path path, int numberOfLines) {
        write(path, numberOfLines, false);
    }

    /**
     * Writes a csv file.
     *
     * If the specified location is not available for writing an UncheckedIOException is thrown.
     *
     * @param filePath The path were the csv content is written
     * @param numberOfLines The number of files to be contained in the csv file
     * @param append If true the lines are appended.
     */
    public void write(String filePath, int numberOfLines, boolean append) {
        notEmpty(filePath, "filePath");
        Path path = Paths.get(filePath);
        write(path, numberOfLines, append);
    }

    public void write(Path path, int numberOfLines, boolean append) {
        notNull(path, "path");
        isTrue(numberOfLines>0, NUMBER_OF_LINES_POSITIVE);
        try {
            StandardOpenOption[] ops = (append) ? (new StandardOpenOption[] { CREATE, WRITE, APPEND}) :
                                                  (new StandardOpenOption[] { CREATE, WRITE });
            Files.write(path, list(numberOfLines).val(), ops);
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Appends to a csv file
     *
     * If the specified location is not available for writing an UncheckedIOException is thrown.
     *
     * @param filePath The path were the csv content is written
     * @param numberOfLines The number of files to be contained in the csv file
     */
    public void append(String filePath, int numberOfLines) {
        write(filePath, numberOfLines, true);
    }

    public void append(Path path, int numberOfLines) {
        write(path, numberOfLines, true);
    }
}
