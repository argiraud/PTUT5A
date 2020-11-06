package fr.polyrecrute.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.polyrecrute.models.Entity;

@Repository
public interface EntityRepository extends JpaRepository<Entity, Long> {
	Optional<Entity> findByEmail(String email);
	Boolean existsByEmail(String email);
}
