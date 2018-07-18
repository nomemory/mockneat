package net.andreinc.mockneat.unit.text.sql;

import java.util.Arrays;
import java.util.function.Function;

public class SQLEscapers {

    private static Function<String, String> DOUBLE_APOSTROPHE = new SQLEscaper(
            Arrays.asList(
                    new SQLEscaper.TextEscapeToken(
                            "'",
                            "'",
                            "''")
            )
    )::escape;

    public static class PostgreSQL {

        public static final Function<String, String> TEXT_$QUOTED =
                DOUBLE_APOSTROPHE.andThen((input) -> "'$quot$" + input + "$quot$'");
    }

    public static class MySQL {

        // Text Escaper for MYSQL
        public static final Function<String, String> TEXT_BACKSLASH = new SQLEscaper(
                Arrays.asList(
                        new SQLEscaper.TextEscapeToken(
                                "\u0000",
                                "\\x00",
                                "\\\\0"),
                        new SQLEscaper.TextEscapeToken(
                                "'",
                                "'",
                                "\\\\'"),
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
}
