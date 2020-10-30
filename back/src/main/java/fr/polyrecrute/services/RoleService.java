package fr.polyrecrute.services;

import fr.polyrecrute.models.ERole;
import fr.polyrecrute.models.Role;
import java.util.List;

public interface RoleService {

    Role findByName(ERole role);
    List<Role> findAll();
}
