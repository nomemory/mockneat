package com.mockneat.types;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class Value {

    private Object o;

    protected Value(Object o) {
        this.o = o;
    }

    public static Value from(Object o) {
        return new Value(o);
    }

    public Object get() {
        return o;
    }

}
