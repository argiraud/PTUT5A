package fr.polyrecrute.repository;

import fr.polyrecrute.models.Company__;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company__, Long> {
}
