package fr.polyrecrute.services;

import fr.polyrecrute.models.*;
import fr.polyrecrute.repository.UserRepository;
import fr.polyrecrute.responceType.Offer;
import fr.polyrecrute.responceType.User;
import fr.polyrecrute.responceType.UserSignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.*;

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

        User__ userCreated = new User__(userSignup.getFirstName(), userSignup.getEtudiantNumber(), userSignup.getBirthDate());
        Entity__ entityCreated = entityService.registerEntity(userSignup, userCreated);
        entityCreated.addRole(roleService.findByName(ERole.USER));
        userCreated.setEntity(entityCreated);
        userRepository.save(userCreated);
    }

    @Override
    public List<User> getTransactionalObjectList(List<User__> pUsers) {
        List<User> users = new ArrayList<>();
        for (User__ user : pUsers){
            users.add(user.getTransactionalObject());
        }
        return users;
    }

    @Override
    public long countUserWithoutOffer(){
        return  userRepository.countAllByWantedOfferIsNull() - (userRepository.count() - entityService.countAllStudents());
    }

    @Override
    public void deleteWantedOffer(User__ user, Offer__ offer) {
        user.deleteWantedOffer(offer);
        userRepository.save(user);
    }

    @Override
    public void addWantedOffer(User__ user, Offer__ offer) {
        user.addWantedOffer(offer);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User__> findUserWhoWantedOffer(Company__ company) {
        return userRepository.findDistinctByWantedOfferIn(company.getOffers());
    }
}
