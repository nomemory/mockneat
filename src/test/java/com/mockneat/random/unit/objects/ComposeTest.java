package com.mockneat.random.unit.objects;

import com.mockneat.random.unit.objects.models.EmptyClass;
import com.mockneat.random.unit.objects.models.PlainOldJavaObject;
import com.mockneat.random.unit.objects.models.PrivateConstructors;
import com.mockneat.types.Pair;
import org.junit.Test;

import static com.mockneat.random.RandTestConstants.COMPOSE_CYCLES;
import static com.mockneat.random.RandTestConstants.RAND;
import static com.mockneat.random.RandTestConstants.RANDS;
import static com.mockneat.random.utils.FunctUtils.loop;
import static java.util.Arrays.stream;
import static org.junit.Assert.assertTrue;

/**
 * Created by andreinicolinciobanu on 08/02/2017.
 */
public class ComposeTest {

    @Test(expected = NullPointerException.class)
    public void testComposeNullPairs() throws Exception {
        Pair[] pairs = null;
        RAND.compose(pairs).object(EmptyClass.class).val();
    }

    @Test(expected = NullPointerException.class)
    public void testComposeNullObject() throws Exception {
        RAND.compose().object(null).val();
    }

    @Test
    public void testComposeEmptyClass() throws Exception {
        EmptyClass empty = RAND.compose().object(EmptyClass.class).val();
        assert (null != empty && empty instanceof EmptyClass);
    }

    @Test
    public void testComposeNullSupplier() {
        loop(COMPOSE_CYCLES, RANDS, r -> {
            PlainOldJavaObject a = r.compose(
                Pair.of(null, Integer.class),
                Pair.of(RAND.names()::val, String.class),
                Pair.of(RAND.doubles()::val, Double.class)
            ).object(PlainOldJavaObject.class).val();
            assertTrue(a.getX() == null);
        });
    }

    @Test(expected = NullPointerException.class)
    public void testComposeNullClass() {
        RAND.compose(
                Pair.of(RAND.ints()::val, null),
                Pair.of(RAND.names()::val, String.class),
                Pair.of(RAND.doubles()::val, Double.class)
        ).object(PlainOldJavaObject.class).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testComposeInvalidConstructor() {
        RAND.compose(Pair.of(null, String.class))
                .object(PlainOldJavaObject.class).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testComposePrivateConsuctor() {
        RAND.compose(Pair.of(null, Integer.class))
                .object(PrivateConstructors.class).val();
    }

    @Test
    public void testCompose() {
        loop(COMPOSE_CYCLES, RANDS, r -> r.compose(
                    Pair.of(RAND.ints()::val, Integer.class),
                    Pair.of(RAND.emails()::val, String.class),
                    Pair.of(RAND.doubles()::val, Double.class)
                ).object(PlainOldJavaObject.class).val(),
                p -> assertTrue(null != p.getX() && null != p.getY() && null != p.getZ()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testComposeWrongSupplierTypes() {
        RAND.compose(
                Pair.of(RAND.ints()::val, String.class),
                Pair.of(RAND.emails()::val, String.class),
                Pair.of(RAND.doubles()::val, Double.class)
        ).object(PlainOldJavaObject.class).val();

    }
}
