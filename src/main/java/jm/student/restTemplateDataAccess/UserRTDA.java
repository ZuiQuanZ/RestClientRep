package jm.student.restTemplateDataAccess;

import jm.student.models.User;

import java.util.List;

public interface UserRTDA {
    User getUserById(Long id);

    User getUserByLogin(String login);

    void addUser(User user);

    void editUser(User user);

    void removeUser(Long id);

    List<User> getAllUsers();
}
