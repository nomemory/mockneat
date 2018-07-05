package net.andreinc.mockneat.unit.objects.model;

import java.io.Serializable;

public class SerialPojo implements Serializable {

    private static final long serialVersionUID = 42L;

    private String name;

    public SerialPojo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
