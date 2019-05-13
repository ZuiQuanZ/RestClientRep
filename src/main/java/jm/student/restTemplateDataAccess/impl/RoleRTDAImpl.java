package jm.student.restTemplateDataAccess.impl;

import jm.student.models.Role;
import jm.student.restTemplateDataAccess.RoleRTDA;
import jm.student.secutiry.serverAuth.HttpRequestFactoryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleRTDAImpl implements RoleRTDA {

    public RestTemplate restTemplate;

    public RoleRTDAImpl() {
        this.restTemplate = new RestTemplate(HttpRequestFactoryClient.getClientHttpRequestFactory());
    }

    @Override
    public Role getRoleById(Long id) {
        return restTemplate.getForEntity("http://localhost:8080/getRole/" + id,
                Role.class).getBody();
    }

    @Override
    public List<Role> getAllRoles() {
        Role[] roleListFromServer = restTemplate.getForEntity("http://localhost:8080/getRoleList",
                Role[].class).getBody();
        List<Role> allRoles = new ArrayList<>();
        for (Role role : roleListFromServer
        ) {
            allRoles.add(role);
        }
        return allRoles;
    }


}

