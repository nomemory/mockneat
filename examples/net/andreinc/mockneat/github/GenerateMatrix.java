package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

/**
 * Created by andreinicolinciobanu on 09/03/18.
 */
public class GenerateMatrix {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        // Generating a matrix of random integers with 5 rows and 5 columns
        Integer[][] a = m.from(new Integer[]{0, 1})
                     .array(() -> new Integer[5]) // Cols
                     .array(() -> new Integer[5][]) // Rows
                     .val();

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
}

// Possible Output:
// 1 1 1 0 1
// 1 1 0 1 0
// 1 1 1 0 0
// 0 1 1 0 1
// 0 1 1 1 1