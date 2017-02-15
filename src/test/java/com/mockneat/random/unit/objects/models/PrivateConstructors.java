package com.mockneat.random.unit.objects.models;

/**
 * Created by andreinicolinciobanu on 08/02/2017.
 */
public class PrivateConstructors {
    private int x;

    private PrivateConstructors(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
