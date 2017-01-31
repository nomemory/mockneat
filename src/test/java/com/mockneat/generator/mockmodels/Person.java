package com.mockneat.generator.mockmodels;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class Person {
    private String firstName;
    private String lastName;
    private Integer age;
    private String country;
    private String description;
    private int age2;
    private Catalog catalog;

    public Person() {
    }

    public Person(String firstName, String lastName, Integer age, String country, String description, int age2) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.description = description;
        this.age2 = age2;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge2() {
        return age2;
    }

    public void setAge2(int age2) {
        this.age2 = age2;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", rCountries='" + country + '\'' +
                ", description='" + description + '\'' +
                ", age2=" + age2 +
                ", catalog=" + catalog +
                '}';
    }
}
