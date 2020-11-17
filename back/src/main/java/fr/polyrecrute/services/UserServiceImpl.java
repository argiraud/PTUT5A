package fr.polyrecrute.services;

import fr.polyrecrute.models.ERole;
import fr.polyrecrute.models.Entity__;
import fr.polyrecrute.models.User__;
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
    private final RoleService roleService;
    private final EntityService entityService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, EntityService entityService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
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

        User__ userCreated = new User__(userSignup.getFirstName(), userSignup.getEtudiantNumber(), userSignup.getBirthDate(), "OK");
        Entity__ entityCreated = entityService.registerEntity(userSignup, userCreated);
        entityCreated.addRole(roleService.findByName(ERole.USER));
        userCreated.setEntity(entityCreated);
        userRepository.save(userCreated);
    }

    @Override
    public long countAll() {
        return userRepository.count();
    }
}
