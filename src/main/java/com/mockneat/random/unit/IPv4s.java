package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.IPv4Type;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class IPv4s implements RandUnitString {

    private Rand rand;

    public IPv4s(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        return rand.ipv4s().ofTypes(IPv4Type.NO_CONSTRAINT).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }

    public RandUnitString ofTypes(IPv4Type... types) {
        return new IPv4sOfTypes(rand, types);
    }

    public RandUnitString ofType(IPv4Type type) { return new IPv4sOfTypes(rand, type); }
}
