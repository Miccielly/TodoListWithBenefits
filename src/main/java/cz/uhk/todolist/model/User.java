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

    private String login;
    private String password;
    private Role role;

    private List<String> projectIds;
}