package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

import java.util.Arrays;

/**
 * Created by andreinicolinciobanu on 11/05/17.
 */
public class GenerateShufflers {
    public static void main(String[] args) {

        MockNeat mockNeat = MockNeat.threadLocal();

        int[] x = { 1, 2, 3, 4, 5, 6 };
        mockNeat.shufflers()
                .arrayInt(x)
                .stream().val()
                .limit(5)
                .forEach(arr -> System.out.println(Arrays.toString(arr)));

    }
}
