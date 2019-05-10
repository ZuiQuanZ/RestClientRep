package jm.student.dao.impl;

import jm.student.dao.UserDao;
import jm.student.models.Role;
import jm.student.models.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    EntityManager entityManager;

    private static HttpHeaders headersuser = new HttpHeaders();
   // headersuser.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<User[]> userEntities = new HttpEntity<>(headersuser);
    HttpEntity<User> userEntity = new HttpEntity<>(headersuser);

    private static RestTemplate restTemplate = new RestTemplate();



    public UserDaoImpl(){}

    @Override
    public User getUserById(Long id) {
        User user = restTemplate.exchange("http://localhost:8080/getUser/" + id,
                HttpMethod.GET, userEntity, User.class).getBody();
        return user;
//        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByLogin(String login) {
        User user = restTemplate.exchange("http://localhost:8080/getUserByLogin/" + login,
                HttpMethod.GET, userEntity, User.class).getBody();
        return user;
        //return (User) entityManager.createQuery("select u from User u where u.login = :login").setParameter("login", login).getSingleResult();
    }

    @Override
    public void addUser(User user) {
        User user1 = restTemplate.postForEntity("http://localhost:8080/addUserFromClient/" ,
                user, User.class).getBody();
//        entityManager.persist(user);
    }

    @Override
    public void editUser(User user) {
        User user1 = restTemplate.postForEntity("http://localhost:8080/editUserFromClient/" ,
                user, User.class).getBody();
        //entityManager.merge(user);
    }

    @Override
    public void removeUser(Long id) {
        User user = restTemplate.exchange("http://localhost:8080/removeUserById/" + id,
                HttpMethod.GET, userEntity, User.class).getBody();
        //User user = entityManager.find(User.class, id);
        //entityManager.remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        User[] userListFromServer = restTemplate.exchange("http://localhost:8080/getUserList",
                HttpMethod.GET, userEntities, User[].class).getBody();
        List<User> allUsers = new ArrayList<>();
        for (User user : userListFromServer
        ) {
            allUsers.add(user);
        }
        return allUsers;
//        return (List<User>) entityManager.createQuery("FROM User").getResultList();
    }
}
