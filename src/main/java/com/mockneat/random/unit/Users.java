package com.mockneat.random.unit;

import com.mockneat.random.Rand;
import com.mockneat.random.unit.interfaces.RandUnitString;
import com.mockneat.types.enums.UserNameType;

import static com.mockneat.types.enums.StringFormatType.LOWER_CASE;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class Users implements RandUnitString {

    private Rand rand;

    public Users(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        UserNameType type = rand.objs().from(UserNameType.class).val();
        return rand.users().ofType(type).format(LOWER_CASE).val();
    }

    @Override
    public Rand getRand() {
        return this.rand;
    }

    public RandUnitString ofTypes(UserNameType... types) {
        return new UsersOfTypes(rand, types);
    }

    public RandUnitString ofType(UserNameType type) {
        return new UsersOfTypes(rand, type);
    }

}
