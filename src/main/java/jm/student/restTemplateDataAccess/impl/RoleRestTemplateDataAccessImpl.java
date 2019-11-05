package jm.student.restTemplateDataAccess.impl;

import jm.student.models.Role;
import jm.student.restTemplateDataAccess.RoleRestTemplateDataAccess;
import jm.student.secutiry.serverAuth.HttpRequestFactoryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleRestTemplateDataAccessImpl implements RoleRestTemplateDataAccess {

    public RestTemplate restTemplate;

    public RoleRestTemplateDataAccessImpl() {
        this.restTemplate = new RestTemplate(HttpRequestFactoryClient.getClientHttpRequestFactory());
    }

    @Override
    public Role getRoleById(Long id) {
        return restTemplate.getForEntity("http://localhost:8080/getRole/" + id,
                Role.class).getBody();
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roleListFromServer = restTemplate.getForEntity("http://localhost:8080/getRoleList",
                List.class).getBody();
        return roleListFromServer;
    }

}

