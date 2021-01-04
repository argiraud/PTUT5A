package fr.polyrecrute.responceType;

import fr.polyrecrute.models.File__;
import fr.polyrecrute.models.Role__;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EntityDetails {
    private Long id;
    private String name;
    private String email;
    private String presentation;
    private Set<Role__> roles;
    private Set<File> files = new HashSet<>();
    private boolean enable;
    private String firstName;
    private String etudiantNumber;
    private Date birthDate;
    private String status;

    public EntityDetails(Long id, String name, String email, String presentation, Set<Role__> roles, Set<File__> files, boolean enable, String firstName, String etudiantNumber, Date birthDate, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.presentation = presentation;
        this.roles = roles;
        for (File__ file: files) {
            this.files.add(file.getTransactionalObject());
        }
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

    public Set<File> getFiles() {
        return files;
    }
}
