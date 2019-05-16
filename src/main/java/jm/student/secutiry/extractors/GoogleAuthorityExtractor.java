package jm.student.secutiry.extractors;

import jm.student.models.Role;
import jm.student.models.User;
import jm.student.services.RoleService;
import jm.student.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class GoogleAuthorityExtractor implements AuthoritiesExtractor {
    @Autowired
    private UserService userService;

    @Override
    public List<GrantedAuthority> extractAuthorities
            (Map<String, Object> map) {

        String login = (String) map.get("email");
        User user = userService.getUserByLogin(login);
        Set<Role> userRoles = user.getRoles();
        String googleAuth = new String();

        for (Role role: userRoles
             ) {
            googleAuth+=role.getAuthority();
            googleAuth+=',';
        }
        googleAuth.substring(0,googleAuth.length()-1);
        List<GrantedAuthority> authorities= AuthorityUtils.commaSeparatedStringToAuthorityList(googleAuth);
 //        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles.toString());
//        if (userRoles.contains(roleService.getRoleById(Long.valueOf(1)))&&userRoles.contains(roleService.getRoleById(Long.valueOf(2)))){
//            List<GrantedAuthority> AUTHORITIES
//            = AuthorityUtils.commaSeparatedStringToAuthorityList(
//            "USER,ADMIN");
//            return AUTHORITIES;
//        } else if (userRoles.contains(roleService.getRoleById(Long.valueOf(1)))){
//            List<GrantedAuthority> AUTHORITIES
//            = AuthorityUtils.createAuthorityList("ADMIN");
//            return AUTHORITIES;
//        } else if (userRoles.contains(roleService.getRoleById(Long.valueOf(2)))){
//            List<GrantedAuthority> AUTHORITIES
//                    = AuthorityUtils.createAuthorityList("USER");
//            return AUTHORITIES;
//        }

      return authorities;
    }
}
