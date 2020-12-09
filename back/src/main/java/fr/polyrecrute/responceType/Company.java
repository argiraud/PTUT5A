package fr.polyrecrute.responceType;

import javax.persistence.Column;

public class Company {

    private long idCompany;
    private String name;

    public Company(long idCompany, String name) {
        this.idCompany = idCompany;
        this.name = name;
    }

    public long getIdEntity() {
        return idCompany;
    }

    public String getName() {
        return name;
    }
}
