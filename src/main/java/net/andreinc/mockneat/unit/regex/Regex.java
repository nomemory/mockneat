package net.andreinc.mockneat.unit.regex;

import com.mifmif.common.regex.Generex;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.notNull;
import static net.andreinc.mockneat.utils.ValidationUtils.validRegex;

public class Regex implements MockUnitString {

    /**
     * <p>Returns a new {@code Regex} object that can be used to generate arbitrary text based on a certain regex pattern.</p>
     *
     * @param regex The regex pattern that the generated string needs to comply.
     * @return A new {@code Regex} object. The {@code Regex} class implements {@code MockUnitString}.
     */
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
