package fr.polyrecrute.services;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.Offer__;
import fr.polyrecrute.responceType.Offer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface OfferService {
    Offer__ create(Company__ company, Offer offer);
    Offer__ findById(String idOffer);
    void storeFile(MultipartFile file, Offer offer);
    long countAll();
    long countOffersPending();
    List<Offer__> getOffers();
    List<Offer> getTransactionalObjectList(Set<Offer__> pOffers);
    List<Offer> getTransactionalObjectList(List<Offer__> pOffers);
    void delete(Offer__ offer);
    void update(Offer upOffer, Offer__ offer);
}
