package fr.polyrecrute.services;

import fr.polyrecrute.models.*;
import fr.polyrecrute.repository.CompanyRepository;
import fr.polyrecrute.responceType.CompanySignup;
import fr.polyrecrute.responceType.Company;
import fr.polyrecrute.responceType.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final RoleService roleService;
    private final OfferService offerService;
    private final EntityService entityService;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, RoleService roleService, OfferService offerService, EntityService entityService) {
        this.companyRepository = companyRepository;
        this.roleService = roleService;
        this.offerService = offerService;
        this.entityService = entityService;
    }

    @Override
    public void registerEntity(CompanySignup companySignup) {
        Company__ companyCreated = new Company__();

        Entity__ entityCreated = entityService.registerEntity(companySignup, companyCreated);
        entityCreated.addRole(roleService.findByName(ERole.COMPANY));
        companyCreated.setEntity(entityCreated);

        companyRepository.save(companyCreated);
    }

    @Override
    public Company__ findCompanyById(String idCompany) {
        return companyRepository.findByIdCompany(idCompany)
                .orElseThrow(() -> {throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Company not found");});
    }

    @Override
    public List<Company__> findAll() {
        return companyRepository.findAllByIdCompanyNotNull();
    }

    @Override
    public long countAll() {
        return companyRepository.count();
    }

    @Override
    public List<Company> getTransactionalObjectList(List<Company__> pCompanies) {
        List<Company> companies = new ArrayList<>();
        for (Company__ company : pCompanies){
            companies.add(company.getTransactionalObject());
        }
        return companies;
    }


    @Override
    @Transactional
    public void deleteOffer(Company__ company, String offerId) {
        Offer__ offer = offerService.findById(offerId);
        company.deleteOffer(offer);
        companyRepository.save(company);
        offerService.delete(offer);
    }

    @Override
    @Transactional
    public Offer__ createOffer(Company__ company, Offer pOffer) {
        Offer__ offer = offerService.create(company, pOffer);
        company.addOffer(offer);
        companyRepository.save(company);
        return offer;
    }

    @Override
    public void deleteWantedUser(Company__ company, User__ user) {
        company.deleteWantedUser(user);
        companyRepository.save(company);
    }

    @Override
    public void addWantedUser(Company__ company, User__ user) {
        company.addWantedUser(user);
        companyRepository.save(company);
    }

    @Override
    public List<Company__> FindCompanyWhoWantedUser(User__ user) {
        return companyRepository.findAllByWantedUserContains(user);
    }
}
