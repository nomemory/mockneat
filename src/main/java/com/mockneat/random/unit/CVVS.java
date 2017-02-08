package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.CVVType;
import com.mockneat.utils.FunctUtils;

import java.util.function.Supplier;

import static com.mockneat.types.enums.CVVType.CVV3;
import static com.mockneat.utils.CheckUtils.checkCVVTypeNotNull;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class CVVS implements RandUnitString {

    private Rand rand;

    public CVVS(Rand rand) {
        this.rand = rand;
    }

    @Override
    public Supplier<String> supplier() {
        return type(CVV3).supplier();
    }

    public RandUnitString type(CVVType type) {
        checkCVVTypeNotNull(type);
        Supplier<String> supplier = () -> {
            final StringBuilder builder = new StringBuilder();
            FunctUtils.cycle(type.getLength(), () ->
                    builder.append(rand.chars().digits().val()));
            return builder.toString();
        };
        return () -> supplier;
    }
}
