package net.andreinc.mockneat.github.model;

/**
 * Created by andreinicolinciobanu on 26/03/17.
 */
public class User {
    private Long id;
    private Long groupId;
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", email='" + email + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
