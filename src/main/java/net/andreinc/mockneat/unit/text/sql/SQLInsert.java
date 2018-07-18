package net.andreinc.mockneat.unit.text.sql;

import net.andreinc.mockneat.abstraction.MockValue;

import java.util.Map;

public class SQLInsert {

    private final String tableName;
    private final Map<String, String> columns;
    private String toString;

    public SQLInsert(String tableName, Map<String, String> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getValue(String column) {
        return columns.get(column);
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();

        buff.append("INSERT INTO ")
                .append(tableName)
                .append(" ")
                .append(getColumnsSection())
                .append("VALUES ").append("(");

        for(String str : columns.values()) {
            buff.append(str)
                .append(", ");
        }

        buff.delete(buff.length() -2, buff.length())
            .append(");");

        return buff.toString();
    }

    private String getColumnsSection() {
        final StringBuilder buff = new StringBuilder();
        buff.append("(");

        columns.keySet().forEach(col -> buff.append(col).append(", "));

        buff.delete(buff.length() - 2, buff.length())
            .append(") ");
        return buff.toString();
    }
}
