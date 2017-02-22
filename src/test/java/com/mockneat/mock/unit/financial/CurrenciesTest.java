package com.mockneat.mock.unit.financial;

import com.mockneat.types.enums.CurrencySymbolType;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.mockneat.mock.RandTestConstants.CURRENCIES_CYCLES;
import static com.mockneat.mock.RandTestConstants.RANDS;
import static com.mockneat.mock.utils.FunctUtils.loop;
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
    public void testCodes() throws Exception {
        loop(CURRENCIES_CYCLES, RANDS, r -> r.currencies().code().val(), c -> assertTrue(CODES.contains(c)));
    }

    @Test
    public void testSymbol() throws Exception {
        loop(CURRENCIES_CYCLES, RANDS, r -> r.currencies().symbol().val(), c -> assertTrue(SYMBOLS.contains(c)));
    }

    @Test
    public void testName() throws Exception {
        loop(CURRENCIES_CYCLES, RANDS, r -> r.currencies().name().val(), c -> assertTrue(NAMES.contains(c)));
    }

    @Test
    public void testForexPair() throws Exception {
        loop(CURRENCIES_CYCLES, RANDS, r -> r.currencies().forexPair().val(), c -> {
            String[] pairs = c.split("/");
            assertTrue(CODES.contains(pairs[0]));
            assertTrue(CODES.contains(pairs[1]));
        });
    }
}