package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.unit.seq.IntSeq;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateIntSeq {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        IntSeq seq = mock.intSeq();
        for(int i = 0; i < 20; i++) {
            System.out.print(seq.val() + " ");
        }

        System.out.println();

        IntSeq seq2 = mock.intSeq()
                          .start(5)
                          .increment(2)
                          .max(20)
                          .cycle(true);

        for(int i = 0; i < 50; i++) {
            System.out.print(seq2.val() + " ");
        }
    }
}
