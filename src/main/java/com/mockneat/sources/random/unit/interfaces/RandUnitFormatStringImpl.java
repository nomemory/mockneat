package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.sources.random.Rand;
import com.mockneat.types.enums.StringFormatType;

import static com.mockneat.utils.NextUtils.checkStringFormatTypeNotNull;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class RandUnitFormatStringImpl implements RandUnitGeneric<String> {

    private Rand rand;
    private String val;
    private StringFormatType formatType;

    public RandUnitFormatStringImpl(Rand rand, StringFormatType formatType, String val) {
        this.rand = rand;
        this.val = val;
        this.formatType = formatType;
    }

    @Override
    public String val() {
        checkStringFormatTypeNotNull(formatType);
        return formatType.getFormatter().apply(this.val);
    }
}
