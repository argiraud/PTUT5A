package fr.polyrecrute.services;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.ERole;
import fr.polyrecrute.models.Entity__;
import fr.polyrecrute.models.Offer__;
import fr.polyrecrute.repository.CompanyRepository;
import fr.polyrecrute.responceType.CompanySignup;
import fr.polyrecrute.responceType.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final RoleService roleService;
    private final EntityService entityService;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, RoleService roleService, EntityService entityService) {
        this.companyRepository = companyRepository;
        this.roleService = roleService;
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
    public void addOffer(Company__ company, Offer__ offer) {
        company.addOffer(offer);
        companyRepository.save(company);
    }
}
