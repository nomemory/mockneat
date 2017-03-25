package net.andreinc.mockneat.github;

import net.andreinc.mockneat.MockNeat;

/**
 * Created by andreinicolinciobanu on 25/03/17.
 */
public class GenerateRegex {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        String lolRegex = "LOo{3}L{10,15}!";
        String lol = mock.regex(lolRegex).val();
        System.out.println(lol);

        String numberRegex = "\\d{3,10}";
        String number = mock.regex(numberRegex).val();
        System.out.println(number);

        String codeRegex = "[A-Z]{2}-\\d{5}-[a-z]{5}";
        String code = mock.regex(codeRegex).val();
        System.out.println(code);
    }
}
