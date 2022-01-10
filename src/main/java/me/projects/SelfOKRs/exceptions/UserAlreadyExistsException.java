package me.projects.SelfOKRs.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String emailAddress) {
        super("A user with " + emailAddress + " already exists");
    }
}
