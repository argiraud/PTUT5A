package fr.polyrecrute.services;

import fr.polyrecrute.models.Entity;
import fr.polyrecrute.responceType.EntitySignin;
import fr.polyrecrute.responceType.EntitySignup;

public interface EntityService {

    Entity findByUserId(Long id);
    void registerEntity(EntitySignup entitySignup);
    EntitySignin loginEntity(String email, String password);
}