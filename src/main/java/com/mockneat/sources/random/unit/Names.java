package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitFormatStringImpl;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.NameType;
import com.mockneat.types.enums.StringFormatType;

public class Names implements RandUnitGeneric<String> {

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

    public NamesOfTypes ofTypes(NameType... types) {
        return new NamesOfTypes(rand, types);
    }

    public NamesOfTypes ofType(NameType type) {
        return new NamesOfTypes(rand, type);
    }

    public RandUnitFormatStringImpl format(StringFormatType formatType) {
        return new RandUnitFormatStringImpl(rand, formatType, val());
    }
}
