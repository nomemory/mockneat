package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.interfaces.MockUnitString;

import java.util.List;

/**
 * Created by andreinicolinciobanu on 11/03/2017.
 */
public class MockUnitExamplesMapToString {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        MockUnitString mus = m.ints().mapToString();
        String integer = mus.val();

        System.out.println(integer);

        MockUnitString oddOrEven = m.ints().mapToString(i -> i%2==0 ? "even" : "odd");
        List<String> oddOrEvenList = oddOrEven.list(10).val();

        System.out.println(oddOrEvenList);
    }
}
