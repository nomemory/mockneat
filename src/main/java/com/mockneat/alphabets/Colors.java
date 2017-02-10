package com.mockneat.alphabets;

/**
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

public enum Colors {

    // TODO class is not yet used
    // Maybe will never be

    ABSOLUTE_ZERO("Absolute Zero", "#0048BA", 0, 28, 73, 217),
    ACID_GREEN("Acid Green", "#B0BF1A", 69, 75, 10, 65),
    AERO("Aero", "#7CB9E8", 49, 73, 91, 206),
    AERO_BLUE("Aero Blue", "#C9FFE5", 79, 100, 90, 151),
    AFRICAN_VIOLET("African Violet", "#B284BE", 70, 52, 75, 288),
    AIR_FORCE_BLUE_RAF("Air Force Blue (RAF)", "#5D8AA8", 36, 54, 66, 204),
    AIR_FORCE_BLUE_USAF("Air Force Blue (USAF)", "#00308F", 0, 19, 56, 220);
    //TODO complete the rest

    private String name;
    private String hex;
    private int r;
    private int g;
    private int b;
    private int hue;

    Colors(String name, String hex, int r, int g, int b, int hue) {
        this.name = name;
        this.hex = hex;
        this.r = r;
        this.g = g;
        this.b = b;
        this.hue = hue;
    }

    public String getName() {
        return name;
    }

    public String getHex() {
        return hex;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public int getHue() {
        return hue;
    }
}
