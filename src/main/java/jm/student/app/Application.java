package jm.student.app;

import jm.student.models.Role;
import jm.student.models.User;
import jm.student.service.abstraction.RoleService;
import jm.student.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "jm.student")
@EntityScan(basePackages = "jm.student.models")

public class Application {
//    @Bean(initMethod = "init")
//    @Autowired
//    public TestDataInit initTestData(UserService userService, RoleService roleService) {
//        return new TestDataInit(userService, roleService);
//    }
//
//    private static UserService userService;
//
//    private static RoleService roleService;

    //    @Autowired
//    public Application(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
    static final String url_role = "http://localhost:8080/getRole";
    static final String url_user = "http://localhost:8080/getUser/1";
    static final String url_roles = "http://localhost:8080/getRoleList";
    static final String url_users = "http://localhost:8080/getUserList";
//
//
//    public static final String USER_NAME = "admin";
//    public static final String PASSWORD = "admin";

    public static void main(String[] args) {
        SpringApplication.run(Application.class);

//
//        HttpHeaders headers = new HttpHeaders();
////
//        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
//        // Request to return JSON format
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("my_other_key", "my_other_value");
//        HttpEntity<User> userEntity = new HttpEntity<>(headers);
//        HttpEntity<User[]> userEntities = new HttpEntity<>(headers);
////
////        // RestTemplate
//        RestTemplate restTemplate = new RestTemplate();
////
//        // Send request with GET method, and Headers.
        //todo: eat this
//        System.out.println(userService.getById(Long.valueOf(3)).getLogin());
//        HttpEntity<User> userEntityAdd = new HttpEntity<>(userService.getById(Long.valueOf(3)));
//        List<User> users= userService.getAllUsers();
//        User user2  = restTemplate.postForEntity("http://localhost:8080/addUserFromClient/" ,
//                userService.getById(Long.valueOf(3)), User.class).getBody();
//
//    }
    }
}
