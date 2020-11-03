package fr.polyrecrute.services;

import fr.polyrecrute.models.Company;
import fr.polyrecrute.models.Entity;
import fr.polyrecrute.repository.CompanyRepository;
import fr.polyrecrute.responceType.CompanySignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final EntityService entityService;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, EntityService entityService) {
        this.companyRepository = companyRepository;
        this.entityService = entityService;
    }

    @Override
    public void registerEntity(CompanySignup companySignup) {
        Company companyCreated = new Company();

        Entity entityCreated = entityService.registerEntity(companySignup, companyCreated);

        companyCreated.setEntity(entityCreated);

        companyRepository.save(companyCreated);
    }
}
