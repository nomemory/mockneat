package net.andreinc.mockneat.unit.objects.model;

import java.util.Objects;

public class SimpleBean {
    private String s;

    public SimpleBean() {}

    public SimpleBean(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleBean that = (SimpleBean) o;
        return Objects.equals(s, that.s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s);
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "s='" + s + '\'' +
                '}';
    }
}
