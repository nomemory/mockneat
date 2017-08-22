package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitInt;

import java.util.LinkedList;
import java.util.List;

import static net.andreinc.mockneat.types.enums.CreditCardType.AMERICAN_EXPRESS;
import static net.andreinc.mockneat.types.enums.CreditCardType.MASTERCARD;
import static net.andreinc.mockneat.types.enums.IPv4Type.CLASS_A;
import static net.andreinc.mockneat.types.enums.IPv4Type.CLASS_C;
import static net.andreinc.mockneat.types.enums.StringFormatType.LOWER_CASE;
import static net.andreinc.mockneat.types.enums.StringFormatType.UPPER_CASE;

/**
 * Created by andreinicolinciobanu on 17/06/17.
 */
public class ExampleNew {
    public static void main(String[] args) {
        MockNeat m = MockNeat.threadLocal();

        MockUnitInt num = m.probabilites(Integer.class)
                           .add(0.3, m.ints().range(0, 10))
                           .add(0.7, m.ints().range(10, 20))
                           .mapToInt(Integer::intValue);

        List<String> strings = m.fmt("#{first} #{last} #{num}")
                                .param("first", m.names().first().format(LOWER_CASE))
                                .param("last", m.names().last().format(UPPER_CASE))
                                .param("num", num)
                                .list(1000)
                                .val();

        System.out.println(strings);

        MockNeat mock = MockNeat.threadLocal();

        // Generating an arbitrary integer in the range [200, 100) and then divide it by 5
        Integer int1 = mock.ints()
                            .range(100, 200)
                            .map(i -> i / 5)
                            .val();

        System.out.println(int1);

        // Generating an AMEX or a MasterCard credit card number
        String amex = mock.creditCards().types(AMERICAN_EXPRESS, MASTERCARD).val();

        // Generate an IPV4 address of Class A or CLASS C
        String ipv4ClassA = mock.ipv4s()
                                .types(CLASS_A, CLASS_C)
                                .val();

        System.out.println(ipv4ClassA);

        // Generate a random name with a 50% chance of a having a midle name
        String fullName = mock.names().full(50.0).val();



        List<List<List<Integer>>> lists = mock.ints()
                                                .range(0, 10)
                                                .list(10).list(LinkedList.class, 10)
                                                .list(100)
                                                .val();

    }
}
