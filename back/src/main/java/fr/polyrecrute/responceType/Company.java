package fr.polyrecrute.responceType;

import javax.persistence.Column;

public class Company {

    private String idCompany;
    private String name;

    public Company(String idCompany, String name) {
        this.idCompany = idCompany;
        this.name = name;
    }

    public String getIdEntity() {
        return idCompany;
    }

    public String getName() {
        return name;
    }
}
