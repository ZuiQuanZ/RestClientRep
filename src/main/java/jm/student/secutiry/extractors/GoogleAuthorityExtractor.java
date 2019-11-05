package jm.student.secutiry.extractors;

import jm.student.models.User;
import jm.student.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GoogleAuthorityExtractor implements AuthoritiesExtractor {

    private UserService userService;

    @Autowired
    public GoogleAuthorityExtractor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
        String login = (String) map.get("email");
        User user = userService.getUserByLogin(login);
        return new ArrayList<>(user.getRoles());
    }
}
