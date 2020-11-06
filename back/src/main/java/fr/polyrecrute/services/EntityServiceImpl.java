package fr.polyrecrute.services;

import fr.polyrecrute.models.*;
import fr.polyrecrute.repository.EntityRepository;
import fr.polyrecrute.responceType.EntityDetails;
import fr.polyrecrute.responceType.EntitySignin;
import fr.polyrecrute.responceType.EntitySignup;
import fr.polyrecrute.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class EntityServiceImpl implements EntityService {

    private final AuthenticationManager authenticationManager;
    private final EntityRepository entityRepository;
    private final RoleService roleService;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public EntityServiceImpl(AuthenticationManager authenticationManager, EntityRepository entityRepository, RoleService roleService, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.entityRepository = entityRepository;
        this.roleService = roleService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Entity findByUserId(Long id) {
        Optional<Entity> registeredUser = entityRepository.findById(id);
        return registeredUser.orElse(null);
    }

    @Override
    public Entity registerEntity(EntitySignup entitySignup, User user) {
        Entity entityCreated = register(entitySignup);
        entityCreated.setUser(user);
        entityRepository.save(entityCreated);
        return  entityCreated;
    }

    @Override
    public Entity registerEntity(EntitySignup entitySignup, Company company) {
        Entity entityCreated = register(entitySignup);
        entityCreated.setCompany(company);
        entityRepository.save(entityCreated);
        return  entityCreated;
    }

    private Entity register(EntitySignup entitySignup) {
        if (entityRepository.existsByEmail(entitySignup.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Email already exists");
        }
        if (entitySignup.getEmail().length() > 50)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is too long");
        if (entitySignup.getName().length() > 50)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is too long");

        Role userRole = roleService.findByName(ERole.USER);

        Entity entityCreated = new Entity(entitySignup.getName(),
                entitySignup.getEmail(),
                encoder.encode(entitySignup.getPassword()),
                "", true, userRole);

        return entityCreated;
    }

    @Override
    public EntitySignin loginEntity(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        Entity entity = (Entity) authentication.getPrincipal();
        String type= "";
        if (entity.getCompany() != null)
            type = EType.Company.toString();
        if (entity.getUser() != null)
            type = EType.User.toString();
        return new EntitySignin(entity.getIdEntity(), entity.getUsername(), entity.getEmail(), entity.getPresentation(), entity.getRoles(), entity.isEnabled(), type, jwt);
    }

    @Override
    public Entity findByEmail(String email) {
        return entityRepository.findByEmail(email)
                .orElseThrow(() -> {throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Email not found");});
    }

    @Override
    public EntityDetails getDetails(String tokenJwt) {
        Entity entity = getEntityFromToken(tokenJwt);
        EntityDetails entityDetails = null;
        if (entity.getCompany() != null){
            entityDetails = new EntityDetails(entity.getIdEntity(),entity.getName(),entity.getEmail(),
                    entity.getPresentation(),entity.getRoles(),entity.isEnabled(), EType.Company.toString(),
                    "","",null,"");
        }
        else{
            entityDetails = new EntityDetails(entity.getIdEntity(),entity.getName(),entity.getEmail(),
                    entity.getPresentation(),entity.getRoles(),entity.isEnabled(), EType.User.toString(),
                    entity.getUser().getFirstName(),entity.getUser().getEtudiantNumber(),
                    entity.getUser().getBirthDate(),entity.getUser().getStatus());
        }
        return entityDetails;
    }

    private Entity getEntityFromToken(String tokenJwt){
        if (!StringUtils.hasText(tokenJwt) || !tokenJwt.startsWith("Bearer")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error token");
        }
        tokenJwt =  tokenJwt.substring(7);
        return findByEmail(jwtUtils.getEmailFromJwtToken(tokenJwt));
    }

}
