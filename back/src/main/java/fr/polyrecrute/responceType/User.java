package fr.polyrecrute.responceType;

import fr.polyrecrute.models.Role__;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User {

    private Long id;
    private String name;
    private String email;
    private String presentation;
    private String firstName;

    public User(Long id, String name, String email, String presentation, String firstName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.presentation = presentation;
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPresentation() {
        return presentation;
    }

    public String getFirstName() {
        return firstName;
    }
}
