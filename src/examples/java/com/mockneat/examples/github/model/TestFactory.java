package com.mockneat.examples.github.model;

public class TestFactory {
    public static Test buildTest(String x, Integer y, Boolean z) {
        return new Test(x, y, z);
    }
}
