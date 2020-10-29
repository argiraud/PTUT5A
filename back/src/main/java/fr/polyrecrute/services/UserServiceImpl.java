package fr.polyrecrute.services;

import fr.polyrecrute.models.ERole;
import fr.polyrecrute.models.Role;
import fr.polyrecrute.models.User;
import fr.polyrecrute.repository.RoleRepository;
import fr.polyrecrute.repository.UserRepository;
import fr.polyrecrute.responceType.UserSignin;
import fr.polyrecrute.responceType.UserSignup;
import fr.polyrecrute.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public User findByUsername(String username) {
        Optional<User> currentRole = userRepository.findByUsername(username);
        if (!currentRole.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Username already registered");
        }
        return currentRole.get();
    }

    @Override
    public User findByUserId(Long id) {
        Optional<User> registedUser = userRepository.findById(id);
        return registedUser.orElse(null);
    }

    @Override
    public void registerUser(UserSignup userSignup) {
        if (userRepository.existsByUsername(userSignup.getUsername())) {
            ;
        }

        loadUserByUsername(userSignup.getUsername());

        if (userRepository.existsByEmail(userSignup.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Email already exists");
        }

        Role userRole = roleRepository.findByName(ERole.USER)
                .orElseThrow(() -> {throw new ResponseStatusException(
                                            HttpStatus.NOT_FOUND, "Role not found");});

        User userCreated = new User(userSignup.getUsername(),
                userSignup.getEmail(),
                encoder.encode(userSignup.getPassword()),
                userRole,true);

        userRepository.save(userCreated);
    }

    @Override
    public UserSignin loginUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User user = (User) authentication.getPrincipal();
        return new UserSignin(user.getId(),user.getUsername(),user.getEmail(),user.isEnabled(), jwt);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> {throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "Username already exists");});
    }
}
