package com.mockneat.annotatations;

import com.mockneat.annotations.generator.ObjectGenerator;
import org.junit.Test;

public class AnnotationTest {

    // It shouldn't
    @Test(expected = StackOverflowError.class)
    public void test1() throws Exception {
        ObjectGenerator<Test1> ogt1 = new ObjectGenerator<>(Test1.class, "t1-gen");
        for (int i = 0; i < 5; i++) {
            System.out.println(ogt1.generate());
        }
    }
}
