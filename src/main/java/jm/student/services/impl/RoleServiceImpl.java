package jm.student.services.impl;

import jm.student.restTemplateDataAccess.RoleRTDA;
import jm.student.models.Role;
import jm.student.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRTDA roleRTDA;

    @Autowired
    public RoleServiceImpl(RoleRTDA roleRTDA) {
        this.roleRTDA = roleRTDA;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRTDA.getRoleById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRTDA.getAllRoles();
    }
}
