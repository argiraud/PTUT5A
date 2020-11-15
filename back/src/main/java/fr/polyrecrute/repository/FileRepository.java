package fr.polyrecrute.repository;

import fr.polyrecrute.models.File__;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File__, Long> {
    Optional<File__> findByIdFile(String fileId);
}
