package net.andreinc.mockneat.unit.user;

import net.andreinc.mockneat.types.enums.UserNameType;
import org.junit.Test;

import static net.andreinc.mockneat.Constants.M;

public class UsersTest {

    @Test(expected = NullPointerException.class)
    public void testUsersTypeNull() {
        M.users().type(null).val();
    }

    @Test(expected = NullPointerException.class)
    public void testUsersTypesNull() {
        M.users().types((UserNameType[]) null).val();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUsersTypesEmpty() {
        M.users().types(new UserNameType[]{}).val();
    }
}
