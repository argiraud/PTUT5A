package fr.polyrecrute.services;

import fr.polyrecrute.models.ERole;
import fr.polyrecrute.models.Role__;
import fr.polyrecrute.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        super();
        this.roleRepository = roleRepository;
    }

    @Override
    public Role__ findByName(ERole role){
        Optional<Role__> currentRole = roleRepository.findByName(role);
        if (!currentRole.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");
        }
        return currentRole.get();
    }

    @Override
    public List<Role__> findAll() {
        return roleRepository.findAll();
    }
}
