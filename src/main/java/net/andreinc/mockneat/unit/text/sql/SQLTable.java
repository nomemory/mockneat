package net.andreinc.mockneat.unit.text.sql;

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
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class SQLTable {

    private final List<SQLInsert> inserts;
    private final MockNeat mockNeat;


    public SQLTable(MockNeat mockNeat, List<SQLInsert> inserts) {
        notNull(mockNeat, "mockNeat");
        notNull(inserts, "inserts");
        this.inserts = inserts;
        this.mockNeat = mockNeat;
    }

    public MockUnitString fromColumn(String columnName) {
        notEmpty(columnName, "columnName");
        return mockNeat
                .from(inserts)
                .mapToString(insert -> insert.getValue(columnName));
    }

    public Optional<SQLInsert> selectFirstWhere(Predicate<SQLInsert> condition) {
        notNull(condition, "condition");
        return inserts.stream().filter(condition).findFirst();
    }

    public List<SQLInsert> selectWhere(Predicate<SQLInsert> condition) {
        notNull(condition, "condition");
        return inserts.stream().filter(condition).collect(Collectors.toList());
    }

    public SQLInsert selectRow(int row) {
        isTrue(row>=0, ROW_POSITIVE_VALUE);
        return inserts.get(row);
    }

    public SQLTable update(int row, String column, String newValue) {
        isTrue(row>=0, ROW_POSITIVE_VALUE);
        notEmpty(column, "column");
        notEmpty(newValue, "newValue");
        inserts.get(row)
               .setValue(column, newValue);
        return this;
    }

    public SQLTable update(int row, Consumer<SQLInsert> updater) {
        isTrue(row>=0, ROW_POSITIVE_VALUE);
        notNull(updater, "updater");
        updater.accept(inserts.get(row));
        return this;
    }

    public SQLTable updateAll(BiConsumer<Integer, SQLInsert> updater) {
        notNull(updater, "updater");
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
