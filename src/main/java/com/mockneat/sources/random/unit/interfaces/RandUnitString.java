package com.mockneat.sources.random.unit.interfaces;

import com.mockneat.types.enums.StringFormatType;

/**
 * Created by andreinicolinciobanu on 03/02/2017.
 */
public interface RandUnitString extends RandUnit<String> {

    default RandUnitString format(StringFormatType formatType) {
        return new RandUnitStringFormatImpl(getRand(), formatType, val());
    }

    default RandUnitString cut(Integer maxLength) {
        return new RandUnitStringCutImpl(getRand(),maxLength, val());
    }

}
