package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.types.enums.PassStrengthType;

import static com.mockneat.types.enums.PassStrengthType.MEDIUM_PASSWORD;

/**
 * Created by andreinicolinciobanu on 31/01/2017.
 */
public class Passwords implements RandUnitGeneric<String> {

    private Rand rand;

    public Passwords(Rand rand) {
        this.rand = rand;
    }

    public PasswordOfTypes ofTypes(PassStrengthType... types) {
        return new PasswordOfTypes(rand, types);
    }

    public PasswordOfTypes ofType(PassStrengthType type) {
        return new PasswordOfTypes(rand, type);
    }

    @Override
    public String val() {
        return rand.passwords().ofType(MEDIUM_PASSWORD).val();
    }
}
