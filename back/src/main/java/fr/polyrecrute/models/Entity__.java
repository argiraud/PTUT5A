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
public class Entity__ implements UserDetails {

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

	@OneToOne(targetEntity= User__.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private User__ user;

	@OneToOne(targetEntity= Company__.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Company__ company;

	@ManyToMany(targetEntity = Role__.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(	name = "entity_role",
				joinColumns = @JoinColumn(name = "entity_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role__> roles = new HashSet<>();

	@OneToMany(targetEntity= File__.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<File__> files = new HashSet<>();

	public Entity__() {
	}

	public Entity__(String name, String email, String password, String presentation, boolean enable) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.presentation = presentation;
		this.enable = enable;
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

	public Set<Role__> getRoles() {
		return roles;
	}

	public String getPresentation() {
		return presentation;
	}

	public String getName() {
		return name;
	}

	public void addRole(Role__ role) {
		roles.add(role);
	}

	public User__ getUser() {
		return user;
	}

	public void setIdEntity(Long idEntity) {
		this.idEntity = idEntity;
	}

	public void setUser(User__ user) {
		this.user = user;
	}

	public Company__ getCompany() {
		return company;
	}

	public void setCompany(Company__ company) {
		this.company = company;
	}

	public Set<File__> getFiles() {
		return files;
	}

	public void addFile(File__ file){
		files.add(file);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public void deleteFile(File__ file){
		files.remove(file);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Entity__ entity = (Entity__) o;
		return Objects.equals(idEntity, entity.idEntity);
	}
}
