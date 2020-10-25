package fr.polyrecrute.dao;

import fr.polyrecrute.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoleDao extends JpaRepository<Role, Long>{
}