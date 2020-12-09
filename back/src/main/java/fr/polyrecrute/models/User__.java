package fr.polyrecrute.models;

import fr.polyrecrute.responceType.Offer;
import fr.polyrecrute.responceType.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@javax.persistence.Entity(name = "User")
@Table(name = "\"user\"")
public class User__ {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "user_id")
    private String idUser;

    @OneToOne(targetEntity = Entity__.class, mappedBy = "user", fetch = FetchType.LAZY)
    private Entity__ entity;

    @Column(length = 50)
    private String firstName;

    @Column(length = 8)
    private String etudiantNumber;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(length = 10)
    private String status;


    public User__() {
    }

    public User__(String firstName, String etudiantNumber, Date birthDate, String status) {
        this.firstName = firstName;
        this.etudiantNumber = etudiantNumber;
        this.birthDate = birthDate;
        this.status = status;
    }

    public String getIdUser() {
        return idUser;
    }

    public Entity__ getEntity() {
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

    public void setEntity(Entity__ entity){
        this.entity = entity;
    }

    public User getTransactionalObject() {
        return new User(entity.getIdEntity(), entity.getName(), entity.getEmail(), entity.getPresentation(), firstName);
    }
}
