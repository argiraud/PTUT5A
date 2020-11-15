package fr.polyrecrute.services;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.Offer__;
import fr.polyrecrute.responceType.Offer;
import org.springframework.web.multipart.MultipartFile;

public interface OfferService {
    Offer__ createOffer(Company__ company, Offer offer);
    Offer__ findById(String idOffer);
    void storeFile(MultipartFile file, Offer offer);
}
