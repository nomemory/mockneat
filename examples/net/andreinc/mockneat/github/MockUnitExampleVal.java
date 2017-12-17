package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

public class MockUnitExampleVal {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();
        String endsWithX = m.strings().val(s -> s + "X");

        System.out.println(endsWithX);
    }
}
