package com.mockneat.mock.interfaces.models;

/**
 * Created by andreinicolinciobanu on 07/03/2017.
 */
public class ClassicPojo {

    public String name;
    public Integer age;

    public ClassicPojo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
