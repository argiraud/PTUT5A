package fr.polyrecrute.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.*;

@javax.persistence.Entity(name = "Entity")
@Table(	name = "entity",
		uniqueConstraints = {
			@UniqueConstraint(columnNames = "email") 
		})
public class Entity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")
	private Long idEntity;

	@Column(length = 50)
	private String name;

	@Column(length = 50)
	private String email;

	@Column(length = 120)
	@JsonIgnore
	private String password;

	@Column(length = 500)
	private String presentation;

	private boolean enable;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private User user;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Company company;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "entity_role",
				joinColumns = @JoinColumn(name = "entity_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public Entity() {
	}

	public Entity(String name, String email, String password, String presentation, boolean enable, Role role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.presentation = presentation;
		this.enable = enable;
		this.roles.add(role);
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enable;
	}

	public Long getIdEntity() {
		return idEntity;
	}

	public String getEmail() {
		return email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public String getPresentation() {
		return presentation;
	}

	public String getName() {
		return name;
	}

	public void addRole(Role role) {
		roles.add(role);
	}

	public User getUser() {
		return user;
	}

	public void setIdEntity(Long idEntity) {
		this.idEntity = idEntity;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Entity entity = (Entity) o;
		return Objects.equals(idEntity, entity.idEntity);
	}


}
