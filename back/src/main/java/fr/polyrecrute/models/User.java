package fr.polyrecrute.models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@javax.persistence.Entity(name = "User")
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long idUser;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private fr.polyrecrute.models.Entity entity;

    @Column(length = 50)
    private String firstName;

    @Column(length = 8)
    private String etudiantNumber;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(length = 10)
    private String status;


    public User() {
    }

    public User(String firstName, String etudiantNumber, Date birthDate, String status) {
        this.firstName = firstName;
        this.etudiantNumber = etudiantNumber;
        this.birthDate = birthDate;
        this.status = status;
    }

    public Long getIdUser() {
        return idUser;
    }

    public Entity getEntity() {
        return entity;
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

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
