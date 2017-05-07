package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateFiles {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        String line = mock.files().from("/Users/andreinicolinciobanu/Desktop/test.txt").val();

        System.out.println(line);
    }
}
