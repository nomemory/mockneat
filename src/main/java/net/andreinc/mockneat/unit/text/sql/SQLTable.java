package net.andreinc.mockneat.unit.text.sql;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.*;

public class SQLTable {

    private final List<SQLInsert> SQLInserts;
    private final MockNeat mockNeat;


    public SQLTable(MockNeat mockNeat, List<SQLInsert> SQLInserts) {
        this.SQLInserts = SQLInserts;
        this.mockNeat = mockNeat;
    }

    public MockUnitString fromColumn(String columnName) {
        return mockNeat
                .from(SQLInserts)
                .mapToString(SQLInsert -> SQLInsert.getValue(columnName));
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();
        SQLInserts.stream()
                  .map(SQLInsert::toString)
                  .forEach(sql -> buff.append(sql).append("\n"));
        return buff.toString();
    }
}
