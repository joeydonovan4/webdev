package edu.northeastern.cs4550.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import edu.northeastern.cs4550.models.User;
import edu.northeastern.cs4550.services.IUserService;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody User user, HttpServletRequest request)
            throws FailedLoginException{
        User existingUser =
                userService.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (existingUser == null) {
            throw new FailedLoginException("Incorrect password.");
        }
        request.getServletContext().setAttribute("user", existingUser);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out.");
    }

    @GetMapping
    public ResponseEntity<User> getLoggedInUser(HttpServletRequest request) {
        User currentUser = (User) request.getServletContext().getAttribute("user");
        return ResponseEntity.ok(currentUser);
    }
}
