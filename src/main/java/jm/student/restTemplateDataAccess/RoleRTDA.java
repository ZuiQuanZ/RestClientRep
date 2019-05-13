package jm.student.restTemplateDataAccess;

import jm.student.models.Role;

import java.util.List;

public interface RoleRTDA {
    Role getRoleById(Long id);

    List<Role> getAllRoles();
}
