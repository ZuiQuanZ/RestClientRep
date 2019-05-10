package jm.student.dao.impl;

import jm.student.dao.RoleDao;
import jm.student.models.Role;
import jm.student.models.User;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import jm.student.models.Role;
import jm.student.models.User;
import jm.student.service.abstraction.RoleService;
import jm.student.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    EntityManager entityManager;

//    static final String url_role = "http://localhost:8080/getRole";
//    static final String url_user = "http://localhost:8080/getUser/1";
//    static final String url_roles = "http://localhost:8080/getRoleList";
//    static final String url_users = "http://localhost:8080/getUserList";
//
//    HttpHeaders headers = new HttpHeaders();
//
//        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("my_other_key", "my_other_value");
//
//    // HttpEntity<String>: To get result as String.
//    HttpEntity<Role[]> roleEntities = new HttpEntity<>(headers);
//    HttpEntity<Role> roleEntity = new HttpEntity<>(headers);
//    HttpEntity<User> userEntity = new HttpEntity<>(headers);
//    HttpEntity<User[]> userEntities = new HttpEntity<>(headers);
//
//    // RestTemplate
//    RestTemplate restTemplate = new RestTemplate();
//
//    // Send request with GET method, and Headers.
//    ResponseEntity<Role[]> response = restTemplate.exchange(url_roles, //
//            HttpMethod.GET, roleEntities, Role[].class);
//
//    Role[] result = response.getBody();
//    Role someRoleResult = restTemplate.exchange(url_role, //
//            HttpMethod.GET, roleEntity, Role.class).getBody();
//    User user = restTemplate.exchange(url_user, //
//            HttpMethod.GET, userEntity, User.class).getBody();
//    User[] users = restTemplate.exchange(url_users, //
//            HttpMethod.GET, userEntities, User[].class).getBody();

    private static HttpHeaders headers = new HttpHeaders();

    HttpEntity<Role[]> roleEntities = new HttpEntity<>(headers);
    HttpEntity<Role> roleEntity = new HttpEntity<>(headers);

    private RestTemplate restTemplate = new RestTemplate();

    public RoleDaoImpl() {
    }

    @Override
    public Role getRoleById(Long id) {
        return restTemplate.exchange("http://localhost:8080/getRole/" + id,
                HttpMethod.GET, roleEntity, Role.class).getBody();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void editRole(Role role) {
        entityManager.merge(role);
    }

    @Override
    public void removeRole(Long id) {
        entityManager.remove(entityManager.find((Role.class), id));
    }

    @Override
    public List<Role> getAllRoles() {
        Role[] roleListFromServer = restTemplate.exchange("http://localhost:8080/getRoleList",
                HttpMethod.GET, roleEntities, Role[].class).getBody();
        List<Role> allRoles = new ArrayList<>();
        for (Role role : roleListFromServer
        ) {
            allRoles.add(role);
        }
        return allRoles;
    }


}

