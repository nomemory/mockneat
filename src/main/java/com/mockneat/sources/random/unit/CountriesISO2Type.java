package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitFormatStringImpl;
import com.mockneat.sources.random.unit.interfaces.RandUnit;
import com.mockneat.sources.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.DictType;
import com.mockneat.types.enums.StringFormatType;

import static com.mockneat.utils.NextUtils.checkStringFormatTypeNotNull;

/**
 * Created by andreinicolinciobanu on 25/01/2017.
 */
public class CountriesISO2Type implements RandUnitString {

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

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
