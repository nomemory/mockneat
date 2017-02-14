package com.mockneat;

/**
 * Created by andreinicolinciobanu on 10/02/2017.
 */
public class Elev {
    private String name;
    private String email;
    private Integer varsta;
    private String creditCard;

    public Elev() {}

    public Elev(String name, String email, Integer varsta, String creditCard) {
        this.name = name;
        this.email = email;
        this.varsta = varsta;
        this.creditCard = creditCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getVarsta() {
        return varsta;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "Elev{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", varsta=" + varsta +
                ", creditCard='" + creditCard + '\'' +
                '}';
    }
}
