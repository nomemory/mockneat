package net.andreinc.mockneat;

/**
 * Created by andreinicolinciobanu on 17/03/18.
 */
public class UserExample {
    public static void main(String[] args) {

        MockNeat m = MockNeat.threadLocal();

        User user = m.filler(() -> new User())
                     .setter(User::setUserName,m.users())
                     .setter(User::setFirstName, m.names().first())
                     .setter(User::setLastName, m.names().last())
                     .setter(User::setCreated, m.localDates().thisYear().toUtilDate())
                     .setter(User::setModified, m.localDates().thisMonth().toUtilDate())
                     .val();

        System.out.println(user);


    }
}
