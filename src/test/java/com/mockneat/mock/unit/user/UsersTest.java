package com.mockneat.mock.unit.user;

import com.mockneat.types.enums.UserNameType;
import org.junit.Test;

import static com.mockneat.mock.Constants.RAND;

/**
 * Created by andreinicolinciobanu on 18/02/2017.
 */
public class UsersTest {

    @Test(expected = NullPointerException.class)
    public void testUsersTypeNull() throws Exception {
        RAND.users().type(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testUsersTypesNull() throws Exception {
        UserNameType[] types = null;
        RAND.users().types(types).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUsersTypesEmpty() throws Exception {
        RAND.users().types(new UserNameType[]{}).val();
    }
}
