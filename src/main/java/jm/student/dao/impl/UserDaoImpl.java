package jm.student.dao.impl;

import jm.student.dao.UserDao;
import jm.student.models.User;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    public RestTemplate restTemplate;

    private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();

        clientHttpRequestFactory.setHttpClient(httpClient());

        return clientHttpRequestFactory;
    }

    private HttpClient httpClient() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("admin", "admin"));

        HttpClient client = HttpClientBuilder
                .create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();
        return client;
    }

    @Autowired
    public UserDaoImpl() {
        this.restTemplate = new RestTemplate(getClientHttpRequestFactory());
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
