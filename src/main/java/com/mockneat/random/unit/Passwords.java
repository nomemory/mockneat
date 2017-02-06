package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnit;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.PassStrengthType;

import static com.mockneat.types.enums.PassStrengthType.MEDIUM_PASSWORD;

/**
 * Created by andreinicolinciobanu on 31/01/2017.
 */
public class Passwords implements RandUnitString {

    private Rand rand;

    public Passwords(Rand rand) {
        this.rand = rand;
    }

    public RandUnit<String> ofTypes(PassStrengthType... types) {
        return new PasswordOfTypes(rand, types);
    }

    public RandUnit<String> ofType(PassStrengthType type) {
        return new PasswordOfTypes(rand, type);
    }

    @Override
    public String val() {
        return rand.passwords().ofType(MEDIUM_PASSWORD).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }
}
