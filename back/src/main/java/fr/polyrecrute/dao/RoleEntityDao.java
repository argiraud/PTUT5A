package fr.polyrecrute.dao;

import fr.polyrecrute.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleEntityDao extends JpaRepository<RoleEntity, Long> {

}
