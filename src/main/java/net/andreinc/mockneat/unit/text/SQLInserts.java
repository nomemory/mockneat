package net.andreinc.mockneat.unit.text;


import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.*;
import net.andreinc.mockneat.types.Pair;
import net.andreinc.mockneat.unit.text.sql.SQLInsert;
import net.andreinc.mockneat.unit.text.sql.SQLTable;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.andreinc.mockneat.abstraction.MockConstValue.constant;
import static net.andreinc.mockneat.abstraction.MockUnitValue.unit;
import static net.andreinc.mockneat.utils.ValidationUtils.notEmpty;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class SQLInserts extends MockUnitBase implements MockUnit<SQLInsert> {

    private String tableName = "some_table";
    private final Map<String, Pair<MockValue<?>, Function<String, String>>> columns = new LinkedHashMap<>();

    /**
     * <p>Returns a {@code SQLInserts} object that can be used to generate SQL Inserts</p>
     *
     * @return A re-usable {@code SQLInserts} object. This class implements {@code MockUnit<SQLInsert>}.
     */
    public static SQLInserts sqlInserts() {
        return MockNeat.threadLocal().sqlInserts();
    }

    protected SQLInserts() { }

    public SQLInserts(MockNeat mockNeat) {
        super(mockNeat);
    }

    /**
     * <p>Sets the table name for which we are generating the SQLInserts.</p>
     *
     * <p>If this is not called, the default table name "some_table" will be used for the INSERTs.</p>
     *
     * @param tableName The table name
     * @return The same {@code SQLInserts} object
     */
    public SQLInserts tableName(String tableName) {
        notEmpty(tableName, "tableName");
        this.tableName = tableName;
        return this;
    }

    /**
     * <p>Associates a column of the SQL table with a corresponding {@code MockUnit<T>}</p>
     *
     * @param column The column of the table
     * @param mockUnit The {@code MockUnit<T>} used to generate values for the column. The result is automatically transformed to String.
     * @return The same {@code SQLInserts} object.
     */
    public SQLInserts column(String column, MockUnit<?> mockUnit) {
        notEmpty(column, "column");
        notNull(mockUnit, "mockUnit");
        columns.put(column, Pair.of(unit(mockUnit.mapToString()), null));
        return this;
    }

    /**
     * <p>Associates a column of the SQL table with a corresponding {@code MockUnit<T>}. </p>
     *
     * <p>Before the value is generated from the {@code MockUnit<T>} the {@param sqlParameter} function is applied to the values. This is useful when for example Strings have to be escaped for a given SQL dialect.</p>
     *
     * @param column The column of the table
     * @param mockUnit The {@code MockUnit<T>} used to generate values for the column. The result is automatically transformed to String.
     * @param sqlFormatter The {@code Function<String, String>} for formatting the generated value
     * @return
     */
    public SQLInserts column(String column, MockUnit<?> mockUnit, Function<String, String> sqlFormatter) {
        notEmpty(column, "column");
        notNull(mockUnit, "mockUnit");
        notNull(sqlFormatter, "sqlFormatter");
        columns.put(column, Pair.of(unit(mockUnit.mapToString()), sqlFormatter));
        return this;
    }

    /**
     * <p>Associates a column of the SQL table with a constant value.</p>
     *
     * @param column The column of the SQLTable
     * @param str The constant {@code String} value.
     * @return The same object
     */
    public SQLInserts column(String column, String str) {
        notEmpty(column, "column");
        notNull(str, "str");
        columns.put(column, Pair.of(constant(str), null));
        return this;
    }


    /**
     * <p>Associates a column of the SQL table with a constant value.</p>
     *
     * <p>Before the value is generated from the {@code MockUnit<T>} the {@param sqlParameter} function is applied to the values. This is useful when for example Strings have to be escaped for a given SQL dialect.</p>
     *
     * @param column The column of the SQLTable
     * @param str The constant {@code String} value.
     * @param sqlFormatter The {@code Function<String, String>} for formatting the constant value
     * @return The same {@code SQLInserts} object.
     */
    public SQLInserts column(String column, String str, Function<String, String> sqlFormatter) {
        notEmpty(column, "column");
        notNull(str, "str");
        notNull(sqlFormatter, "sqlFormatter");
        columns.put(column,Pair.of(constant(str), sqlFormatter));
        return this;
    }

    @Override
    public Supplier<SQLInsert> supplier() {
        return () -> {
            final Map<String, Pair<String, Function<String, String>>> values = new LinkedHashMap<>();
            columns.forEach((k, v) ->
                    values.put(k, Pair.of(v.getFirst().getStr(), v.getSecond())));
            return new SQLInsert(tableName, values);
        };
    }

    /**
     * <p>Creates a {@code MockUnit<SQLTable>}, each table having a fixed number of rows</p>
     * @param numRows The number of rows of the table.
     * @return The same {@code SQLInserts} object.
     */
    public MockUnit<SQLTable> table(int numRows) {
        Supplier<SQLTable> supp = () -> {
            List<SQLInsert> inserts = this.list(numRows).val();
            return new SQLTable(mockNeat, inserts);
        };
        return () -> supp;
    }
}
