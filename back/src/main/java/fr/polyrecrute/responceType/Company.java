package fr.polyrecrute.responceType;

import fr.polyrecrute.models.User__;

import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;

public class Company {

    private long idCompany;
    private String name;
    private Set<User__> wantedUser = new HashSet<>();

    public Company(long idCompany, String name, Set wantedUser) {
        this.idCompany = idCompany;
        this.name = name;
        this.wantedUser = wantedUser;
    }

    public long getIdEntity() {
        return idCompany;
    }

    public String getName() {
        return name;
    }
}
