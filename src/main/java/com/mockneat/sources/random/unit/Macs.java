package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.types.enums.MACAddressFormatType;

import static com.mockneat.types.enums.MACAddressFormatType.COLON_EVERY_2_DIGITS;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class Macs implements RandUnitGeneric<String> {

    private Rand rand;

    public Macs(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        return rand.macs().format(COLON_EVERY_2_DIGITS).val();
    }

    public MacsFormat format(MACAddressFormatType type) {
        return new MacsFormat(rand, type);
    }
}
