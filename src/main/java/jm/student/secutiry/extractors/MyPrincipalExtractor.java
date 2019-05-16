package jm.student.secutiry.extractors;

import jm.student.models.Role;
import jm.student.models.User;
import jm.student.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyPrincipalExtractor implements PrincipalExtractor {
    private UserService userService;

    public MyPrincipalExtractor(UserService userService){
        this.userService=userService;
    }
    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        String sub = (String) map.get("sub");
        String login = (String) map.get("email");
        //login="user";
        //User user = userService.getUserByLogin(login);
        User newUser = new User(login,sub,true);
        //System.out.println(user);
        if (true){
        //    newUser.setId(Long.valueOf(2));
            Set<Role> roles = new HashSet<>();
            Role role = new Role("admin");
            role.setId(Long.valueOf(1));
            roles.add(role);
            newUser.setRoles(roles);
            userService.addUser(newUser);
        }

        return userService.getById(Long.valueOf(3));
    }
}
