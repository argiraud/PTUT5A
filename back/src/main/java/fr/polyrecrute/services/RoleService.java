package fr.polyrecrute.services;

import fr.polyrecrute.models.ERole;
import fr.polyrecrute.models.Role__;
import java.util.List;

public interface RoleService {

    Role__ findByName(ERole role);
    List<Role__> findAll();
}
