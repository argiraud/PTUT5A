package fr.polyrecrute.services;

import fr.polyrecrute.models.User;
import fr.polyrecrute.responceType.UserSignin;
import fr.polyrecrute.responceType.UserSignup;

public interface UserService {

    User findByUsername(String username);
    User findByUserId(Long id);
    void registerUser(UserSignup userSignup);
    UserSignin loginUser(String username, String password);
}