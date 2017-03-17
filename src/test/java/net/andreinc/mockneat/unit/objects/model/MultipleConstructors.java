package net.andreinc.mockneat.unit.objects.model;

/**
 * Created by andreinicolinciobanu on 08/03/2017.
 */
public class MultipleConstructors {
    private String x;
    private String y;
    private String z;
    private String a;

    public MultipleConstructors(String x, String y, String z, String a) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.a = a;
    }

    public MultipleConstructors(String x, String y, String z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MultipleConstructors(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public MultipleConstructors(String x) {
        this.x = x;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
