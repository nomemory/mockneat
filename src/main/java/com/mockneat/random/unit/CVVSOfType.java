package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.CVVType;
import com.mockneat.utils.FunctUtils;

import static com.mockneat.utils.NextUtils.checkCVVTypeNotNull;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class CVVSOfType implements RandUnitString {

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

    @Override
    public Rand getRand() { return rand; }
}
