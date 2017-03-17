package net.andreinc.mockneat.unit.user;

import net.andreinc.mockneat.types.enums.UserNameType;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.M;

/**
 * Created by andreinicolinciobanu on 18/02/2017.
 */
public class UsersTest {

    @Test(expected = NullPointerException.class)
    public void testUsersTypeNull() throws Exception {
        M.users().type(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testUsersTypesNull() throws Exception {
        UserNameType[] types = null;
        M.users().types(types).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUsersTypesEmpty() throws Exception {
        M.users().types(new UserNameType[]{}).val();
    }
}
