package me.projects.SelfOKRs.exceptions;

public class UserEntityNotFoundException extends RuntimeException {

    public UserEntityNotFoundException(Long id) {
        super("Could not find User " + id);
    }
}
