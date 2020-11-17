package fr.polyrecrute.repository;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.Offer__;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer__, Long> {
    Optional<Offer__> findByIdOffer(String idOffer);
    List<Offer__> findAllByIdOfferNotNull();
    List<Offer__> findAllByCompany(Company__ company);
    long count();
    long countAllByValidateIsFalse();
}
