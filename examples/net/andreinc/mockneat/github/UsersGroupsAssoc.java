package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.github.model.Group;
import net.andreinc.mockneat.github.model.User;

import java.util.List;

/**
 * Created by andreinicolinciobanu on 26/03/17.
 */
public class UsersGroupsAssoc {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        List<Group> groups = m.reflect(Group.class)
                                .field("id", m.longSeq())
                                .field("name", m.regex("[A-Z]{1}-[A-Z]{1}-[A-Z]{1}"))
                                .list(5)
                                .val();

        System.out.println(groups);

        List<User> users = m.reflect(User.class)
                            .field("id", m.longSeq())
                            .field("groupId", m.from(groups).map(Group::getId))
                            .field("email", m.emails())
                            .list(100)
                            .val();

        System.out.println(users);
    }
}
