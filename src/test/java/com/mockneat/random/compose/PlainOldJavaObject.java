package com.mockneat.random.compose;

public class PlainOldJavaObject {
    private Integer x;
    private String y;
    private Double z;

    public PlainOldJavaObject() {}

    public PlainOldJavaObject(Integer x, String y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }
}
