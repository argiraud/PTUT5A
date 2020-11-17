package fr.polyrecrute.services;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.Offer__;
import fr.polyrecrute.responceType.Company;
import fr.polyrecrute.responceType.CompanySignup;

import java.util.List;

public interface CompanyService {
    void registerEntity(CompanySignup companySignup);
    Company__ findCompanyById(String idCompany);
    List<Company__> findAll();
    long countAll();
    List<Company> getTransactionalObjectList(List<Company__> companies);
    void addOffer(Company__ company, Offer__ offer);
}
