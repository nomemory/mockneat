package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateEmails {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();

        String email = mock.emails().val();

        String corpEmail = mock.emails().domain("startup.io").val();

        String domsEmail = mock.emails().domains("abc.com", "corp.org").val();

        System.out.println(email);
        System.out.println(corpEmail);
        System.out.println(domsEmail);
    }
}
