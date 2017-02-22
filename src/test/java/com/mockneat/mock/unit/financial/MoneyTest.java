package com.mockneat.mock.unit.financial;

import org.junit.Test;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import static com.mockneat.mock.RandTestConstants.*;
import static com.mockneat.mock.unit.financial.Money.DEFAULT_LOWER;
import static com.mockneat.mock.unit.financial.Money.DEFAULT_UPPER;
import static com.mockneat.mock.utils.FunctUtils.loop;
import static java.text.NumberFormat.getCurrencyInstance;
import static java.util.Locale.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by andreinicolinciobanu on 21/02/2017.
 */
public class MoneyTest {

    @Test
    public void testMoney() throws Exception {
        NumberFormat nf = getCurrencyInstance(US);
        loop(MONEY_CYCLES, RANDS, r -> r.money().val(), m -> {
            try {
                Number n = nf.parse(m);
                Double d = n.doubleValue();
                assertTrue(d >= DEFAULT_LOWER && d <= DEFAULT_UPPER);
            } catch (ParseException e) {
                fail(e.getMessage());
            }
        });
    }

    @Test
    public void testMoneyBound() throws Exception {
        double bound = 100000.00;
        NumberFormat nf = getCurrencyInstance(US);
        loop(MONEY_CYCLES, RANDS, r -> r.money().bound(bound).val(), m -> {
            try {
                double d = nf.parse(m).doubleValue();
                assertTrue(d >= 0.0 && d <= bound);
            } catch (ParseException e) {
               fail(e.getMessage());
            }
        });
    }

    @Test
    public void testMoneyRange() throws Exception {
        double lower = 10.0;
        double upper = 20.0;
        NumberFormat nf = getCurrencyInstance(US);
        loop(MONEY_CYCLES, RANDS, r -> r.money().range(lower, upper).val(), m -> {
            try {
                double d = nf.parse(m).doubleValue();
                assertTrue(d >= lower && d <= upper);
            } catch (ParseException e) {
                fail(e.getMessage());
            }
        });
    }

    @Test
    public void testMoneyLocale() throws Exception {
        Locale[] locales = {FRANCE, GERMANY, ITALY, JAPAN, KOREA, CHINA, US, UK, CANADA};
        loop(MONEY_CYCLES, RANDS, r -> {
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
    public void testMoneyNullLocale() throws Exception {
        RAND.money().locale(null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoneyNegativeBound() throws Exception {
        RAND.money().bound(-5.0).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoneyIncorrectRange() throws Exception {
        RAND.money().range(-1.5, 10).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoneyIncorrectRange2() throws Exception {
        RAND.money().range(10, -1.5).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoneyIncorrectRange3() throws Exception {
        RAND.money().range(10, 10).val();
    }
}
