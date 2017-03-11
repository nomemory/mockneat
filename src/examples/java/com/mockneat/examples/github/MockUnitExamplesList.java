package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;

import java.util.List;

/**
 * Created by andreinicolinciobanu on 11/03/2017.
 */
public class MockUnitExamplesList {
    public static void main(String[] args) {
        MockNeat m = MockNeat.old();

        List<String> countries = m.countries().names().list(100).val();
        System.out.println(countries);
    }
}
