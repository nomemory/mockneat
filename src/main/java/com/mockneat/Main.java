package com.mockneat;

import com.mockneat.random.Rand;
import com.mockneat.types.enums.CreditCardType;

import java.util.Map;

/**
 * Created by andreinicolinciobanu on 15/01/2017.
 */
public class Main {

    public static void main(String[] args) {
        Rand R = new Rand();
        CreditCardType type = null;
        R.ccs().type(type).val();
    }
}
