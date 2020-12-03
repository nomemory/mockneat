package net.andreinc.mockneat.unit.financial;

import org.junit.Test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import static net.andreinc.mockneat.Constants.*;
import static java.text.NumberFormat.getCurrencyInstance;
import static java.util.Locale.*;
import static net.andreinc.mockneat.unit.financial.Money.DEFAULT_LOWER;
import static net.andreinc.mockneat.unit.financial.Money.DEFAULT_UPPER;
import static net.andreinc.mockneat.utils.LoopsUtils.loop;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MoneyTest {

    @Test
    public void testMoney() {
        NumberFormat nf = getCurrencyInstance(US);
        loop(MOCK_CYCLES, MOCKS, r -> r.money().locale(US).val(), m -> {
            try {
                Number n = nf.parse(m);
                double d = n.doubleValue();
                assertTrue(d >= DEFAULT_LOWER && d <= DEFAULT_UPPER);
            } catch (ParseException e) {
                fail(e.getMessage());
            }
        });
    }

    @Test
    public void testMoneyBound() {
        double bound = 100000.00;
        NumberFormat nf = getCurrencyInstance(US);
        loop(MONEY_CYCLES, MOCKS, r -> r.money().locale(US).bound(bound).val(), m -> {
            try {
                double d = nf.parse(m).doubleValue();
                assertTrue(d >= 0.0 && d <= bound);
            } catch (ParseException e) {
               fail(e.getMessage());
            }
        });
    }

    @Test
    public void testMoneyRange() {
        double lower = 10.0;
        double upper = 20.0;
        NumberFormat nf = getCurrencyInstance(US);
        loop(MONEY_CYCLES, MOCKS, r -> r.money().range(lower, upper).val(), m -> {
            try {
                double d = nf.parse(m).doubleValue();
                assertTrue(d >= lower && d <= upper);
            } catch (ParseException e) {
                fail(e.getMessage());
            }
        });
    }

    @Test
    public void testMoneyLocale() {
        Locale[] locales = {FRANCE, GERMANY, ITALY, JAPAN, KOREA, CHINA, US, UK, CANADA};
        loop(MONEY_CYCLES, MOCKS, r -> {
            Locale locale = r.from(locales).val();
            String money = r.money().locale(locale).val();
            NumberFormat nf = getCurrencyInstance(locale);
            try {
                double db = nf.parse(money).doubleValue();
                assertTrue(db >= DEFAULT_LOWER && db <= DEFAULT_UPPER);
            } catch (ParseException e) {
                fail(e.getMessage());
            }
        });
    }

    //
    // Incorrect params
    //

    @Test(expected = NullPointerException.class)
    public void testMoneyNullLocale() {
        M.money().locale(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoneyNegativeBound() {
        M.money().bound(-5.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoneyIncorrectRange() {
        M.money().range(-1.5, 10).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoneyIncorrectRange2() {
        M.money().range(10, -1.5).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoneyIncorrectRange3() {
        M.money().range(10, 10).val();
    }
}
