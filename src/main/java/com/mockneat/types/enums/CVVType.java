package com.mockneat.types.enums;

/**
 * Created by andreinicolinciobanu on 20/12/2016.
 */
public enum CVVType {
    CVV3(3), CVV4(4);

    private Integer length;

    public Integer getLength() {
        return length;
    }

    CVVType(Integer length) {
        this.length = length;
    }
}
