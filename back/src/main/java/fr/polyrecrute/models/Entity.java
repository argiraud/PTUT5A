package fr.polyrecrute.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@javax.persistence.Entity
@Table(	name = "Entity",
		uniqueConstraints = {
			@UniqueConstraint(columnNames = "email") 
		})
public class Entity implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_entity")
	private Long idEntity;

	@NotBlank
	@Column(length = 50)
	private String name;

	@NotBlank
	@Column(length = 50)
	@Email
	private String email;

	@NotBlank
	@Column(length = 120)
	@JsonIgnore
	private String password;

	@Column(length = 500)
	private String presentation;

	@NotBlank
	private boolean enable;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "Entity_roles",
				joinColumns = @JoinColumn(name = "id_entity"),
				inverseJoinColumns = @JoinColumn(name = "id_role"))
	private Set<Role> roles = new HashSet<>();

	public Entity() {
	}

	public Entity(@NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 120) String password, String presentation, @NotBlank boolean enable, Role role) {
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
