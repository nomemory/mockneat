package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitFormatStringImpl;
import com.mockneat.sources.random.unit.interfaces.RandUnit;
import com.mockneat.sources.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.NameType;
import com.mockneat.types.enums.StringFormatType;

public class Names implements RandUnitString {

    private Rand rand;

    public Names(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        NameType nameType = rand.objs().from(NameType.class).val();
        DictType dictType = rand.objs().from(nameType.getDictionaries()).val();
        return rand.dicts().from(dictType).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }

    public RandUnitString ofTypes(NameType... types) {
        return new NamesOfTypes(rand, types);
    }

    public RandUnitString ofType(NameType type) {
        return new NamesOfTypes(rand, type);
    }
}
