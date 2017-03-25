package net.andreinc.mockneat.unit.regex;

import com.mifmif.common.regex.Generex;
import net.andreinc.mockneat.interfaces.MockUnitString;

import java.util.function.Supplier;

/**
 * Created by andreinicolinciobanu on 25/03/17.
 */
public class FromRegex implements MockUnitString {

    private String regex;

    @Override
    public Supplier<String> supplier() {
        return () -> new Generex(regex).random();
    }
}
