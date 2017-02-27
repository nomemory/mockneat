package com.mockneat.mock.unit.objects;

/**
 * Created by andreinicolinciobanu on 22/02/2017.
 */
public class MockConstValue implements MockValue {

    public Object value;

    private boolean forced = false;

    protected MockConstValue(Object value) {
        this.value = value;
    }

    protected MockConstValue(Object value, boolean forced) {
        this(value);
        this.forced = forced;
    }

    public static MockConstValue val(Object value) {
        return new MockConstValue(value);
    }

    public static MockConstValue val(Object value, boolean forced) { return new MockConstValue(value, forced); }

    @Override
    public Object get() {
        return value;
    }


    @Override
    public Boolean isForced() {
        return forced;
    }
}
