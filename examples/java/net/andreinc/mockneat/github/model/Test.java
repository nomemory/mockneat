package net.andreinc.mockneat.github.model;

/**
 * Created by andreinicolinciobanu on 04/03/2017.
 */
public class Test {
    private String x;
    private Integer y;
    private Boolean z;

    public Test() {}

    public Test(String x, Integer y, Boolean z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Boolean getZ() {
        return z;
    }

    public void setZ(Boolean z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Test{" +
                "x='" + x + '\'' +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
