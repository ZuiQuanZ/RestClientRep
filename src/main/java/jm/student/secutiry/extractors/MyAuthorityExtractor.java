package jm.student.secutiry.extractors;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MyAuthorityExtractor implements AuthoritiesExtractor {
    List<GrantedAuthority> AUTHORITIES
            = AuthorityUtils.createAuthorityList("ADMIN");
//    List<GrantedAuthority> SUBSCRIBED_AUTHORITIES
//            = AuthorityUtils.commaSeparatedStringToAuthorityList(
//            "USER,ADMIN");

    @Override
    public List<GrantedAuthority> extractAuthorities
            (Map<String, Object> map) {

//        if (Objects.nonNull(map.get("plan"))) {
//            if (!((LinkedHashMap) map.get("plan"))
//                    .get("name")
//                    .equals("free")) {
//                return SUBSCRIBED_AUTHORITIES;
//            }
//        }
        return AUTHORITIES;
    }
}
