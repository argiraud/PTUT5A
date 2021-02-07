package fr.polyrecrute.repository;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.Offer__;
import fr.polyrecrute.models.User__;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CompanyRepository extends JpaRepository<Company__, Long> {
    Optional<Company__> findByIdCompany(String idCompany);
    List<Company__> findAllByIdCompanyNotNull();
    List<Company__> findAllByWantedUserContains(User__ wantedUser);
    long count();
}
