package jm.student.dao.impl;

import jm.student.dao.RoleDao;
import jm.student.models.Role;
import jm.student.secutiry.auth.HttpRequestFactoryClient;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {


    public RestTemplate restTemplate;

    private HttpRequestFactoryClient requestFactoryClient = new HttpRequestFactoryClient();
    @Autowired
    public RoleDaoImpl() {
        this.restTemplate = new RestTemplate(requestFactoryClient.getClientHttpRequestFactory());
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

