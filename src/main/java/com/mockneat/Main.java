package com.mockneat;

import com.mockneat.random.Rand;

import java.util.stream.IntStream;

/**
 * Created by andreinicolinciobanu on 15/01/2017.
 */
public class Main {

    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i -> {
            System.out.println(Rand.threadLocal().iPv6s().val());
        });

        Rand r = Rand.threadLocal();
    }
}
