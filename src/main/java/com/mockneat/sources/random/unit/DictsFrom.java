package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitString;
import com.mockneat.sources.random.utils.DictsUtils;
import com.mockneat.types.enums.DictType;

import java.util.List;

public class DictsFrom implements RandUnitString {

    private Rand rand;
    private DictType dictType;
    private DictsUtils utils;

    public DictsFrom(Rand rand, DictType type) {
        this.rand = rand;
        this.dictType = type;
        this.utils = DictsUtils.getInstance();
    }

    @Override
    public String val() {
        List<String> lines = utils.get(dictType);
        return rand.objs().from(lines).val();
    }

    @Override
    public Rand getRand() {
        return rand;
    }
}
