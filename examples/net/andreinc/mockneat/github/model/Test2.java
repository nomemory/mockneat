package net.andreinc.mockneat.github.model;

/**
 * Created by andreinicolinciobanu on 07/05/17.
 */
public class Test2 {
    private int x;
    private int y;
    private Short z;
    private String w1;
    private String w2;

    @Override
    public String toString() {
        return "Test2{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w1='" + w1 + '\'' +
                ", w2='" + w2 + '\'' +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public short getZ() {
        return z;
    }

    public void setZ(short z) {
        this.z = z;
    }

    public String getW1() {
        return w1;
    }

    public void setW1(String w1) {
        this.w1 = w1;
    }

    public String getW2() {
        return w2;
    }

    public void setW2(String w2) {
        this.w2 = w2;
    }
}
