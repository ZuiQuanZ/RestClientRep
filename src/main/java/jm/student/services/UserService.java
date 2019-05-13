package jm.student.services;

import jm.student.models.User;

import java.util.List;

public interface UserService {
    User getById(Long id);

    User getUserByLogin(String login);

    void addUser(User user);

    void editUser(User user);

    void removeUser(Long id);

    List<User> getAllUsers();
}
