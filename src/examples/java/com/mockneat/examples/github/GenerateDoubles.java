package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateDoubles {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();
        Double val = mock.doubles().val();

        Double bound = 10.0;
        Double boundedVal = mock.doubles().bound(bound).val();

        Double valInRange = mock.doubles().range(100.0, 200.0).val();


        System.out.println(val);
        System.out.println(boundedVal);
        System.out.println(valInRange);
    }
}
