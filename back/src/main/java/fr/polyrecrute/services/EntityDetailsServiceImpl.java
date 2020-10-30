package fr.polyrecrute.services;

import fr.polyrecrute.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EntityDetailsServiceImpl implements UserDetailsService {

    private final EntityRepository entityRepository;

    @Autowired
    public EntityDetailsServiceImpl(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return entityRepository.findByEmail(email)
                .orElseThrow(() -> {throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "Email or password incorrect");});
    }

}
