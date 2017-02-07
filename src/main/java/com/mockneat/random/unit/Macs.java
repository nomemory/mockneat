package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.MACAddressFormatType;

import java.util.function.Supplier;
import java.util.stream.IntStream;

import static com.mockneat.types.enums.MACAddressFormatType.COLON_EVERY_2_DIGITS;
import static com.mockneat.utils.NextUtils.checkMacFormatTypeNotNull;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class Macs implements RandUnitString {

    private Rand rand;

    public Macs(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<String> supplier() {
        return type(COLON_EVERY_2_DIGITS).supplier();
    }

    public RandUnitString type(MACAddressFormatType type) {
        Supplier<String> supplier = () -> {
            checkMacFormatTypeNotNull(type);
            StringBuilder buff = new StringBuilder();
            IntStream.range(0, 12).forEach(i -> type.getConsumer().consume(i, buff, this.rand));
            return buff.deleteCharAt(0).toString();
        };
        return () -> supplier;
    }
}
