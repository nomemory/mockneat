package net.andreinc.mockneat;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by andreinicolinciobanu on 25/03/17.
 */
public class ComplexStructure {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        Map<String, List<Map<Set<Integer>, List<Integer>>>> result
                = m.ints()
                    // List<Integer>
                    .list(2)
                    // Map<Set<Integer>, List<Integer>>
                    .mapKeys(2, m.ints().set(3)::val)
                    // List<Map<Set<Integer>, List<Integer>>>
                    .list(LinkedList.class, 2)
                    // Map<String, List<Map<Set<Integer>, List<Integer>>>>
                    .mapKeys(4, m.strings()::val)
                    .val();
    }
}
