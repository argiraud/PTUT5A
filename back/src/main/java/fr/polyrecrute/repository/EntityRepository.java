package fr.polyrecrute.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import fr.polyrecrute.models.Role__;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.polyrecrute.models.Entity__;

@Repository
public interface EntityRepository extends JpaRepository<Entity__, Long> {
	Optional<Entity__> findByEmail(String email);
	Optional<Entity__> findById(Long id);
	Boolean existsByEmail(String email);
	long countAllByUserIsNotNullAndRolesNotContains(Role__ role);
	List<Entity__> findAllByUserIsNotNullAndRolesNotContains(Role__ role);
}
