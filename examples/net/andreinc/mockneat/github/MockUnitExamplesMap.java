package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

public class MockUnitExamplesMap {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();
        m.emails()
         .map(String::toUpperCase)
         .consume(System.out::println);
    }
}
