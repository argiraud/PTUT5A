package fr.polyrecrute.repository;

import java.util.Optional;

import fr.polyrecrute.models.ERole;
import fr.polyrecrute.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
