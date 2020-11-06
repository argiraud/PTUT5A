package fr.polyrecrute.services;

import fr.polyrecrute.models.Company;
import fr.polyrecrute.models.Entity;
import fr.polyrecrute.models.User;
import fr.polyrecrute.responceType.EntityDetails;
import fr.polyrecrute.responceType.EntitySignin;
import fr.polyrecrute.responceType.EntitySignup;

public interface EntityService {

    Entity findByUserId(Long id);
    Entity registerEntity(EntitySignup entitySignup, User user);
    Entity registerEntity(EntitySignup entitySignup, Company company);
    EntitySignin loginEntity(String email, String password);
    Entity findByEmail(String email);
    EntityDetails getDetails(String tokenJwt);
}