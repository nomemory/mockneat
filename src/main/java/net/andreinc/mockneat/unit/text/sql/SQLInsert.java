package net.andreinc.mockneat.unit.text.sql;


import net.andreinc.mockneat.types.Pair;

import java.util.Map;
import java.util.function.Function;

import static net.andreinc.mockneat.utils.ValidationUtils.*;

public class SQLInsert {

    private final String tableName;
    private final Map<String, Pair<String, Function<String, String>>> columns;

    public SQLInsert(String tableName, Map<String, Pair<String, Function<String, String>>> columns) {
        notEmpty(tableName, "tableName");
        notNull(columns, "columns");
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getValue(String column) {
        notEmpty(column, "column");
        isTrue(columns.containsKey(column), COLUMN_DOESNT_EXISTS, "column", column, "table", tableName);
        return columns.get(column).getFirst();
    }

    public void setValue(String column, String value) {
        notEmpty(column, "column");
        notEmpty(value, "value");
        isTrue(columns.containsKey(column), COLUMN_DOESNT_EXISTS, "column", column, "table", tableName);
        Function<String, String> existingMethod = columns.get(column).getSecond();
        columns.put(column, Pair.of(value, existingMethod));
    }

    @Override
    public String toString() {

        StringBuilder buff = new StringBuilder(1024);

        buff.append("INSERT INTO ")
                .append(tableName)
                .append(' ')
                .append(getColumnsSection())
                .append("VALUES (");

        for(Pair<String, Function<String, String>> str : columns.values()) {
            String strVal = str.getFirst();

            if (str.getSecond() != null) {
                strVal = str.getSecond().apply(strVal);
            }

            buff.append(strVal)
                .append(", ");
        }

        buff.delete(buff.length() -2, buff.length())
            .append(");");

        return buff.toString();
    }

    private String getColumnsSection() {
        final StringBuilder buff = new StringBuilder();
        buff.append('(');

        columns.keySet().forEach(col -> buff.append(col).append(", "));

        buff.delete(buff.length() - 2, buff.length())
            .append(") ");
        return buff.toString();
    }
}
