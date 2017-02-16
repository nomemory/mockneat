package com.mockneat.random.utils;

public class LuhnUtils {
    /**
     * Tests if a certain String is a valid Luhn number
     * @param cc
     * @return
     */
    public static boolean luhnCheck(String cc) {
        int result = 0;
        boolean flag = false;
        int digit = 0;
        int i = cc.length();

        while(i-->0) {
            digit = Character.getNumericValue(cc.charAt(i));
            if (flag) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }
            result += digit;
            flag = !flag;
        }

        return (result % 10 == 0);
    }
}
