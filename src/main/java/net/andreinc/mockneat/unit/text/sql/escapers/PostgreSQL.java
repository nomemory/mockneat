package net.andreinc.mockneat.unit.text.sql.escapers;

import net.andreinc.mockneat.unit.text.sql.SQLEscaper;

import java.util.Arrays;
import java.util.function.Function;

import static net.andreinc.mockneat.unit.text.sql.escapers.Generic.DOUBLE_APOSTROPHE;

public class PostgreSQL {

    private PostgreSQL() {}

    // Text escaper using the $ QUOTE strategy
    public static final Function<String, String> TEXT_$_QUOTED =
            DOUBLE_APOSTROPHE
                    .andThen(input -> "$quot$" + input + "$quot$");

    // Text Escaper for PostgreSQL
    public static final Function<String, String> TEXT_BACKSLASH = new SQLEscaper(
            Arrays.asList(
                    new SQLEscaper.TextEscapeToken(
                            "\u0000",
                            "\\x00",
                            "\\\\0"),
                    new SQLEscaper.TextEscapeToken(
                            "'",
                            "'",
                            "''"),
                    new SQLEscaper.TextEscapeToken(
                            "\"",
                            "\"",
                            "\\\\\""
                    ),
                    new SQLEscaper.TextEscapeToken(
                            "\b",
                            "\\x08",
                            "\\\\b"
                    ),
                    new SQLEscaper.TextEscapeToken(
                            "\n",
                            "\\n",
                            "\\\\n"
                    ),
                    new SQLEscaper.TextEscapeToken(
                            "\r",
                            "\\r",
                            "\\\\r"
                    ),
                    new SQLEscaper.TextEscapeToken(
                            "\t",
                            "\\t",
                            "\\\\t"
                    ),
                    new SQLEscaper.TextEscapeToken(
                            "\u001A",
                            "\\x1A",
                            "\\\\Z"
                    ),
                    new SQLEscaper.TextEscapeToken(
                            "\\",
                            "\\\\",
                            "\\\\\\\\"
                    )
            )
    )::escape;
}
