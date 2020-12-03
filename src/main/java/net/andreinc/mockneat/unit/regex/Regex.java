package net.andreinc.mockneat.unit.regex;

import com.mifmif.common.regex.Generex;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.notNull;
import static net.andreinc.mockneat.utils.ValidationUtils.validRegex;

public class Regex implements MockUnitString {

    public static Regex regex(String regex) {
        return MockNeat.threadLocal().regex(regex);
    }

    private final String regExp;

    public Regex(String regExp) {
        this.regExp = regExp;
    }

    @Override
    public Supplier<String> supplier() {
        notNull(regExp, "regExp");
        validRegex(regExp);
        return () -> new Generex(regExp).random();
    }
}
