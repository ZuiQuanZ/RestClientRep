package jm.student.dao.impl;

import jm.student.dao.UserDao;
import jm.student.models.User;
import jm.student.secutiry.auth.HttpRequestFactoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    public RestTemplate restTemplate;

    private HttpRequestFactoryClient requestFactoryClient = new HttpRequestFactoryClient();

    @Autowired
    public UserDaoImpl() {
        this.restTemplate = new RestTemplate(requestFactoryClient.getClientHttpRequestFactory());
    }

    @Override
    public User getUserById(Long id) {
        User user = restTemplate.getForEntity("http://localhost:8080/getUser/" + id,
                User.class).getBody();
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        ResponseEntity<User> userResponseEntity = restTemplate.getForEntity("http://localhost:8080/getUserByLogin/" + login,
                User.class);
        User user = userResponseEntity.getBody();
        return user;
    }

    @Override
    public void addUser(User user) {
        restTemplate.postForEntity("http://localhost:8080/addUserFromClient/",
                user, User.class);

    }

    @Override
    public void editUser(User user) {
         restTemplate.postForEntity("http://localhost:8080/editUserFromClient/",
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
