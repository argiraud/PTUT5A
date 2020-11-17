package fr.polyrecrute.repository;

import fr.polyrecrute.models.User__;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User__, Long> {
    long count();
}
