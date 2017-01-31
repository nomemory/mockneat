package com.mockneat.annotations.strategies;

import com.mockneat.annotations.strategies.constants.ConstStrategy;
import com.mockneat.annotations.strategies.random.*;

/**
 * Created by andreinicolinciobanu on 03/11/2016.
 */
public enum Strategies {

    CONST_STRATEGY (new ConstStrategy()),

    RAND_BOOLEAN_STRATEGY(new RandBooleanStrategy()),
    RAND_CREDIT_CARD_STRATEGY(new RandCreditCardStrategy()),
    RAND_CVV_STRATEGY(new RandCreditCardStrategy()),
    RAND_DICT_STRATEGY(new RandDictStrategy()),
    RAND_EMAIL_STRATEGY(new RandEmailStrategy()),
    RAND_INTEGER_STRATEGY(new RandIntegerStrategy()),
    RAND_IPV4_ADDRESS_STRATEGY(new RandIPv4AddressStrategy()),
    RAND_MAC_ADDRESS_STRATEGY(new RandMacAddressStrategy()),
    RAND_PASSWORD_STRATEGY(new RandPasswordStrategy()),
    RAND_USERNAME_STRATEGY(new RandUserNameStrategy()),
    RAND_UUID_STRATEGY(new RandUUIDStrategy());

    private Strategy strategy;

    Strategies(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy get() {
        return strategy;
    }
}
