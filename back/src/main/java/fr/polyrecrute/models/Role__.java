package fr.polyrecrute.models;

import javax.persistence.*;

@javax.persistence.Entity(name = "Roles")
@Table(name = "role")
public class Role__ {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long idRole;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	public Role__() {

	}

	public Role__(ERole name) {
		this.name = name;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}