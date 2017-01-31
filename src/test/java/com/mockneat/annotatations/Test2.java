package com.mockneat.annotatations;

import com.mockneat.annotations.types.constants.ConstString;
import com.mockneat.annotations.types.Generator;
import com.mockneat.annotations.types.other.WithGenerator;

/**
 * Created by andreinicolinciobanu on 04/01/2017.
 */
public class Test2 {

    private Test1 t1;
    private String a1;

    @Generator("t2-gen")
    public Test2(
            @WithGenerator("t1-gen") Test1 t1,
            @ConstString("a1") String a1
    ) {
        this.t1 = t1;
        this.a1 = a1;
    }

}
