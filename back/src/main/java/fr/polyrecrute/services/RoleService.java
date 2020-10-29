package fr.polyrecrute.services;

import fr.polyrecrute.models.Role;
import java.util.List;

public interface RoleService {

    Role findByName(String name);
    List<Role> findAll();
}
