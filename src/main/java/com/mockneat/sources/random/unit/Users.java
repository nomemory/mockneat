package com.mockneat.sources.random.unit;

import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.unit.interfaces.RandUnitGeneric;
import com.mockneat.sources.random.unit.interfaces.RandUnitFormatStringImpl;
import com.mockneat.types.enums.StringFormatType;
import com.mockneat.types.enums.UserNameType;

import static com.mockneat.types.enums.StringFormatType.LOWER_CASE;

/**
 * Created by andreinicolinciobanu on 27/01/2017.
 */
public class Users implements RandUnitGeneric<String> {

    private Rand rand;

    public Users(Rand rand) {
        this.rand = rand;
    }

    @Override
    public String val() {
        UserNameType type = rand.objs().from(UserNameType.class).val();
        return rand.users().ofType(type).format(LOWER_CASE).val();
    }

    public UsersOfTypes ofTypes(UserNameType... types) {
        return new UsersOfTypes(rand, types);
    }

    public UsersOfTypes ofType(UserNameType type) {
        return new UsersOfTypes(rand, type);
    }

    public RandUnitFormatStringImpl format(StringFormatType formatType) {
        return new RandUnitFormatStringImpl(rand, formatType, val());
    }
}
