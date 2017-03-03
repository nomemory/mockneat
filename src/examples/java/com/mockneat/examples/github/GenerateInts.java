package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateInts {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        Integer i1 = mock.ints().val();
        System.out.println(i1);

        Integer bounded = mock.ints().bound(10).val();
        System.out.println(bounded);

        Integer ranged = mock.ints().range(10, 20).val();
        System.out.println(ranged);
    }
}
