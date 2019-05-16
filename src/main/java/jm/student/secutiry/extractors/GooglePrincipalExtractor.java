package jm.student.secutiry.extractors;

import jm.student.models.Role;
import jm.student.models.User;
import jm.student.services.RoleService;
import jm.student.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class GooglePrincipalExtractor implements PrincipalExtractor {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    public GooglePrincipalExtractor() {
    }

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        String sub = (String) map.get("sub");
        String login = (String) map.get("email");
        User user = userService.getUserByLogin(login);

        if (user == null) {
            User newUser = new User(login, sub, true);
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRoleById(Long.valueOf(2)));
            newUser.setRoles(roles);
            userService.addUser(newUser);
        }


        return userService.getUserByLogin(login);
    }
}
