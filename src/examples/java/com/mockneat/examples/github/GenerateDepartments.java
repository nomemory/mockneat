package com.mockneat.examples.github;

import com.mockneat.mock.MockNeat;

/**
 * Created by andreinicolinciobanu on 03/03/2017.
 */
public class GenerateDepartments {
    public static void main(String[] args) {
        MockNeat mock = MockNeat.threadLocal();
        String dept = mock.departments().val();
        System.out.println(dept);
    }
}
