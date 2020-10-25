package fr.polyrecrute.dao;

import fr.polyrecrute.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityDao extends JpaRepository<UserEntity, Long> {
}
