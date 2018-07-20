package net.andreinc.mockneat.unit.text.sql.escapers;

import net.andreinc.mockneat.unit.text.sql.SQLEscaper;

import java.util.Arrays;
import java.util.function.Function;

public class MySQL {
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
