package net.andreinc.mockneat.unit.text.sql.escapers;

import net.andreinc.mockneat.unit.text.sql.SQLEscaper;

import java.util.Arrays;
import java.util.function.Function;

public class Generic {
    public static Function<String, String> DOUBLE_APOSTROPHE = new SQLEscaper(
            Arrays.asList(
                    new SQLEscaper.TextEscapeToken(
                            "'",
                            "'",
                            "''")
            )
    )::escape;
}
