package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

import java.util.Arrays;
import java.util.List;

/**
 * Created by andreinicolinciobanu on 22/05/17.
 */
public class GenerateSeq {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        List<String> list = Arrays.asList("a", "b", "c", "d");

        mock.seq(list).cycle(true).list(100).consume(System.out::println);
        // Prints ["a", "b", "c", "d", "a", "b"....]

        mock.seq(list).after("X").list(100).consume(System.out::println);
        // Prints ["a", "b", "c", "d", "X", "X"...."X"]

    }
}
