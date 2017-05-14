package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreinicolinciobanu on 11/03/2017.
 */
public class MockUnitsExample01 {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        // A mockUnit that can generate integers
        MockUnit<Integer> intMockUnit = m.ints().bound(100);
        // A mockUnit that can generate list of integers
        MockUnit<List<Integer>> listMockUnit = intMockUnit.list(100);

        // Generating an integer with the mockUnit
        int integer = intMockUnit.val();

        // Generating a list of integers
        List<Integer> list = listMockUnit.val();

        // Generating another list of integers
        List<Integer> list2 = listMockUnit.val();


        List<Integer[]> listArray = m.ints()
                                     .range(0, 100)
                                     .array(100)
                                     .list(ArrayList.class, 100)
                                     .val();

        List<String> l = m.fmt("{ username: #{user}, email: #{email} }")
         .param("user", m.users())
         .param("email", m.emails().domain("gameloft.com"))
         .list(100)
         .val();
        System.out.println(l);
    }
}
