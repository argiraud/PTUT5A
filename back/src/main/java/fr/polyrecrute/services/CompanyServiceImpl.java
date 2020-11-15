package fr.polyrecrute.services;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.ERole;
import fr.polyrecrute.models.Entity__;
import fr.polyrecrute.repository.CompanyRepository;
import fr.polyrecrute.responceType.CompanySignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
