package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.types.enums.CVVType;
import com.mockneat.utils.FunctUtils;

import static com.mockneat.utils.NextUtils.checkCVVTypeNotNull;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class CVVSOfType implements RandUnitGeneric<String> {

    private Rand rand;
    private CVVType cvvType;

    public CVVSOfType(Rand rand, CVVType cvvType) {
        this.rand = rand;
        this.cvvType = cvvType;
    }

    @Override
    public String val() {
        checkCVVTypeNotNull(cvvType);
        final StringBuilder builder = new StringBuilder();
        FunctUtils.cycle(cvvType.getLength(), () ->
                builder.append(rand.chars().digits().val()));
        return builder.toString();
    }
}
