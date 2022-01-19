package me.projects.SelfOKRs.exceptions;

public class UserNotAuthorized extends RuntimeException {

    public UserNotAuthorized(String username) {
        super("User " + username + " is not authorized");
    }
}
