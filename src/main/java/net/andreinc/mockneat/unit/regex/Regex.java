package net.andreinc.mockneat.unit.regex;

import com.mifmif.common.regex.Generex;
import net.andreinc.mockneat.interfaces.MockUnitString;

import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.notNull;
import static net.andreinc.mockneat.utils.ValidationUtils.validRegex;

public class Regex implements MockUnitString {

    private String regex;

    public Regex(String regex) {
        this.regex = regex;
    }

    @Override
    public Supplier<String> supplier() {
        notNull(regex, "regex");
        validRegex(regex);
        return () -> new Generex(regex).random();
    }
}
