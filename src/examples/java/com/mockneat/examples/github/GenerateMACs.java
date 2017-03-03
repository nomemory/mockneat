package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;

import static com.mockneat.types.enums.MACAddressFormatType.DOT_EVERY_4_DIGITS;

/**
 * Created by andreinicolinciobanu on 04/03/2017.
 */
public class GenerateMACs {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        String mac = mock.macs().type(DOT_EVERY_4_DIGITS).val();
        System.out.println(mac);
    }
}
