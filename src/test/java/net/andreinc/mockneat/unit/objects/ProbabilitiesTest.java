package net.andreinc.mockneat.unit.objects;

import org.junit.Test;

import static net.andreinc.mockneat.Constants.M;

/**
 * Created by andreinicolinciobanu on 06/05/17.
 */
public class ProbabilitiesTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNegative() throws Exception {
        M.probabilites(Integer.class)
                .add(-0.5, M.ints().bound(10))
                .add(1.5, M.ints().range(10, 20))
                .val();
    }

    @Test(expected = NullPointerException.class)
    public void testNull() throws Exception {
        M.probabilites(Integer.class)
                .add(null, M.ints().bound(10))
                .val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumNot0() throws Exception {
        M.probabilites(Integer.class)
                .add(0.2, M.fromInts(new int[]{1,2,3}))
                .add(0.5, M.fromInts(new int[]{4,5,6}))
                .val();
    }
}
