package net.andreinc.mockneat.unit.financial;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitBase;
import net.andreinc.mockneat.abstraction.MockUnitString;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Supplier;

import static java.text.NumberFormat.getCurrencyInstance;
import static java.util.Locale.US;
import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Money extends MockUnitBase implements MockUnitString {

    public static final double DEFAULT_LOWER = 0.0;
    public static final double DEFAULT_UPPER = 10000.0;

    private NumberFormat formatter = getCurrencyInstance(US);

    public static Money money() { return MockNeat.threadLocal().money(); }

    public Money() { }

    public Money(MockNeat mockNeat) {
        super(mockNeat);
    }

    public Money locale(Locale locale) {
        notNull(locale, "locale");
        this.formatter = getCurrencyInstance(locale);
        return this;
    }

    public MockUnitString range(double lowerBound, double upperBound) {
        return () -> mockNeat.doubles()
                         .range(lowerBound, upperBound)
                         .mapToString(formatter::format).supplier();
    }

    public MockUnitString bound(double bound) {
        return () -> mockNeat.doubles()
                            .bound(bound)
                            .mapToString(formatter::format)::val;
    }

    @Override
    public Supplier<String> supplier() {
        return range(DEFAULT_LOWER, DEFAULT_UPPER).supplier();
    }
}
