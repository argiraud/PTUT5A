package fr.polyrecrute.models;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_role")
	private Integer idRole;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	public Role() {

	}

	public Role(ERole name) {
		this.name = name;
	}

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}