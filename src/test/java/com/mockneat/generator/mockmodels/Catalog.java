package com.mockneat.generator.mockmodels;

/**
 * Created by andreinicolinciobanu on 24/01/2017.
 */
public class Catalog {
    private int x;
    private char y;
    private String z;

    public Catalog() {
    }

    public Catalog(int x, char y, String z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "x=" + x +
                ", y=" + y +
                ", z='" + z + '\'' +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public char getY() {
        return y;
    }

    public void setY(char y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }
}
