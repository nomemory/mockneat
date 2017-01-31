package com.mockneat.utils;

import com.mockneat.types.CallBack;

/**
 * Created by andreinicolinciobanu on 06/01/2017.
 */
public class FunctUtils {

    public static final void cycle(int cycles, CallBack callBack) {
        for (int i = 0; i < cycles; i++) {
            callBack.call();
        }
    }
}
