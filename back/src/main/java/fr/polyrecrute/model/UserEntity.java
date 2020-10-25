package fr.polyrecrute.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "UTILISATEUR")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "LOGIN", unique=true, insertable=true, updatable=true, nullable=false)
    private String login;

    @Column(name = "USER_PASSWORD", insertable=true, updatable=true, nullable=false)
    private String password;

    @Column(name = "USER_ACTIVE", insertable=true, updatable = true, nullable=false)
    private Integer active;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<RoleEntity> roles= new HashSet<>();
}

