package jm.student.models;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    private Long id;

    private String role;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public String toString() {
        if (role.contains("ADMIN") && role.contains("USER")) {
            return "admin, user";
        } else if (role.contains("ADMIN")) {
            return "admin";
        }
        return "user";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role roles = (Role) o;
        return role.equals(roles.role);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + role.hashCode();
        return result;
    }

}
