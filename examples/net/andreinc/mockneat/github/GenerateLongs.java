package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateLongs {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        Long i1 = mock.longs().val();
        System.out.println(i1);

        Long bounded = mock.longs().bound(10).val();
        System.out.println(bounded);

        Long ranged = mock.longs().range(10, 20).val();
        System.out.println(ranged);
    }
}
