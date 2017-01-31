package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.types.enums.MACAddressFormatType;

import java.util.stream.IntStream;

import static com.mockneat.utils.NextUtils.checkMacFormatTypeNotNull;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class MacsFormat implements RandUnitGeneric<String> {

    private Rand rand;
    private MACAddressFormatType formatType;

    public MacsFormat(Rand rand, MACAddressFormatType type) {
        this.rand = rand;
        this.formatType = type;
    }

    @Override
    public String val() {
        checkMacFormatTypeNotNull(formatType);
        StringBuilder buff = new StringBuilder();
        IntStream.range(0, 12).forEach(i -> formatType.getConsumer().consume(i, buff, this.rand));
        return buff.deleteCharAt(0).toString();
    }
}
