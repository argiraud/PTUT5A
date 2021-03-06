package fr.polyrecrute.services;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.File__;
import fr.polyrecrute.models.Offer__;
import fr.polyrecrute.repository.OfferRepository;
import fr.polyrecrute.responceType.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final FileService fileService;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, FileService fileService) {
        this.offerRepository = offerRepository;
        this.fileService = fileService;
    }

    @Override
    public Offer__ create(Company__ company, Offer pOffer) {
        if (pOffer.getTitle().length() > 100)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title is too long");
        if (pOffer.getKeyWord().length() > 50)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Key word is too long");
        if (pOffer.getDescription().length() > 500)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Description is too long");
        Offer__ offer = new Offer__(company, pOffer.getTitle(), pOffer.getKeyWord(), pOffer.getDescription(), "available");
        offerRepository.save(offer);;
        return offer;
    }

    @Override
    @Transactional
    public Offer__ findById(String idOffer) {
        return offerRepository.findByIdOffer(idOffer)
                .orElseThrow(() -> {throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "idOffer incorrect");});
    }

    @Override
    public void storeFile(MultipartFile pFile, Offer pOffer) {
        File__ file = fileService.storeFile(pFile, null);
        Offer__ offer = findById(pOffer.getIdOffer());
        offer.addFile(file);
        offerRepository.save(offer);
    }

    @Override
    public long countAll() {
        return offerRepository.count();
    }

    @Override
    public long countAllByState(String state) {
        return offerRepository.countAllByStateEquals(state);
    }

    @Override
    public long countOffersPending() {
        return offerRepository.countAllByValidateIsFalse();
    }

    @Override
    @Transactional
    public List<Offer__> getOffers() {
        return offerRepository.findAllByIdOfferNotNull();
    }

    @Transactional
    public List<Offer> getTransactionalObjectList(Set<Offer__> pOffers){
        List<Offer> offers = new ArrayList<>();
        for (Offer__ offer : pOffers){
            offers.add(offer.getTransactionalObject());
        }
        return offers;
    }

    @Transactional
    public List<Offer> getTransactionalObjectList(List<Offer__> pOffers){
        List<Offer> offers = new ArrayList<>();
        for (Offer__ offer : pOffers){
            offers.add(offer.getTransactionalObject());
        }
        return offers;
    }

    @Override
    public void delete(Offer__ offer) {
        offerRepository.delete(offer);
    }

    @Override
    public void update(Offer upOffer, Offer__ offer) {
        offer.setDescription(upOffer.getDescription());
        offer.setTitle(upOffer.getTitle());
        offer.setKeyWord(upOffer.getKeyWord());
        offer.setState(upOffer.getState());
        offerRepository.save(offer);
    }
}
