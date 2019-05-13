package jm.student.services.impl;

import jm.student.restTemplateDataAccess.UserRTDA;
import jm.student.models.User;
import jm.student.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRTDA userRTDA;

    @Autowired
    public UserServiceImpl(UserRTDA userRTDA) {
        this.userRTDA = userRTDA;
    }

    @Override
    public User getById(Long id) {
        return userRTDA.getUserById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRTDA.getUserByLogin(login);
    }

    @Override
    public void addUser(User user) {
        userRTDA.addUser(user);
    }

    @Override
    public void editUser(User user) {
        userRTDA.editUser(user);
    }

    @Override
    public void removeUser(Long id) {
        userRTDA.removeUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRTDA.getAllUsers();
    }
}
