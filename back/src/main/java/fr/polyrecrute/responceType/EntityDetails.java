package fr.polyrecrute.responceType;

import fr.polyrecrute.models.Role__;

import java.util.Date;
import java.util.Set;

public class EntityDetails {
    private Long id;
    private String name;
    private String email;
    private String presentation;
    private Set<Role__> roles;
    private boolean enable;
    private String firstName;
    private String etudiantNumber;
    private Date birthDate;
    private String status;

    public EntityDetails(Long id, String name, String email, String presentation, Set<Role__> roles, boolean enable, String firstName, String etudiantNumber, Date birthDate, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.presentation = presentation;
        this.roles = roles;
        this.enable = enable;
        this.firstName = firstName;
        this.etudiantNumber = etudiantNumber;
        this.birthDate = birthDate;
        this.status = status;
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

    public Set<Role__> getRoles() {
        return roles;
    }

    public boolean isEnable() {
        return enable;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEtudiantNumber() {
        return etudiantNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getStatus() {
        return status;
    }
}
