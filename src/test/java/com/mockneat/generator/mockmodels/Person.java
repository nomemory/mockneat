package com.mockneat.generator.mockmodels;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by andreinicolinciobanu on 23/01/2017.
 */
public class Person {
    private String name;
    private String email;
    private Integer age;
    private Catalog catalog;
    private List<Integer> integers;
    private Map<String, Integer> map;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public List<Integer> getIntegers() {
        return integers;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public void setIntegers(List<Integer> integers) {
        this.integers = integers;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", catalog=" + catalog +
                ", integers=" + integers +
                ", map=" + map +
                '}';
    }
}
