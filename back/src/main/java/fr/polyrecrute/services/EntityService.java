package fr.polyrecrute.services;

import fr.polyrecrute.models.*;
import fr.polyrecrute.responceType.EntityDetails;
import fr.polyrecrute.responceType.EntitySignin;
import fr.polyrecrute.responceType.EntitySignup;
import fr.polyrecrute.responceType.Question;
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
    Entity__ updateEntity(Entity__ entity, EntityDetails entityUpdate);
    void updatePassword(Entity__ entity, String password);
    void deleteFile(Entity__ entity, File__ file);
    void addQuestion(Entity__ entity, Question question);
}