package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.types.enums.IPv4Type;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class IPv4s implements RandUnitGeneric<String> {

    private Rand rand;

    public IPv4s(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        return rand.ipv4s().ofTypes(IPv4Type.NO_CONSTRAINT).val();
    }

    public IPv4sOfTypes ofTypes(IPv4Type... types) {
        return new IPv4sOfTypes(rand, types);
    }

    public IPv4sOfTypes ofType(IPv4Type type) { return new IPv4sOfTypes(rand, type); }
}
