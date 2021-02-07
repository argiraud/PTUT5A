package fr.polyrecrute.repository;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.Offer__;
import fr.polyrecrute.models.User__;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User__, Long> {
    List<User__> findDistinctByWantedOfferIn(Set<Offer__> wantedOffer);
}
