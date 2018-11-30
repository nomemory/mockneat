package net.andreinc.mockneat.unit.text;

/**
 * Copyright 2018, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. PARAM NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER PARAM AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */


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
    private final Map<String, Pair<MockValue, Function<String, String>>> columns = new LinkedHashMap<>();

    public static SQLInserts sqlInserts() {
        return MockNeat.threadLocal().sqlInserts();
    }

    protected SQLInserts() { }

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
