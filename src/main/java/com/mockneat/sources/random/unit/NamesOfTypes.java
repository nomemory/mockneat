package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitFormatStringImpl;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.NameType;
import com.mockneat.types.enums.StringFormatType;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class NamesOfTypes implements RandUnitGeneric<String> {

    private Rand rand;
    private NameType[] types;

    public NamesOfTypes(Rand rand, NameType... types) {
        this.rand = rand;
        this.types = types;
    }

    @Override
    public String val() {
        //TODO validate types!!
        NameType nameType = rand.objs().from(types).val();
        DictType dictType = rand.objs().from(nameType.getDictionaries()).val();
        return rand.dicts().from(dictType).val();
    }

    public RandUnitFormatStringImpl format(StringFormatType formatType) {
        return new RandUnitFormatStringImpl(rand, formatType, val());
    }
}
