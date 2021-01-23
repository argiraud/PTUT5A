package fr.polyrecrute.models;


import fr.polyrecrute.responceType.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import fr.polyrecrute.responceType.Company;

@javax.persistence.Entity(name = "Company")
@Table(name = "company")
public class Company__ {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "company_id")
    private String idCompany;

    @OneToOne(targetEntity = Entity__.class, mappedBy = "company", fetch = FetchType.LAZY)
    private Entity__ entity;

    @OneToMany(targetEntity= Offer__.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Offer__> offers = new HashSet<>();

    @OneToMany(targetEntity= User__.class, fetch = FetchType.LAZY)
    private Set<User__> wantedUser = new HashSet<>();

    public Company__() {
    }

    public Company__(Entity__ entity) {
        this.entity = entity;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public Entity__ getEntity() {
        return entity;
    }

    public void setEntity(Entity__ entity) {
        this.entity = entity;
    }

    public Set<Offer__> getOffers() {
        return offers;
    }

    public Company getTransactionalObject() {
        return new Company(entity.getIdEntity(), entity.getName(), wantedUser);
    }

    public Set<User__> getWantedUser() {
        return wantedUser;
    }

    public void addOffer(Offer__ offer){
        offers.add(offer);
    }

    public void addWantedUser(User__ user){
        wantedUser.add(user);
    }

    public void deleteOffer(Offer__ offer){
        offers.remove(offer);
    }

    public void deleteWantedUser(User__ user){
        wantedUser.remove(user);
    }
}
