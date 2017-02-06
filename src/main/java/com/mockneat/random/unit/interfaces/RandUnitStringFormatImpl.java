package com.mockneat.random.unit.interfaces;

import com.mockneat.random.Rand;
import com.mockneat.types.enums.StringFormatType;

import java.util.function.Supplier;

import static com.mockneat.utils.NextUtils.checkStringFormatTypeNotNull;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class RandUnitStringFormatImpl implements RandUnitString {

    private Rand rand;
    private Supplier<String> val;
    private StringFormatType formatType;

    public RandUnitStringFormatImpl(Rand rand, StringFormatType formatType, Supplier<String> val) {
        this.rand = rand;
        this.val = val;
        this.formatType = formatType;
    }

    @Override
    public String val() {
        checkStringFormatTypeNotNull(formatType);
        return formatType.getFormatter().apply(val.get());
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
