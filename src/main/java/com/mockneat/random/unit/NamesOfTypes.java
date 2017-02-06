package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.NameType;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class NamesOfTypes implements RandUnitString {

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

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
