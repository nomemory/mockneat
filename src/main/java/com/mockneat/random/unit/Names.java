package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.NameType;

import java.util.function.Supplier;

public class Names implements RandUnitString {

    private Rand rand;

    public Names(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<String> supplier() {
        return null;
    }

    public RandUnitString types(NameType... types) {
        Supplier<String> supplier = () -> {
            NameType nameType = rand.objs().from(types).val();
            DictType dictType = rand.objs().from(nameType.getDictionaries()).val();
            return rand.dicts().type(dictType).val();
        };
        return () -> supplier;
    }

    public RandUnitString type(NameType type) {
        return types(type);
    }
}
