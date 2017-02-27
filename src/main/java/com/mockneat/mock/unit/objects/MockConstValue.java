package com.mockneat.mock.unit.objects;

/*
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
public class MockConstValue implements MockValue {

    private Object value;

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
