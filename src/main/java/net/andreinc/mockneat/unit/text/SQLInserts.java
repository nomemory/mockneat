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

    private String tableName;
    private Map<String, Pair<MockValue, Function<String, String>>> columns = new LinkedHashMap<>();

    public SQLInserts(MockNeat mockNeat) {
        super(mockNeat);
    }

    public SQLInserts tableName(String tableName) {
        notEmpty(tableName, "tableName");
        this.tableName = tableName;
        return this;
    }

    public SQLInserts column(String column, MockUnit mockUnit) {
        notEmpty(column, "column");
        notNull(mockUnit, "mockUnit");
        columns.put(column, Pair.of(unit(mockUnit.mapToString()), null));
        return this;
    }

    public SQLInserts column(String column, MockUnit mockUnit, Function<String, String> sqlFormatter) {
        notEmpty(column, "column");
        notNull(mockUnit, "mockUnit");
        notNull(sqlFormatter, "sqlFormatter");
        columns.put(column, Pair.of(unit(mockUnit.mapToString()), sqlFormatter));
        return this;
    }

    public SQLInserts column(String column, String str) {
        notEmpty(column, "column");
        notNull(str, "str");
        columns.put(column, Pair.of(constant(str), null));
        return this;
    }


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

    public MockUnit<SQLTable> table(int numRows) {
        Supplier<SQLTable> supp = () -> {
            List<SQLInsert> inserts = this.list(numRows).val();
            return new SQLTable(mockNeat, inserts);
        };
        return () -> supp;
    }
}
