package net.andreinc.mockneat.unit.objects;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitBase;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static net.andreinc.mockneat.utils.ValidationUtils.notNull;

public class Filler<T> extends MockUnitBase implements MockUnit<T> {

    private Supplier<T> supplier;

    //TODO: Change this horrible way
    Map<BiConsumer, MockUnit> setters = new LinkedHashMap<>();

    public Filler(MockNeat mockNeat, Supplier<T> supplier) {
        super(mockNeat);
        notNull(supplier, "supplier");
        this.supplier = supplier;
    }

    public <R> Filler<T> setter(BiConsumer<T, R> setter, MockUnit<R> mockUnit) {
        notNull(setter, "setter");
        notNull(mockUnit, "mockUnit");
        setters.put(setter, mockUnit);
        return this;
    }

    @Override
    public Supplier<T> supplier() {
        return () -> {
            T object = supplier.get();
            notNull(object, "supplier");
            setters.forEach((k,v) ->  k.accept(object, v.val()));
            return object;
        };
    }
}
