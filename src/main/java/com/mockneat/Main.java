package com.mockneat;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

/**
 * Created by andreinicolinciobanu on 15/01/2017.
 */
public class Main {

    public static void main(String[] args) {
//        Rand R = Rand.threadLocal();
//
//
//        List<Map<Integer, List<String>>> map =
//                R.names()
//                    .type(FIRST_NAME_FEMALE).format(LOWER_CASE)
//                    .list(10)
//                    .mapKeys(10,
//                        R.ints().range(0, 100).supplier())
//                    .list(10)
//                    .val();
//
//        System.out.println(map);
//
//        List<Elev> list = R.compose(
//                valueType(R.names()::val, String.class),
//                valueType(R.emails()::val, String.class),
//                valueType(R.ints().range(18,24)::val, Integer.class),
//                valueType(R.ccs().type(AMERICAN_EXPRESS)::val, String.class)
//        ).object(Elev.class).list(10).val();
//
//        MockGenerator<Elev> mg = MockGenerator.forClass(Elev.class);
//
//        mg.field("name", R.names()::val)
//                .field("email", R.emails()::val)
//                .field("varsta", R.ints().bound(100)::val)
//                .field("creditCard", R.ccs()::val);
//
//        SQLGenerator sql = new SQLGenerator();
//
//        System.out.println(mg.newInstance().get());
//
//        System.out.println(R.countries().names().val());
//        System.out.println(R.countries().iso2().val());
//        System.out.println(R.ipv4s().types(CLASS_A, CLASS_A_LOOPBACK).val());
//        System.out.println(R.macs().val());
//        System.out.println(R.objs().from(DayOfWeek.class).val());
//        System.out.println(R.markovs().type(KAFKA).val());
//        System.out.println(R.localDates().thisYear().val());
//        System.out.println(R.chars().digits().val());
//        System.out.println(R.passwords().type(STRONG_PASSWORD).val());

        System.out.println(RandomStringUtils.random(32, 32, 127, true, true, null, new SecureRandom()));
    }
}
