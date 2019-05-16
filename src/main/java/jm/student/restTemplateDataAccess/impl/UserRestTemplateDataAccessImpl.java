package jm.student.restTemplateDataAccess.impl;

import jm.student.models.User;
import jm.student.restTemplateDataAccess.UserRestTemplateDataAccess;
import jm.student.secutiry.serverAuth.HttpRequestFactoryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserRestTemplateDataAccessImpl implements UserRestTemplateDataAccess {

    public RestTemplate restTemplate;

    public UserRestTemplateDataAccessImpl() {
        this.restTemplate = new RestTemplate(HttpRequestFactoryClient.getClientHttpRequestFactory());
    }

    @Override
    public User getUserById(Long id) {
        User user = restTemplate.getForEntity("http://localhost:8080/getUser/" + id,
                User.class).getBody();
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        ResponseEntity<User> userResponseEntity = restTemplate.postForEntity("http://localhost:8080/getUserByLogin",login,
                User.class);
        User user = userResponseEntity.getBody();
        return user;
    }

    @Override
    public void addUser(User user) {
        restTemplate.postForEntity("http://localhost:8080/addUser/",
                user, User.class);

    }

    @Override
    public void editUser(User user) {
        restTemplate.postForEntity("http://localhost:8080/editUser/",
                user, User.class).getBody();
    }

    @Override
    public void removeUser(Long id) {
        restTemplate.getForEntity("http://localhost:8080/removeUserById/" + id,
                User.class).getBody();
    }

    @Override
    public List<User> getAllUsers() {
        User[] userListFromServer = restTemplate.getForEntity("http://localhost:8080/getUserList",
                User[].class).getBody();
        List<User> allUsers = new ArrayList<>();
        for (User user : userListFromServer
        ) {
            allUsers.add(user);
        }
        return allUsers;
    }
}
