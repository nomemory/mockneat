package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitInt;
import net.andreinc.mockneat.types.enums.IPv4Type;

import java.util.LinkedList;
import java.util.List;

import static net.andreinc.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;
import static net.andreinc.mockneat.types.enums.CreditCardType.MASTERCARD;

/**
 * Created by andreinicolinciobanu on 31/05/17.
 */
public class QuickExamples {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        // Generating an arbitrary integer in the range [200, 100)
        Integer int1 = mock.ints().range(100, 200).val();

        // Generating an AMEX or a MasterCard credit card number
        String amex = mock.creditCards().types(AMERICAN_EXPRESS, MASTERCARD).val();

        // Generate an IPV4 address of Class A
        String ipv4ClassA = mock.ipv4s().type(IPv4Type.CLASS_A).val();

        // Generate a random name with a 50% chance of a having a midle name
        String fullName = mock.names().full().val();


        MockUnitInt integers = mock.ints().bound(10);

        int[] arr1 = integers.arrayPrimitive(10).val();
        Integer[] arr2 = integers.array(10).val();
        List<Integer> list1 = integers.list(LinkedList.class, 10).val();
    }
}
