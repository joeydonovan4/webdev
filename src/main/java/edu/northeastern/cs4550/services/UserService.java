package edu.northeastern.cs4550.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import edu.northeastern.cs4550.models.User;
import edu.northeastern.cs4550.repositories.UserRepository;
import edu.northeastern.cs4550.utils.ResourceNotFoundException;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByID(int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException(User.class, "id", Integer.toString(id));
        }
        return user.get();
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException(User.class, "username", username);
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        User existingUser = findUserByID(user.getId());

        existingUser.setDateOfBirth(user.getDateOfBirth());
        existingUser.setEmail(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setPhone(user.getPhone());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }

    @Override
    public User deleteUser(int id) {
        User existingUser = findUserByID(id);
        userRepository.delete(existingUser);
        return existingUser;
    }
}
