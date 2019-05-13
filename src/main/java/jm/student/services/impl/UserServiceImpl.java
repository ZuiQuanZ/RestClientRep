package jm.student.services.impl;

import jm.student.restTemplateDataAccess.UserRestTemplateDataAccess;
import jm.student.models.User;
import jm.student.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRestTemplateDataAccess userRestTemplateDataAccess;

    @Autowired
    public UserServiceImpl(UserRestTemplateDataAccess userRestTemplateDataAccess) {
        this.userRestTemplateDataAccess = userRestTemplateDataAccess;
    }

    @Override
    public User getById(Long id) {
        return userRestTemplateDataAccess.getUserById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRestTemplateDataAccess.getUserByLogin(login);
    }

    @Override
    public void addUser(User user) {
        userRestTemplateDataAccess.addUser(user);
    }

    @Override
    public void editUser(User user) {
        userRestTemplateDataAccess.editUser(user);
    }

    @Override
    public void removeUser(Long id) {
        userRestTemplateDataAccess.removeUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRestTemplateDataAccess.getAllUsers();
    }
}
