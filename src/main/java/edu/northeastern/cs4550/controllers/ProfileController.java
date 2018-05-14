package edu.northeastern.cs4550.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import edu.northeastern.cs4550.models.User;
import edu.northeastern.cs4550.services.IUserService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private IUserService userService;

    @PutMapping
    public ResponseEntity<User> updateProfile(@Valid @RequestBody User user, HttpSession session)
        throws AccessDeniedException {
        if (session.getAttribute("user") == null) {
            throw new AccessDeniedException("Unauthorized");
        }
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }
}
