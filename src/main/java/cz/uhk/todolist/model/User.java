package cz.uhk.todolist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Users")
public class User {
    enum Role {Admin, User}

    @Id
    private String id;


    private String username;    //TODO udělat username unikátní
    private String password;
    private Role role;

    private List<String> projectIds;

    public User(String username, String password, Role role, List<String> projectIds) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.projectIds = projectIds;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<String> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<String> projectIds) {
        this.projectIds = projectIds;
    }
}