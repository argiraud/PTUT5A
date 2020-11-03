package fr.polyrecrute.models;


import javax.persistence.*;
@javax.persistence.Entity(name = "Company")
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long idCompany;

    @OneToOne(mappedBy = "company", fetch = FetchType.LAZY)
    private fr.polyrecrute.models.Entity entity;

    public Company() {
    }

    public Company(Entity entity) {
        this.entity = entity;
    }

    public Long getIdCompany() {
        return idCompany;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
