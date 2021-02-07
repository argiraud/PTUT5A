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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntityServiceImpl implements EntityService {

    private final AuthenticationManager authenticationManager;
    private final EntityRepository entityRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final FileService fileService;
    private final RoleService roleService;

    @Autowired
    public EntityServiceImpl(AuthenticationManager authenticationManager, EntityRepository entityRepository, PasswordEncoder encoder, JwtUtils jwtUtils, FileService fileService, RoleService roleService) {
        this.authenticationManager = authenticationManager;
        this.entityRepository = entityRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.fileService = fileService;
        this.roleService = roleService;
    }

    @Override
    public Entity__ findByUserId(Long id) {
        return entityRepository.findById(id)
                .orElseThrow(() -> {throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found");});
    }


    @Override
    public Entity__ registerEntity(EntitySignup entitySignup, User__ user) {
        Entity__ entityCreated = register(entitySignup);
        entityCreated.setUser(user);
        entityRepository.save(entityCreated);
        return  entityCreated;
    }

    @Override
    public Entity__ registerEntity(EntitySignup entitySignup, Company__ company) {
        Entity__ entityCreated = register(entitySignup);
        entityCreated.setCompany(company);
        entityRepository.save(entityCreated);
        return  entityCreated;
    }

    private Entity__ register(EntitySignup entitySignup) {
        if (entityRepository.existsByEmail(entitySignup.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        if (entitySignup.getEmail().length() > 50)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is too long");
        if (entitySignup.getName().length() > 50)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is too long");

        Entity__ entityCreated = new Entity__(entitySignup.getName(),
                entitySignup.getEmail(),
                encoder.encode(entitySignup.getPassword()),
                "", true);

        return entityCreated;
    }

    @Override
    public EntitySignin loginEntity(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        Entity__ entity = (Entity__) authentication.getPrincipal();
        return new EntitySignin(entity.getIdEntity(), entity.getUsername(), entity.getEmail(), entity.getPresentation(), entity.getRoles(), entity.isEnabled(), jwt);
    }

    @Override
    public Entity__ findByEmail(String email) {
        return entityRepository.findByEmail(email)
                .orElseThrow(() -> {throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Email not found");});
    }

    @Override
    @Transactional
    public EntityDetails getDetails(Entity__ entity) {
        EntityDetails entityDetails = null;
        if (entity.getCompany() != null){
            entityDetails = new EntityDetails(entity.getIdEntity(),entity.getName(),entity.getEmail(),
                    entity.getPresentation(),entity.getRoles(),entity.getFiles(),entity.isEnabled(),
                    "","",null,"");
        }
        else{
            entityDetails = new EntityDetails(entity.getIdEntity(),entity.getName(),entity.getEmail(),
                    entity.getPresentation(),entity.getRoles(),entity.getFiles(), entity.isEnabled(),
                    entity.getUser().getFirstName(),entity.getUser().getEtudiantNumber(),
                    entity.getUser().getBirthDate(),entity.getUser().getStatus());
        }
        return entityDetails;
    }

    @Override
    public Entity__ getEntityFromToken(String tokenJwt){
        if (!StringUtils.hasText(tokenJwt) || !tokenJwt.startsWith("Bearer")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error token");
        }
        tokenJwt =  tokenJwt.substring(7);
        return findByEmail(jwtUtils.getEmailFromJwtToken(tokenJwt));
    }

    @Override
    public void storeFile(MultipartFile pFile, Entity__ entity) {
        File__ file = fileService.storeFile(pFile, entity);
        entity.addFile(file);
        entityRepository.save(entity);
    }

    @Override
    public long countAllStudents() {
        return entityRepository.countAllByUserIsNotNullAndRolesNotContains(roleService.findByName(ERole.ADMIN));
    }

    @Override
    public List<User__> getAllStudents() {

        List<Entity__> entities = entityRepository.findAllByUserIsNotNullAndRolesNotContains(roleService.findByName(ERole.ADMIN));
        List<User__> users = new ArrayList<User__>();
        for (Entity__ entity : entities){
            users.add(entity.getUser());
        }
        return users;
    }

    @Override
    public Entity__ updateEntity(Entity__ entity, EntityDetails entityUpdate) {

        entity.setName(entityUpdate.getName());
        entity.setEmail(entityUpdate.getEmail());
        entity.setPresentation(entityUpdate.getPresentation());

        if (entity.getUser() != null){
            entity.getUser().setFirstName(entityUpdate.getFirstName());
            entity.getUser().setEtudiantNumber(entityUpdate.getEtudiantNumber());
            entity.getUser().setBirthDate(entityUpdate.getBirthDate());
            entity.getUser().setStatus(entityUpdate.getStatus());
        }

        entityRepository.save(entity);
        return entity;
    }

    @Override
    public void updatePassword(Entity__ entity, String password) {
        entity.setPassword(encoder.encode(password));
        entityRepository.save(entity);
    }

}
