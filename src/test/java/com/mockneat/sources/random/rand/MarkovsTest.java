package com.mockneat.sources.random.rand;


import com.mockneat.sources.random.Rand;
import com.mockneat.sources.random.utils.markov.MarkovUnit;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MarkovsTest {
    @Test
    public void test1() throws Exception {
        String textFile = "resources/markov/kafka";
        try{
            List<String> lines = Files.readAllLines(Paths.get(textFile));
            MarkovUnit mu = new MarkovUnit(lines, 2);
            int times = 1000;
            while(times-->0) {
                mu.generateText(new Rand(), 0, 100);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
