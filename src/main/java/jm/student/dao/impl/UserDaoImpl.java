package jm.student.dao.impl;

import jm.student.dao.UserDao;
import jm.student.models.Role;
import jm.student.models.User;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tomcat.util.codec.binary.Base64;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
//    @PersistenceContext
//    EntityManager entityManager;

//    private static HttpHeaders headersuser = new HttpHeaders();
//    static {
//
//        // headersuser.setContentType(MediaType.APPLICATION_JSON);
//        String auth = "admin" + ":" + "admin";
//        byte[] encodedAuth = Base64.encodeBase64(
//                auth.getBytes(Charset.forName("US-ASCII")) );
//        String authHeader = "Basic " + /*new String( encodedAuth )*/auth ;
//        headersuser.set( "Authorization", authHeader );
//    }
//
//
//
//    HttpEntity<User[]> userEntities = new HttpEntity<>(headersuser);
//    HttpEntity<User> userEntity = new HttpEntity<>(headersuser);
//




    public RestTemplate restTemplate;





    private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory()
    {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();

        clientHttpRequestFactory.setHttpClient(httpClient());

        return clientHttpRequestFactory;
    }

    private HttpClient httpClient()
    {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("admin", "admin"));

        HttpClient client = HttpClientBuilder
                .create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();
        return client;
    }
    //private static RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());



    public UserDaoImpl(){}

    @Override
    public User getUserById(Long id) {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        User user = restTemplate.getForEntity("http://localhost:8080/getUser/" + id,
                User.class).getBody();
        return user;
//        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByLogin(String login) {
        System.out.println("yey");
        User user = restTemplate.getForEntity("http://localhost:8080/getUserByLogin/" + login,
                User.class).getBody();
        return user;
        //return (User) entityManager.createQuery("select u from User u where u.login = :login").setParameter("login", login).getSingleResult();
    }

    @Override
    public void addUser(User user) {
         restTemplate.postForEntity("http://localhost:8080/addUserFromClient/" ,
                user, User.class);
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
        User user = restTemplate.getForEntity("http://localhost:8080/removeUserById/" + id,
                 User.class).getBody();
        //User user = entityManager.find(User.class, id);
        //entityManager.remove(user);
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
//        return (List<User>) entityManager.createQuery("FROM User").getResultList();
    }
}
