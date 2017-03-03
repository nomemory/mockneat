package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;

import static com.mockneat.types.enums.CVVType.CVV4;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateCVVS {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        String cvv3 = mock.cvvs().val();
        System.out.println(cvv3);

        String cvv4 = mock.cvvs().type(CVV4).val();
        System.out.println(cvv4);
    }
}
