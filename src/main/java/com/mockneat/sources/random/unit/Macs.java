package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnit;
import com.mockneat.types.enums.MACAddressFormatType;

import static com.mockneat.types.enums.MACAddressFormatType.COLON_EVERY_2_DIGITS;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class Macs implements RandUnit<String> {

    private Rand rand;

    public Macs(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        return rand.macs().format(COLON_EVERY_2_DIGITS).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }

    public RandUnit<String> format(MACAddressFormatType type) {
        return new MacsFormat(rand, type);
    }
}
