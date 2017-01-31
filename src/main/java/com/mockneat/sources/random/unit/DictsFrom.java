package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitFormatStringImpl;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.sources.random.utils.DictsUtils;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.StringFormatType;

import java.util.List;

import static com.mockneat.utils.StringUtils.lowerCase;

public class DictsFrom implements RandUnitGeneric<String> {

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
        return lowerCase(rand.objs().from(lines).val());
    }

    public RandUnitFormatStringImpl format(StringFormatType type) {
        return new RandUnitFormatStringImpl(rand, type, val());
    }

}
