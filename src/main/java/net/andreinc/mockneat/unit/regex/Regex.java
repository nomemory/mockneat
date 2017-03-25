package net.andreinc.mockneat.unit.regex;

import com.mifmif.common.regex.Generex;
import net.andreinc.mockneat.interfaces.MockUnitString;

import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.INPUT_PARAMETER_NOT_NULL;
import static net.andreinc.mockneat.utils.ValidationUtils.validRegex;
import static org.apache.commons.lang3.Validate.notNull;

//TODO add documentation
public class Regex implements MockUnitString {

    private String regex;

    public Regex(String regex) {
        this.regex = regex;
    }

    @Override
    public Supplier<String> supplier() {
        notNull(regex, INPUT_PARAMETER_NOT_NULL, "regex");
        validRegex(regex);
        return () -> new Generex(regex).random();
    }
}
