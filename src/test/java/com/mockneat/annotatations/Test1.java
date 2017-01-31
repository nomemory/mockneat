package com.mockneat.annotatations;

import com.mockneat.annotations.types.Generator;
import com.mockneat.annotations.types.constants.ConstString;
import com.mockneat.annotations.types.other.WithGenerator;

/**
 * Created by andreinicolinciobanu on 04/01/2017.
 */
public class Test1 {

    private String t1;
    private String t2;
    private Test2 test2;

    @Generator("t1-gen")
    public Test1(
            @ConstString("t1") String t1,
            @ConstString("t2") String t2,
            @WithGenerator("t2-gen") Test2 test2
    ) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public String toString() {
        return "Test1{" +
                "t1='" + t1 + '\'' +
                ", t2='" + t2 + '\'' +
                ", test2=" + test2 +
                '}';
    }
}
