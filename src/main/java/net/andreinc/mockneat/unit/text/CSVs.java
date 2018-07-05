package net.andreinc.mockneat.unit.text;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.*;
import org.apache.commons.text.StringEscapeUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

import static net.andreinc.mockneat.abstraction.MockConstValue.constant;
import static net.andreinc.mockneat.abstraction.MockUnitValue.unit;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmpty;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class CSVs extends MockUnitBase implements MockUnitString {

    public CSVs(MockNeat mockNeat) {
        super(mockNeat);
    }

    private String separator = ",";
    private List<MockValue> columns = new LinkedList<>();

    public void separator(String separator) {
        notEmpty(separator, "separator");
        this.separator = separator;
    }

    /**
     * Add a new column to the resulting csv line.
     * Internally the supplied mockUnit is transformed to a MockUnitString and afterwards
     * the `escapeCsvMethod()` is called.
     * @param mockUnit The supplied mockUnit
     */
    public CSVs addColumn(MockUnit mockUnit) {
        notNull(mockUnit, "mockUnit");
        columns.add(unit(mockUnit.mapToString().escapeCsv()));
        return this;
    }

    /**
     * Add a new column to resulting CVS line with a constant value.
     * The toString() method of the supplied object is invoked.
     * @param value The constant value used
     */
    public CSVs addColumn(Object value) {
        notNull(value, "value");
        columns.add(constant(value));
        return this;
    }

    @Override
    public Supplier<String> supplier() {
        Supplier<String> supplier = () -> {
            StringBuilder buff = new StringBuilder();
            columns.stream().forEach((v) -> {
                if (v instanceof MockConstValue) {
                    buff.append(StringEscapeUtils.escapeCsv(v.getStr()));
                }
                else if (v instanceof MockUnitValue) {
                    buff.append(v.get());
                }
                buff.append(separator);
            });
            buff.delete(buff.length() - separator.length(), buff.length());
            return buff.toString();
        };
        return supplier;
    }

    public void toFile(String filePath, int numberOfLines) {

    }
}
