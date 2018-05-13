package edu.northeastern.cs4550.services;

import java.util.List;

import edu.northeastern.cs4550.models.User;

public interface IUserService {

    User createUser(User user);

    List<User> findAllUsers();

    User findUserByID(int id);

    User updateUser(User user);

    User deleteUser(int id);
}
