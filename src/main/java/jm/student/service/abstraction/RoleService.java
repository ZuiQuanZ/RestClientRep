package jm.student.service.abstraction;

import jm.student.models.Role;

import java.util.List;

public interface RoleService {
    Role getRoleById(Long id);

    List<Role> getAllRoles();
}
