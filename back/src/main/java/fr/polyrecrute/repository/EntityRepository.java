package fr.polyrecrute.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.polyrecrute.models.Entity__;

@Repository
public interface EntityRepository extends JpaRepository<Entity__, Long> {
	Optional<Entity__> findByEmail(String email);
	Boolean existsByEmail(String email);
}
