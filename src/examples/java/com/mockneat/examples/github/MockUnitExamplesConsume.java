package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;

/**
 * Created by andreinicolinciobanu on 11/03/2017.
 */
public class MockUnitExamplesConsume {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        m.urls().list(100).consume(System.out::println);
    }
}
