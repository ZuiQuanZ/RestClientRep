package jm.student.controllers;

import jm.student.models.User;
import jm.student.secutiry.utility.CodeMessenger;
import jm.student.secutiry.utility.ErrorCode;
import jm.student.secutiry.utility.SuccessCode;
import jm.student.service.abstraction.RoleService;
import jm.student.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    private MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String toLoginPage() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public ModelAndView loginPage(ModelAndView model, HttpServletRequest request) {
        ErrorCode code = CodeMessenger.getErrorCode();
        SuccessCode successCode = CodeMessenger.getSuccessCode();
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!user.equals("anonymousUser")) {
            model.setViewName("redirect:" + request.getHeader("refer"));
            return model;
        }

        model.setViewName("login");
        if (code != null && code.equals(ErrorCode.LOGIN)) {
            model.addObject("isNotValid", true);
        }

        if (successCode != null && successCode.equals(SuccessCode.LOGOUT)) {
            model.addObject("logout", true);
        }

        return model;
    }

    @GetMapping("/admin")
    public ModelAndView usersListPage(@ModelAttribute ModelAndView model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addObject("admin", userService.getById(user.getId()));
        return model;
    }

    @GetMapping("/user")
    public ModelAndView userPage(@ModelAttribute ModelAndView model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addObject("roles", roleService.getAllRoles());
        model.addObject("user", userService.getById(user.getId()));
        return model;
    }

}
