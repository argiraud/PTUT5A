package fr.polyrecrute.repository;

import fr.polyrecrute.models.Company__;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company__, Long> {
    Optional<Company__> findByIdCompany(String idCompany);
    List<Company__> findAllByIdCompanyNotNull();
    long count();
}
