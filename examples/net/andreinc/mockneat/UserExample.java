package net.andreinc.mockneat;

/**
 * Created by andreinicolinciobanu on 17/03/18.
 */
public class UserExample {
    public static void main(String[] args) {

        MockNeat m = MockNeat.threadLocal();

        User user1 = m.filler(() -> new User())
                     .setter(User::setUserName,m.users())
                     .setter(User::setFirstName, m.names().first())
                     .setter(User::setLastName, m.names().last())
                     .setter(User::setCreated, m.localDates().thisYear().toUtilDate())
                     .setter(User::setModified, m.localDates().thisMonth().toUtilDate())
                     .val();

        System.out.println(user1);

        User user2 = m.reflect(User.class)
                      .field("userName", m.users())
                      .field("firstName", m.names().first())
                      .field("lastName", m.names().last())
                      .field("created", m.localDates().thisYear().toUtilDate())
                      .field("modified", m.localDates().thisMonth().toUtilDate())
                      .val();

        System.out.println(user2);

        User user3 = m.constructor(User.class)
                      .params(
                        m.users(),
                        m.names().first(),
                        m.names().last(),
                        m.localDates().thisYear().toUtilDate(),
                        m.localDates().thisMonth().toUtilDate()
                      )
                      .val();

        System.out.println(user3);
    }
}
