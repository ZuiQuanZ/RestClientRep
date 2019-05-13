package jm.student.services.impl;

import jm.student.restTemplateDataAccess.RoleRestTemplateDataAccess;
import jm.student.models.Role;
import jm.student.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRestTemplateDataAccess roleRestTemplateDataAccess;

    @Autowired
    public RoleServiceImpl(RoleRestTemplateDataAccess roleRestTemplateDataAccess) {
        this.roleRestTemplateDataAccess = roleRestTemplateDataAccess;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRestTemplateDataAccess.getRoleById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRestTemplateDataAccess.getAllRoles();
    }
}
