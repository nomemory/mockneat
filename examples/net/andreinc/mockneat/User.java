package net.andreinc.mockneat;

import java.util.Date;

public class User {

    private String userName;
    private String firstName;
    private String lastName;
    private Date created;
    private Date modified;

    public User() {
    }

    public User(String userName, String firstName, String lastName, Date created, Date modified) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.created = created;
        this.modified = modified;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String
    toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
