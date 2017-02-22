package com.mockneat.mock.unit.objects;

/**
 * Created by andreinicolinciobanu on 22/02/2017.
 */
public class MockConstValue implements MockValue {
    public Object value;

    protected MockConstValue(Object value) {
        this.value = value;
    }

    public static MockConstValue Const(Object value) {
        return new MockConstValue(value);
    }

    @Override
    public Object get() {
        return value;
    }
}
