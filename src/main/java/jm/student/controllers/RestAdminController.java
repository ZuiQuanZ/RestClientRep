package jm.student.controllers;

import jm.student.models.Role;
import jm.student.models.User;
import jm.student.services.RoleService;
import jm.student.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestAdminController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    private RestAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/delRest")
    public void delRest(Long id) {
        userService.removeUser(id);
    }


    @PostMapping(path = "/admin/addUser", consumes = "application/json")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PostMapping(path = "/admin/editUser", consumes = "application/json")
    public void editUser(@RequestBody User user) {
        userService.editUser(user);
    }

    @GetMapping("/admin/getUser")
    public User getUser(Long id) {
        return userService.getById(id);
    }

    @GetMapping("/admin/getUsers")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/admin/getAllRoles")
    public  List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

}
