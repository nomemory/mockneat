package com.mockneat.types.enums;

/**
 * Created by andreinicolinciobanu on 02/02/2017.
 */
public enum MarkovChainType {

    KAFKA("resources/markov/kafka");

    private String path;

    MarkovChainType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
