package fr.polyrecrute.services;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.Entity__;
import fr.polyrecrute.models.Offer__;
import fr.polyrecrute.models.User__;
import fr.polyrecrute.responceType.Company;
import fr.polyrecrute.responceType.CompanySignup;
import fr.polyrecrute.responceType.Offer;

import java.util.List;

public interface CompanyService {
    void registerEntity(CompanySignup companySignup);
    Company__ findCompanyById(String idCompany);
    List<Company__> findAll();
    long countAll();
    List<Company> getTransactionalObjectList(List<Company__> companies);
    void deleteOffer(Company__ company, String offerId);
    Offer__ createOffer(Company__ company, Offer offer);
    void deleteWantedUser(Company__ company, User__ user);
    void addWantedUser(Company__ company, User__ user);
    List<Company__> FindCompanyWhoWantedUser(User__ user);
}
