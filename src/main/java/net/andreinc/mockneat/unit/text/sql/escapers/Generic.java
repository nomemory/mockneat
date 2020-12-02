package net.andreinc.mockneat.unit.text.sql.escapers;

import net.andreinc.mockneat.unit.text.sql.SQLEscaper;

import java.util.Collections;
import java.util.function.UnaryOperator;

public class Generic {
    public static final UnaryOperator<String> DOUBLE_APOSTROPHE = new SQLEscaper(
            Collections.singletonList(
                    new SQLEscaper.TextEscapeToken(
                            "'",
                            "'",
                            "''")
            )
    )::escape;

    private Generic() {}
}