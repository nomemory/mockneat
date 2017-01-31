package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitFormatStringImpl;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.StringFormatType;

import static com.mockneat.utils.NextUtils.checkStringFormatTypeNotNull;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class CountriesISO2Type implements RandUnitGeneric<String> {

    private Rand rand;
    private StringFormatType formatType = StringFormatType.CAPITALIZED;

    public CountriesISO2Type(Rand rand, StringFormatType formatType) {
        this.rand = rand;
        this.formatType = formatType;
    }

    @Override
    public String val() {
        checkStringFormatTypeNotNull(formatType);
        return rand.dicts().from(DictType.COUNTRY_ISO_CODE_2).format(formatType).val();
    }

    public RandUnitFormatStringImpl format(StringFormatType formatType) {
        return new RandUnitFormatStringImpl(rand, formatType, val());
    }
}
