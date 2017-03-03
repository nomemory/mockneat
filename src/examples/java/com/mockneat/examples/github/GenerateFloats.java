package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateFloats {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        Float val = mock.floats().val();

        Float boundVal = mock.floats().bound(10f).val();

        Float rangeVal = mock.floats().range(100.0f, 200.0f).val();

        System.out.println(val);
        System.out.println(boundVal);
        System.out.println(rangeVal);
    }
}
