package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;
import com.mockneat.mock.unit.seq.LongSeq;

/**
 * Created by andreinicolinciobanu on 04/03/2017.
 */
public class GenerateLongSeq {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        LongSeq seq = mock.longSeq();
        for(int i = 0; i < 20; i++) {
            System.out.print(seq.val() + " ");
        }

        System.out.println();

        LongSeq seq2 = mock.longSeq()
                           .start(5)
                            .increment(2)
                            .max(20)
                            .cycle(true);

        for(int i = 0; i < 50; i++) {
            System.out.print(seq2.val() + " ");
        }
    }
}
