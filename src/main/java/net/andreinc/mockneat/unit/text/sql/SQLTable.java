package net.andreinc.mockneat.unit.text.sql;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitString;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;

public class SQLTable {

    private final List<SQLInsert> inserts;
    private final MockNeat mockNeat;


    public SQLTable(MockNeat mockNeat, List<SQLInsert> inserts) {
        this.inserts = inserts;
        this.mockNeat = mockNeat;
    }

    public MockUnitString fromColumn(String columnName) {
        return mockNeat
                .from(inserts)
                .mapToString(SQLInsert -> SQLInsert.getValue(columnName));
    }

    public Optional<SQLInsert> selectFirstWhere(Predicate<SQLInsert> condition) {
        return inserts.stream().filter(condition).findFirst();
    }

    public List<SQLInsert> selectWhere(Predicate<SQLInsert> condition) {
        return inserts.stream().filter(condition).collect(Collectors.toList());
    }

    public SQLInsert selectRow(int row) {
        return inserts.get(row);
    }

    public SQLTable update(int row, String column, String newValue) {
        inserts.get(row)
               .setValue(column, newValue);
        return this;
    }

    public SQLTable update(int row, Consumer<SQLInsert> updater) {
        updater.accept(inserts.get(row));
        return this;
    }

    public SQLTable updateAll(BiConsumer<Integer, SQLInsert> updater) {
        range(0, inserts.size())
                .forEach(i -> updater.accept(i, inserts.get(i)));
        return this;
    }

    public int size() {
        return inserts.size();
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();
        inserts.stream()
                  .map(SQLInsert::toString)
                  .forEach(sql -> buff.append(sql).append("\n"));
        return buff.toString();
    }
}
