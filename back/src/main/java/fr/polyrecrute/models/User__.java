package fr.polyrecrute.models;

import fr.polyrecrute.responceType.Offer;
import fr.polyrecrute.responceType.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(targetEntity= Offer__.class, fetch = FetchType.LAZY)
    private Set<Offer__> wantedOffer = new HashSet<>();


    public User__() {
    }

    public User__(String firstName, String etudiantNumber, Date birthDate) {
        this.firstName = firstName;
        this.etudiantNumber = etudiantNumber;
        this.birthDate = birthDate;
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


    public void setEntity(Entity__ entity){
        this.entity = entity;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEtudiantNumber(String etudiantNumber) {
        this.etudiantNumber = etudiantNumber;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public User getTransactionalObject() {
        return new User(entity.getIdEntity(), entity.getName(), entity.getEmail(), entity.getPresentation(), firstName);
    }

    public void addWantedOffer(Offer__ offer){
        wantedOffer.add(offer);
    }

    public void deleteWantedOffer(Offer__ offer){
        wantedOffer.remove(offer);
    }

    public Set<Offer__> getWantedOffer() {
        return wantedOffer;
    }
}
