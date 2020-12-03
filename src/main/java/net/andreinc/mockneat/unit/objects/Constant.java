package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;

import java.util.function.Supplier;

public class Constant<T> implements MockUnit<T> {

    public static <T> Constant<T> constant(T object) { return MockNeat.threadLocal().constant(object); }

    private final T object;

    public Constant(T object) {
        this.object = object;
    }

    @Override
    public Supplier<T> supplier() {
        return () -> object;
    }

}
