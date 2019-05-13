package jm.student.restTemplateDataAccess;

import jm.student.models.Role;

import java.util.List;

public interface RoleRestTemplateDataAccess {
    Role getRoleById(Long id);

    List<Role> getAllRoles();
}
