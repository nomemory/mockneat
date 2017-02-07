package com.mockneat;

import com.mockneat.random.Rand;

import java.util.Map;

/**
 * Created by andreinicolinciobanu on 15/01/2017.
 */
public class Main {

    public static void main(String[] args) {
        Rand R = new Rand();
        System.out.println(R.ints().bound(10).mapWithKeys(10,
                R.chars().letters()::stream
        ).val());

        Map<Double, Long> m = R.doubles()
                               .gaussians()
                               .mapWithValues(
                                        10,
                                        R.longs()
                                         .range(100L, 105L)::val
                               )
                               .val();
        System.out.println(m);
    }
}
