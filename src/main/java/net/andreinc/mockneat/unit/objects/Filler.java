package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitBase;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class Filler<T> extends MockUnitBase implements MockUnit<T> {

    private Supplier<T> supplier;
    Map<BiConsumer, MockUnit> setters = new HashMap<>();

    public Filler(MockNeat mockNeat, Supplier<T> supplier) {
        super(mockNeat);
        this.supplier = supplier;
    }

    public <R> Filler<T> setter(BiConsumer<T, R> setter, MockUnit<R> mockUnit) {
        setters.put(setter, mockUnit);
        return this;
    }

    @Override
    public Supplier<T> supplier() {
        return () -> {
            T object = supplier.get();
            setters.forEach((k,v) -> k.accept(object, v.val()));
            return object;
        };
    }


}
