package edu.northeastern.cs4550.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import edu.northeastern.cs4550.models.User;
import edu.northeastern.cs4550.services.IUserService;
import edu.northeastern.cs4550.utils.UsernameAlreadyExistsException;

@RestController
@RequestMapping("/api/register")
public class SignupController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<User> register(@Valid @RequestBody User user, HttpSession session) {
        User existingUser = userService.findUserByUsername(user.getUsername());
        if (existingUser != null) {
            throw new UsernameAlreadyExistsException(user.getUsername());
        }
        User createdUser = userService.createUser(user);
        session.setAttribute("user", createdUser);
        return ResponseEntity.ok(createdUser);
    }
}
