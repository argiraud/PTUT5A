package fr.polyrecrute.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import fr.polyrecrute.responceType.Entity;

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

    @OneToMany(targetEntity= Offer__.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY	)
    private Set<Offer__> offers = new HashSet<>();

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

    public Entity getTransactionalObject() {
        return new Entity(entity.getIdEntity(), entity.getName());
    }
}
