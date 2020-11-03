package fr.polyrecrute.responceType;

import fr.polyrecrute.models.Role;

import java.util.Date;
import java.util.Set;

public class UserSignup extends EntitySignup {


    private String firstName;
    private String etudiantNumber;
    private Date birthDate;

    public UserSignup(String name, String email, String password, String firstName, String etudiantNumber, Date birthDate) {
        super(name, email, password);
        this.firstName = firstName;
        this.etudiantNumber = etudiantNumber;
        this.birthDate = birthDate;
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
}
