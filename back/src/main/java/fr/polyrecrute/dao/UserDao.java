package fr.polyrecrute.dao;

import fr.polyrecrute.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}