package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

/**
 * Created by andreinicolinciobanu on 09/03/18.
 */
public class GenerateMatrix {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        // Generating a matrix of random integers with 5 rows and 5 columns
        int[][] a = m.ints()
                     .arrayPrimitive(5)
                     .array(int[].class, 10)
                     .val();

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
}
