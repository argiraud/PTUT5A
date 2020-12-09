package fr.polyrecrute.services;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.Entity__;
import fr.polyrecrute.models.User__;
import fr.polyrecrute.responceType.EntityDetails;
import fr.polyrecrute.responceType.EntitySignin;
import fr.polyrecrute.responceType.EntitySignup;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface EntityService {

    Entity__ findByUserId(Long id);
    Entity__ registerEntity(EntitySignup entitySignup, User__ user);
    Entity__ registerEntity(EntitySignup entitySignup, Company__ company);
    EntitySignin loginEntity(String email, String password);
    Entity__ findByEmail(String email);
    EntityDetails getDetails(Entity__ entity);
    Entity__ getEntityFromToken(String tokenJwt);
    void storeFile(MultipartFile pFile, Entity__ entity);
    long countAllStudents();
    List<User__> getAllStudents();
}