package fr.polyrecrute.services;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.File__;
import fr.polyrecrute.models.Offer__;
import fr.polyrecrute.repository.OfferRepository;
import fr.polyrecrute.responceType.Offer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OfferServiceImpl implements OfferService {
    private static final Logger logger = LoggerFactory.getLogger(OfferServiceImpl.class);
    private final OfferRepository offerRepository;
    private final FileService fileService;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, FileService fileService) {
        this.offerRepository = offerRepository;
        this.fileService = fileService;
    }

    @Override
    public Offer__ createOffer(Company__ company, Offer pOffer) {
        if (pOffer.getTitle().length() > 100)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title is too long");
        if (pOffer.getKeyWord().length() > 50)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Key word is too long");
        if (pOffer.getDescription().length() > 500)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Description is too long");
        logger.info("company : " + company.getIdCompany());
        logger.info("pOffer.getTitle() : " + pOffer.getTitle());
        logger.info("pOffer.getKeyWord() : " + pOffer.getKeyWord());
        logger.info("pOffer.getDescription() : " + pOffer.getDescription());
        Offer__ offer = new Offer__(company, pOffer.getTitle(), pOffer.getKeyWord(), pOffer.getDescription());
        offerRepository.save(offer);
        return offer;
    }

    @Override
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
}
