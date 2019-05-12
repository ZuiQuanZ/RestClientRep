package jm.student.dao;

import jm.student.models.Role;

import java.util.List;

public interface RoleDao {
    Role getRoleById(Long id);

    List<Role> getAllRoles();
}
