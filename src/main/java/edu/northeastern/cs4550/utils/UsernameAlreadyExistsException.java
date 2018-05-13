package edu.northeastern.cs4550.utils;


public class UsernameAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsernameAlreadyExistsException(String username) {
        super("Username " + username + " already in use. Please select another.");
    }
}
