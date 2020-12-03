package net.andreinc.mockneat.unit.financial;

import net.andreinc.mockneat.types.enums.CurrencySymbolType;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static net.andreinc.mockneat.Constants.CURRENCIES_CYCLES;
import static net.andreinc.mockneat.Constants.MOCKS;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;

public class CurrenciesTest {
    private static final Set<String> CODES = new HashSet<>();
    private static final Set<String> SYMBOLS = new HashSet<>();
    private static final Set<String> NAMES = new HashSet<>();

    static {
        Arrays.stream(CurrencySymbolType.values()).forEach(v -> {
            CODES.add(v.getCode());
            SYMBOLS.add(v.getSymbol());
            NAMES.add(v.getName());
        });
    }

    @Test
    public void testCodes() {
        loop(CURRENCIES_CYCLES, MOCKS, r -> r.currencies().code().val(), c -> assertTrue(CODES.contains(c)));
    }

    @Test
    public void testSymbol() {
        loop(CURRENCIES_CYCLES, MOCKS, r -> r.currencies().symbol().val(), c -> assertTrue(SYMBOLS.contains(c)));
    }

    @Test
    public void testName() {
        loop(CURRENCIES_CYCLES, MOCKS, r -> r.currencies().name().val(), c -> assertTrue(NAMES.contains(c)));
    }

    @Test
    public void testForexPair() {
        loop(CURRENCIES_CYCLES, MOCKS, r -> r.currencies().forexPair().val(), c -> {
            String[] pairs = c.split("/");
            assertTrue(CODES.contains(pairs[0]));
            assertTrue(CODES.contains(pairs[1]));
        });
    }
}