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
        Object attr = session.getAttribute("user");
        if (attr == null) {
            throw new AccessDeniedException("Unauthorized");
        }
        User currentUser = (User) attr;
        user.setId(currentUser.getId());
        User updatedUser = userService.updateUser(user);
        session.setAttribute("user", updatedUser);
        return ResponseEntity.ok(updatedUser);
    }
}
