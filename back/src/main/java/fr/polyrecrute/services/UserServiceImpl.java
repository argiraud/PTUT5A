package fr.polyrecrute.services;

import fr.polyrecrute.models.Entity;
import fr.polyrecrute.models.User;
import fr.polyrecrute.repository.UserRepository;
import fr.polyrecrute.responceType.UserSignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final EntityService entityService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, EntityService entityService) {
        this.userRepository = userRepository;
        this.entityService = entityService;
    }

    @Override
    public void registerEntity(UserSignup userSignup) {
        if (userSignup.getFirstName().length() > 50)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First name is too long");
        if (userSignup.getEtudiantNumber().length() > 8)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Etudiant number name is too long");
        if (userSignup.getBirthDate().compareTo(new Date()) > 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Birth date is wrong");

        User userCreated = new User(userSignup.getFirstName(), userSignup.getEtudiantNumber(), userSignup.getBirthDate(), "OK");

        Entity entityCreated = entityService.registerEntity(userSignup, userCreated);

        userCreated.setEntity(entityCreated);
        userRepository.save(userCreated);
    }
}
