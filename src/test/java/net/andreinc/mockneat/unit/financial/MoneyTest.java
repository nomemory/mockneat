package net.andreinc.mockneat.unit.financial;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */

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
