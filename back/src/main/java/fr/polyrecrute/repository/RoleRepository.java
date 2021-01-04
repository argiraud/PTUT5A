package fr.polyrecrute.repository;

import java.util.Optional;

import fr.polyrecrute.models.ERole;
import fr.polyrecrute.models.Role__;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role__, Long> {
	Optional<Role__> findByName(ERole name);
}
