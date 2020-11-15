package fr.polyrecrute.responceType;

import fr.polyrecrute.models.Role__;

import java.util.Set;

public class EntitySignin {
    private Long id;
    private String name;
    private String email;
    private String presentation;
    private Set<Role__> roles;
    private boolean enable;
    private String tokenJWT;
    private String tokenType = "Bearer";

    public EntitySignin(Long id, String name, String email, String presentation, Set<Role__> roles, boolean enable, String tokenJWT) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.presentation = presentation;
        this.roles = roles;
        this.enable = enable;
        this.tokenJWT = tokenJWT;
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

    public boolean isEnable() {
        return enable;
    }

    public String getTokenJWT() {
        return tokenJWT;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Set<Role__> getRoles() {
        return roles;
    }

    public String getPresentation() {
        return presentation;
    }
}
